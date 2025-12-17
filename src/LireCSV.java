
import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;

class LireCSV extends GestionCSV {

    private static final String TITRE = "Lecture de fichier CSV";

    public static void start() {
        // afficher le titre du choix fait
        titreChoix(TITRE);

        String chemin;

        // demander le chemin tant que celui-ci n'est pas valide
        do {
            chemin = askPath();
        } while (!isPathValidCSV(chemin));

        lecture(chemin);
    }

    private static void lecture(String chemin) {
        File fichierCSV = new File(chemin);

        Path tempChemin = Path.of(chemin);

        afficherMessage(tempChemin);

        // lire le fichier
        try (Scanner lecteur = new Scanner(fichierCSV)) {
            while (lecteur.hasNextLine()) {
                String ligne = lecteur.nextLine();
                System.out.println(ligne);
            }
        } catch (Exception e) {
            System.err.println("Une erreur s'est produite.");
        }
    }

    private static void afficherMessage(Path chemin) {
        System.out.printf("%nLecture du fichier : %s%n", chemin.getFileName());
    }
}
