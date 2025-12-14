import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDateTime; // <-- Pour la date et l'heure

public class Fail2BanLogParser implements ILogParser {
    
    private String regexString = "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})"; //sert pour le regex
    
    private Pattern monScanner;

    //Ici on a un pattern pour chercher l'IP dans les logs
    public Fail2BanLogParser() {
        this.monScanner = Pattern.compile(regexString);
    }

// On parse une ligne des log pour en extraire l'IP
    @Override
    public AttackEvent parseLogLine(String logLine) {
        
        Matcher chercheur = this.monScanner.matcher(logLine);
        
        if (chercheur.find()) {
            String ipTrouvee = chercheur.group(1);  //On récupère l'IP trouvée
            
            AttackEvent event = new AttackEvent(); // Puis on crée un nouvelle évènement 
            event.setIpAddress(ipTrouvee);
            event.setTimestamp(LocalDateTime.now());
            //Enfin on retourne l'évènement , la date et l'IP trouvée

            return event;
        }
        
        return null;
    }
}