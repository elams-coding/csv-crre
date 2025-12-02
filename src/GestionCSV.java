
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class GestionCSV {

    protected static final Scanner sc = App.sc;
    protected static final Logger logger = App.logger;

    private static void askPath() {
        logger.info("Demande du chemin en cours");

        String path;
        System.out.print("Chemin du fichier CSV : ");
        path = sc.nextLine();

        while (path.isBlank()) {
            logger.log(Level.WARNING, "Le chemin est vide");

            System.err.println("Erreur de saisie.");
            System.out.print("Recommencez: ");
            path = sc.nextLine();
        }

        isPathValidCSV(path);
    }

    private static boolean isPathValidCSV(String strPath) {
        Path path = Path.of(strPath);

        boolean existe = Files.exists(path);
        boolean estFichierCSV = Files.isRegularFile(path) && path.toString().toLowerCase().endsWith(".csv");

        return existe && estFichierCSV;
    }
}
