
import java.util.Scanner;
import java.util.logging.Logger;

public class App {

    public static final Scanner SC = new Scanner(System.in);
    public static final Logger logger = Logger.getLogger(App.class.getName());

    static final int CREER_CSV = 1;
    static final int LIRE_CSV = 2;
    static final int QUITTER = 3;

    public static void main(String[] args) throws Exception {
        try (SC) {
            logger.info("Début de l'application");

            System.out.printf("""
                -----------------------------
                          CSV  CRRE
                -----------------------------
                Choisir une option :
                    (%d) Créer un fichier CSV
                    (%d) Lire  un fichier CSV
                    (%d) Quitter
                -----------------------------

                """, new Object[]{CREER_CSV, LIRE_CSV, QUITTER});

            // conserver le choix dans une variable
            int choix = Utilitaire.choixMenu(new App());

            Utilitaire.executerChoix(choix, new App());
        }
    }
}
