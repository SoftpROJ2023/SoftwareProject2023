package frontendproductioncode;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FrontendComponents {
    private static final Logger logger = Logger.getLogger(FrontendComponents.class.getName());
    static boolean isExitPage = false;
    boolean loggedIn = false; // You can use this to track whether the user is logged in.

    HomePage homePage = new HomePage();

    public void frontendView(){
        displayWelcomePage();

        while (!isExitPage){
            Scanner scanner = new Scanner(System.in);
            logger.info("Please Enter Your Choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    homePage.signUp();
                    break;
                case 2:
                    homePage.signIn();
                    break;
                case 3:
                    exit();
                    break;
                default:
                    logger.info("Invalid choice.");
                    break;
            }
        }
    }
    public void displayWelcomePage() {
        logger.log(Level.INFO, "Welcome to our application!");
        logger.log(Level.INFO, "1. Registration");
        logger.log(Level.INFO, "2. Log In");
        logger.log(Level.INFO, "3. Exit");
    }
    public static void exit() {
        isExitPage =true;
        logger.info("Exit function called.");
    }

}

