
class LireCSV extends GestionCSV {

    public static void main() {
        verifierChemin();
    }

    private static void verifierChemin() {
        // appel de la fonction pour demander le chemin du fichier
        // CSV
        String chemin = askPath();

        while (!isPathValidCSV(chemin)) {
            chemin = askPath();
        }
    }
}
