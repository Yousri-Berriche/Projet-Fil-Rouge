// Main.java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- DÉMARRAGE DU MONITORING (LECTURE & ANALYSE) ---");

        // --- PHASE 1 : Préparation des Services (Injection de Dépendances) ---
        ILogReader reader = new LocalLogReader("simulation.log");
        ILogParser parser = new Fail2BanLogParser();
        
        // Nouvelle dépendance : La stratégie de géolocalisation
        IGeoLocationStrategy geoLocator = new ApiGeoStrategy(); 

        // --- PHASE 2 : Exécution de la Chaîne ---
        List<String> lignesDuFichier = reader.readLogs();
        System.out.println("📂 " + lignesDuFichier.size() + " lignes trouvées.");

        List<AttackEvent> attaquesDetectees = new ArrayList<>();

        // 1. Parsing
        for (String ligne : lignesDuFichier) {
            AttackEvent event = parser.parseLogLine(ligne);

            if (event != null) {
                // 2. Géolocalisation
                boolean success = geoLocator.locate(event); 
                
                if (success) {
                    attaquesDetectees.add(event);
                } else {
                    // Si la géolocalisation échoue (souvent car c'est une IP privée/locale), on l'ajoute quand même
                    attaquesDetectees.add(event); 
                }
            }
        }

        // --- PHASE 3 : Affichage du Résultat (pour la console) ---
        System.out.println("\n🚨 Rapport d'Attaques (" + attaquesDetectees.size() + " détectées) : ");

        for (AttackEvent attaque : attaquesDetectees) {
            String geoInfo = "";
            if (attaque.getLatitude() != 0.0) {
                geoInfo = String.format(" | Pays: %s | Lat/Lon: %.4f, %.4f", 
                                        attaque.getCountry(), 
                                        attaque.getLatitude(), 
                                        attaque.getLongitude());
            } else {
                geoInfo = " | Local/Non Géolocalisé";
            }
            
            System.out.println("   IP: " + attaque.getIpAddress() + geoInfo);
        }
        
        // ***************************************************************
        // --- PHASE 4 : Exportation des Données (pour la page Web) ---
        // ***************************************************************
        IDataExporter exporter = new JsonWriter();
        
        // Fichier où Apache pourra lire les données
        String outputPath = "data.json"; 
        
        // On exporte uniquement les attaques qui ont été géolocalisées avec succès.
        List<AttackEvent> geoLocatedAttacks = new ArrayList<>();
        for (AttackEvent event : attaquesDetectees) {
            if (event.getLatitude() != 0.0) { // Si la Lat/Lon a été remplie
                geoLocatedAttacks.add(event);
            }
        }
        
        if (exporter.export(geoLocatedAttacks, outputPath)) {
            System.out.println("   > " + geoLocatedAttacks.size() + " événements écrits dans " + outputPath);
        }

        System.out.println("--- FIN DU RAPPORT ---");
    } // <-- C'EST ICI QU'IL FAUT FERMER LA MÉTHODE main
} 