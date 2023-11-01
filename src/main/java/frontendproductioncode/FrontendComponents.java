package frontendproductioncode;

import my.backendproductioncode.AdminDashboard;
import my.backendproductioncode.ProductCatalog;
import my.backendproductioncode.RegistrationService;
import my.backendproductioncode.SignInServices;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FrontendComponents {
    private static final Logger logger = Logger.getLogger(FrontendComponents.class.getName());
    RegistrationService registrationService;
    SignInServices signInService;
    AdminDashboard admin;
    ProductCatalog productCatalogs;
    static boolean isExitPage = false;
    boolean loggedIn = false;
    boolean adminFlag = false;
    boolean userFlag = false;
    boolean isInUserDashboard=false;

    public FrontendComponents(){
        registrationService = new RegistrationService();
        signInService = new SignInServices();
        admin=new AdminDashboard();
        productCatalogs=new ProductCatalog(admin);
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
            else if(loggedIn && userFlag){
                displayUserDashboard();
                Scanner scanner = new Scanner(System.in);
                logger.info("Please Enter Your Choice: ");
                if (scanner.hasNextLine()) {
                    String choice = scanner.nextLine();
                    String search;
                    switch (choice) {
                        case "1":
                            logger.log(Level.INFO, "You selected 'See all categories'");
                            productCatalogs.printProductCategories();
                            isInUserDashboard=true;
                            break;
                        case "2":
                            logger.log(Level.INFO, "You selected 'Search for Product related to a specific category'");
                            displayEnterYourValue();
                            search=scanner.nextLine();
                            productCatalogs.getProductsRelatedToSpecificCategory(search);
                            break;
                        case "3":
                            logger.log(Level.INFO, "You selected 'See all products'");
                            productCatalogs.printAllProductData();
                            break;
                        case "4":
                            logger.log(Level.INFO, "You selected 'Search for a product'");
                            displayEnterYourValue();
                            search=scanner.nextLine();
                            productCatalogs.searchProductByNameAndPrintDetails(search);
                            break;
                        case "5":
                            logger.log(Level.INFO, "You selected 'Filter products by availability'");
                            displayEnterYourValue();
                            search=scanner.nextLine();
                            productCatalogs.filterProductsByAvailabilityAndPrintProductNames(search);
                            break;
                        case "6":
                            logger.log(Level.INFO, "Logging out...");
                            loggedIn=false;
                            userFlag=false;
                            break;
                        default:
                            logger.log(Level.INFO, "Invalid choice. Please enter a valid option (1-6).");
                            break;
                    }
                }
            }
            else if(loggedIn && adminFlag){
                displayAdminDashboard();
                Scanner scanner = new Scanner(System.in);
                logger.info("Please Enter Your Choice: ");
                if (scanner.hasNextLine()) {
                    String choice = scanner.nextLine();
                    String addCat;
                    switch(choice) {
                        case "1":
                            displayEnterYourValue();
                            addCat=scanner.nextLine();
                            admin.addProductCategory(addCat);
                            break;
                        case "2":
                            admin.printProductCategories();
                            break;
                        case "3":
                            loggedIn=false;
                            adminFlag=false;
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
    public void displayUserDashboard() {
        logger.log(Level.INFO, "Welcome to user dashboard");
        logger.log(Level.INFO, "1. See all categories ");
        logger.log(Level.INFO, "2. Search for Product related to a specific category");
        logger.log(Level.INFO, "3. See all products");
        logger.log(Level.INFO, "4. Search for a product");
        logger.log(Level.INFO, "5. Filter products by availability");
        logger.log(Level.INFO, "6. Log out");
    }
    public void displayAdminDashboard(){
        logger.log(Level.INFO, "Welcome to Admin dashboard");
        logger.log(Level.INFO, "1. Add a new catalog");
        logger.log(Level.INFO, "2. Edit an existing product category");
        logger.log(Level.INFO, "3. Delete an existing product category");
        logger.log(Level.INFO, "4. Add a product listing");
        logger.log(Level.INFO, "5. Update a product listing");
        logger.log(Level.INFO, "6. View customer accounts");
        logger.log(Level.INFO, "7. Search for a specific customer account");
        logger.log(Level.INFO, "8. Delete a customer account");
        logger.log(Level.INFO, "9. Add a new customer account");
        logger.log(Level.INFO, "10. Schedule a new installation appointment");
        logger.log(Level.INFO, "11. Update an existing installation appointment");
        logger.log(Level.INFO, "12. Cancel an existing installation appointment");

    }
    public void displayEnterYourValue(){
        logger.info(" ");
        logger.info("Please enter what you want to search for here");
        logger.info(" ");
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
        adminFlag=false;
        userFlag=false;
        Scanner scanner = new Scanner(System.in);
        logger.info("Welcome to the Sign-in page!");
        logger.info("Username: ");
        String username = scanner.nextLine();
        if (username.equals("Admin")) {
            adminFlag = true; // Set adminFlag to true if the username is "Admin"
        }else {
            userFlag=true;
        }
        logger.info("Password: ");
        String password = scanner.nextLine();
        signInService.signInUser(registrationService,username,password);
    }

    public static void exit() {
        isExitPage =true;
        logger.info("Exit function called.");
    }

}

