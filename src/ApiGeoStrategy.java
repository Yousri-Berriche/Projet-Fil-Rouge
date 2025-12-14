// ApiGeoStrategy.java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
// Note : Le parsing de JSON est trop complexe sans librairie externe (comme Gson). 
// Pour gagner du temps, on fait un parsing très simple qui ne marche qu'avec ip-api.com

public class ApiGeoStrategy implements IGeoLocationStrategy {

    private static final String API_URL = "http://ip-api.com/csv/"; // API simple en format CSV

    @Override
    public boolean locate(AttackEvent event) {
        String ip = event.getIpAddress();

        // On n'essaie pas de géolocaliser les IPs privées (comme 192.168.x.x ou 10.x.x.x)
        if (ip.startsWith("192.168.") || ip.startsWith("10.")) {
            return false;
        }

        try {
            URL url = new URL(API_URL + ip);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                
                // 1. Lire la réponse (qui est en CSV : Success,FR,France,IDF...)
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine = in.readLine();
                in.close();

                // 2. Parser le CSV
                String[] data = inputLine.split(",");

                // Le format attendu est : [0:status, 1:countryCode, 2:country, ..., 7:lat, 8:lon]
                if (data.length > 8 && data[0].equals("success")) {
                    
                    // 3. Remplir l'objet AttackEvent
                    event.setCountry(data[2]); // Pays
                    
                    // Conversion de String à double pour Lat/Lon
                    double lat = Double.parseDouble(data[7]); 
                    double lon = Double.parseDouble(data[8]);
                    event.setLatitude(lat);
                    event.setLongitude(lon);

                    return true; // Succès de la géolocalisation
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur API de géolocalisation pour " + ip + " : " + e.getMessage());
        }

        return false; // Échec
    }
}