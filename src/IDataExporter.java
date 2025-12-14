// IDataExporter.java

import java.util.List;

public interface IDataExporter {
    // Le contrat : prend une liste d'événements et les écrit dans un fichier.
    // Retourne 'true' si l'exportation a réussi.
    boolean export(List<AttackEvent> events, String filePath);
}