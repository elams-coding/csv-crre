
class LireCSV extends GestionCSV {

    private static final String titre = "Lecture de fichier CSV";

    public static void start() {
        // afficher le titre du choix fait
        titreChoix(titre);

        String chemin;

        // demander le chemin tant que celui-ci n'est pas valide
        do {
            chemin = askPath();
        } while (!isPathValidCSV(chemin));
    }
}
