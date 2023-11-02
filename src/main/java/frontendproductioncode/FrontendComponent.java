package frontendproductioncode;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FrontendComponent {

    private static final Logger logger = Logger.getLogger(FrontendComponent.class.getName());


    public void frontendView(){
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            logger.info("Welcome to the Car Accessories Company! We're excited to have you join us.");
            logger.info("\n1.Register\n2.Login\n3.Exit\n");
            logger.info("Choose an option: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (java.util.InputMismatchException e) {
                logger.log(Level.WARNING,"Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    logger.log(Level.INFO,"====================Register===============");
                    logger.log(Level.INFO,"You selected 'Register'.");

                case 2:
                    logger.log(Level.INFO,"====================Login===============");
                    logger.log(Level.INFO,"You selected 'Login'.");

                    break;
                case 3:
                    logger.log(Level.INFO,"Exiting...");
                    exit = true;
                    break;
                default:
                    logger.log(Level.WARNING,"Invalid choice. Please select a valid option.");
            }
        }
    }

}
