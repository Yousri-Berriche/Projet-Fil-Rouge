// IGeoLocationStrategy.java
public interface IGeoLocationStrategy {
    // Le contrat : prend un événement et essaie de le remplir avec des coordonnées.
    // Retourne 'true' si la localisation a réussi, 'false' sinon.
    boolean locate(AttackEvent event);
}