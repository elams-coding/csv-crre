
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class GestionCSV {

    protected static final Scanner sc = App.SC;
    protected static final Logger logger = App.logger;
    private static Path path;
    private static boolean existe = false;
    private static boolean estFichier = false;
    private static boolean estDossier = false;
    private static boolean estFichierCSV = false;

    /**
     * Afficher le titre de la section.
     *
     * @param titre Chaîne de caractères qui sert de titre de section
     */
    protected static void titreChoix(String titre) {
        // afficher le titre définie par le choix
        System.out.printf("""
        --< %s >--

        """, titre);
    }

    /**
     * Demande à l'utilisateur de saisir le chemin système du fichier CSV
     *
     * @return Le chemin du fichier CSV.
     */
    protected static String askPath() {
        logger.info("Demande du chemin en cours");

        String strPath;
        // demander la saisie du chemin de l'utilsateur
        System.out.print("Chemin du fichier CSV : ");
        strPath = sc.nextLine().trim();

        // saisir de nouveau le chemin tant que la saisie est vide
        // ou si le chemin n'est pas valide.
        while (strPath.isBlank() && !isPathValidCSV(strPath)) {
            logger.log(Level.WARNING, "Le chemin est vide");

            System.err.println("Erreur de saisie.");
            System.out.print("Recommencez: ");
            strPath = sc.nextLine().trim();
        }

        return strPath;
    }

    /**
     * Test si la chaîne de caractère entrée en argument est un chemin qui
     * existe dans le système et test le type d'élément sur lequel il pointe
     * (fichier régulier ou dossier).
     *
     * @param strPath Chaîne de caractère à tester
     *
     * @return Retourne {@code true} si la chaîne de caractère est un chemin qui
     * existe dans le système et si l'élément sur lequel pointe le chemin est un
     * fichier ou dossier. Sinon retourne {@code false}.
     */
    protected static boolean isPathValid(String strPath) {
        path = Path.of(strPath);

        existe = Files.exists(path);
        estFichier = Files.isRegularFile(path);
        estDossier = Files.isDirectory(path);

        // ajouter dans les logs un message en fonction de
        // l'existance ou non et le type d'élement dont il s'agit
        if (existe && estFichier) {
            logger.info("Le chemin spécifié pointe sur un fichier (régulier)");
        } else if (existe && estDossier) {
            logger.info("Le chemin spécifié pointe sur un dossier");
        } else {
            logger.log(Level.SEVERE, "Le chemin spécifié \"{0}\" ne pointe ni sur un fichier (régulier), ni sur un dossier", path);
        }

        return existe && estFichier || estDossier;
    }

    /**
     * Test si la chaîne de caractère entrée en argument est un chemin système
     * qui pointe sur un fichier CSV.
     *
     * @param strPath Chaîne de caractère convertie en chemin système à tester
     *
     * @return Retourne {@code true} si le chemin système pointe vers un fichier
     * CSV. Sinon {@code false}.
     */
    protected static boolean isPathValidCSV(String strPath) {
        if (isPathValid(strPath)) {
            // s'assurer que le chemin est bien celui mis en argument
            path = Path.of(strPath);

            estFichierCSV = path.toString().toLowerCase().endsWith(".csv");

            // ajouter dans les logs un message en fonction du type de
            // fichier
            if (estFichierCSV) {
                logger.info("Le fichier est un fichier CSV");
            } else {
                String nomFichier = "";

                if (!strPath.isBlank()) {
                    // obtenir le séparateur du Système
                    String separateur = File.separator;

                    // créer un tableau de noms présent dans le chemin donné
                    String[] noms = strPath.split(separateur);

                    // avoir l'index du dernier élement du tableau
                    int dernierElement = noms.length;
                    dernierElement--;

                    // conserver le dernier nom présent dans le tableau
                    nomFichier = noms[dernierElement];
                }

                if (nomFichier.isBlank()) {
                    logger.log(Level.WARNING, "Le fichier n'est pas un fichier CSV");
                } else {
                    logger.log(Level.WARNING, "Le fichier \"{0}\" n'est pas un fichier CSV", nomFichier);
                }
            }
        }

        return estFichierCSV;
    }
}
