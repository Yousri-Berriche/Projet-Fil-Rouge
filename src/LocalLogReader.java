import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LocalLogReader implements ILogReader {

    private String filePath;

    public LocalLogReader(String filePath) {
        this.filePath = filePath;
    }

    @Override // Maintenant, ceci est valide car Java sait ce qu'est ILogReader !
    public List<String> readLogs() {
        try {
            Path path = Paths.get(this.filePath);
            return Files.readAllLines(path);
            
        } catch (IOException e) {
            System.err.println("Erreur de lecture du fichier : " + e.getMessage());
            return new ArrayList<>();
        }
    }
}