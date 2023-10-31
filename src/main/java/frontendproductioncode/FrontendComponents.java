package frontendproductioncode;

import my.backendproductioncode.RegistrationService;
import my.backendproductioncode.SignInServices;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FrontendComponents {
    private static final Logger logger = Logger.getLogger(FrontendComponents.class.getName());
    RegistrationService registrationService;
    SignInServices signInService;
    static boolean isExitPage = false;
    boolean loggedIn = false;

    public FrontendComponents(){
        registrationService = new RegistrationService();
        signInService = new SignInServices();
    }

    public void frontendView(){

        while (!isExitPage){
            if(!loggedIn){
                displayWelcomePage();
                Scanner scanner = new Scanner(System.in);
                logger.info("Please Enter Your Choice: ");
                if (scanner.hasNextLine()) {
                    String choice = scanner.nextLine();
                    switch(choice) {
                        case "1":
                            register();
                            signIn();
                            loggedIn=true;
                            break;
                        case "2":
                            signIn();
                            loggedIn=true;
                            break;
                        case "3":
                            exit();
                            break;
                        default:
                            logger.info("invalid input");
                            break;
                    }
                }
            }


        }
    }
    public void displayWelcomePage() {
        logger.log(Level.INFO, "Welcome to our application!");
        logger.log(Level.INFO, "1. Registration");
        logger.log(Level.INFO, "2. Log In");
        logger.log(Level.INFO, "3. Exit");
    }
    public void register(){
        Scanner scanner = new Scanner(System.in);
        logger.info("Welcome to the Sign-up page!");
        logger.info("Username: ");
        String username = scanner.nextLine();
        logger.info("Email: ");
        String email = scanner.nextLine();
        logger.info("Password: ");
        String password = scanner.nextLine();
        logger.info("Confirm Password: ");
        String confirmPassword = scanner.nextLine();
        if (password.equals(confirmPassword)) {
            registrationService.registerUser(username,email,password,confirmPassword);
        } else {
            logger.info("Password and Confirm Password do not match. Registration failed.");
        }
    }
    public void signIn(){
        Scanner scanner = new Scanner(System.in);
        logger.info("Welcome to the Sign-in page!");

        logger.info("Username: ");
        String username = scanner.nextLine();

        logger.info("Password: ");
        String password = scanner.nextLine();
        signInService.signInUser(registrationService,username,password);
    }

    public static void exit() {
        isExitPage =true;
        logger.info("Exit function called.");
    }

}

