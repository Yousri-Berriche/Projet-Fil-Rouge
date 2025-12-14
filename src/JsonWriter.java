// JsonWriter.java

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonWriter implements IDataExporter {

    @Override
    public boolean export(List<AttackEvent> events, String filePath) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[\n"); // Début du tableau JSON

        try {
            for (int i = 0; i < events.size(); i++) {
                AttackEvent event = events.get(i);
                
                // On crée un objet JSON manuellement pour chaque événement
                jsonBuilder.append("  {\n");
                jsonBuilder.append("    \"ip\": \"").append(event.getIpAddress()).append("\",\n");
                jsonBuilder.append("    \"country\": \"").append(event.getCountry()).append("\",\n");
                jsonBuilder.append("    \"lat\": ").append(event.getLatitude()).append(",\n");
                jsonBuilder.append("    \"lon\": ").append(event.getLongitude()).append("\n");
                jsonBuilder.append("  }");

                // Ajouter une virgule si ce n'est pas le dernier élément
                if (i < events.size() - 1) {
                    jsonBuilder.append(",\n");
                } else {
                    jsonBuilder.append("\n");
                }
            }
            jsonBuilder.append("]"); // Fin du tableau JSON

            // Écrire le JSON dans le fichier (ex: data.json)
            try (FileWriter file = new FileWriter(filePath)) {
                file.write(jsonBuilder.toString());
                System.out.println("\n✅ Exportation réussie : " + filePath);
                return true;
            }
        } catch (IOException e) {
            System.err.println("❌ Erreur lors de l'écriture JSON : " + e.getMessage());
            return false;
        }
    }
    
}