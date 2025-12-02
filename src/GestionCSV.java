
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class GestionCSV {

    protected static final Scanner sc = App.sc;
    protected static final Logger logger = App.logger;
    private static Path path;
    private static boolean existe = false;
    private static boolean estFichier = false;
    private static boolean estDossier = false;
    private static boolean estFichierCSV = false;

    private static void askPath() {
        logger.info("Demande du chemin en cours");

        String strPath;
        // demander la saisie du chemin de l'utilsateur
        System.out.print("Chemin du fichier CSV : ");
        strPath = sc.nextLine().trim();

        // saisir de nouveau le chemin tant que la saisie est vide
        while (strPath.isBlank()) {
            logger.log(Level.WARNING, "Le chemin est vide");

            System.err.println("Erreur de saisie.");
            System.out.print("Recommencez: ");
            strPath = sc.nextLine().trim();
        }

        // appel de la fonction pour vérifier que le chemin soit
        // valide
        isPathValid(strPath);
    }

    private static boolean isPathValid(String strPath) {
        while (strPath.isBlank()) {
            askPath();
        }

        path = Path.of(strPath);

        existe = Files.exists(path);
        estFichier = Files.isRegularFile(path);
        estDossier = Files.isDirectory(path);

        // ajouter dans les logs un message en fonction de
        // l'existance ou non et le type d'élement dont il s'agit
        if (existe && estFichier) {
            logger.info("Le chemin spécifié \"{0}\" pointe sur un fichier (régulier)");
        } else if (existe && estDossier) {
            logger.info("Le chemin spécifié \"{0}\" pointe sur un dossier");
        } else {
            logger.log(Level.WARNING, "Le chemin spécifié \"{0}\" ne pointe ni sur un fichier (régulier), ni sur un dossier", path);
        }

        return existe && estFichier || estDossier;
    }

    private static boolean isPathValidCSV(String strPath) {
        if (isPathValid(strPath)) {
            // s'assurer que le chemin est bien celui mis en argument
            path = Path.of(strPath);

            estFichierCSV = path.toString().toLowerCase().endsWith(".csv");

            // ajouter dans les logs un message en fonction du type de
            // fichier
            if (estFichierCSV) {
                logger.info("Le fichier est un fichier CSV");
            } else {
                // obtenir le séparateur du Système
                String separateur = File.separator;

                // créer un tableau de noms présent dans le chemin donné
                String[] noms = strPath.split(separateur);

                // avoir l'index du dernier élement du tableau
                int dernierElement = noms.length;
                dernierElement--;

                // conserver le dernier nom présent dans le tableau
                String nomFichier = noms[dernierElement];

                logger.log(Level.WARNING, "Le fichier \"{0}\" n'est pas un fichier CSV", nomFichier);
            }
        }

        return estFichierCSV;
    }
}
