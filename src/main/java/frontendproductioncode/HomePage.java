package frontendproductioncode;

import my.backendproductioncode.RegistrationService;

import java.util.Scanner;
import java.util.logging.Logger;

public class HomePage {
    HomePage() {
        // Initialization code, if any
    }
    Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(HomePage.class.getName());
    public  void signUp() {
        RegistrationService userService = new RegistrationService();
        logger.info("Enter your username: ");
        String username = scanner.nextLine();

        logger.info("Enter your email: ");
        String email = scanner.nextLine();

        logger.info("Enter your password: ");
        String password = scanner.nextLine();

        logger.info("Confirm your password: ");
        String confirmPassword = scanner.nextLine();

        if (password.equals(confirmPassword)) {
            logger.info("Sign-up successful! You can now sign in.");
            userService.registerUser(username,email,password,confirmPassword);
            userService.printRegisteredUsers();
        } else {
            logger.info("Password and confirmation do not match. Please try again.");
        }
    }

    public  void signIn() {
        logger.info("Sign In function called.");
    }

}
