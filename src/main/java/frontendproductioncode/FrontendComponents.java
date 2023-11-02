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
    private static int id;
    private static String name;
    private static String description;
    private static int price;
    private static String category;
    private static String availability;
    RegistrationService registrationService;
    SignInServices signInService;
    AdminDashboard admin;
    ProductCatalog productCatalogs;
    static boolean isExitPage = false;
    boolean loggedIn = false;
    boolean adminFlag = false;
    boolean userFlag = false;
    boolean isInUserDashboard=false;
    String message="Please Enter Your Choice:";

    public FrontendComponents(){
        registrationService = new RegistrationService();
        signInService = new SignInServices();
        admin=new AdminDashboard();
        productCatalogs=new ProductCatalog(admin);
    }


    public void frontendView() {
        while (!isExitPage) {
            if (!loggedIn) {
                handleWelcomePage();
            } else if ( userFlag) {
                handleUserDashboard();
            } else if ( adminFlag) {
                handleAdminDashboard();
            }
        }
    }

    private void handleWelcomePage() {
        displayWelcomePage();
        Scanner scanner = new Scanner(System.in);
        logger.info(message);
        if (scanner.hasNextLine()) {
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    register();
                    signIn();
                    loggedIn = true;
                    break;
                case "2":
                    signIn();
                    loggedIn = true;
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
    private void handleUserDashboard() {
        displayUserDashboard();
        Scanner scanner = new Scanner(System.in);
        logger.info(message);
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
    private void handleAdminDashboard() {
        displayAdminDashboard();
        Scanner scanner = new Scanner(System.in);
        logger.info(message);
        if (scanner.hasNextLine()) {
            String choice = scanner.nextLine();
            String addCat;

            switch (choice) {
                case "1":
                    logger.log(Level.INFO, "You chose see all categories");
                    admin.printProductCategories();
                    break;
                case "2":
                    logger.log(Level.INFO, "You chose to Add a new catalog");
                    displayEnterYourValue();
                    addCat = scanner.nextLine();
                    admin.addProductCategory(addCat);
                    break;
                case "3":
                    logger.log(Level.INFO, "You chose to Edit an existing product category");
                    logger.info("You Category");
                    addCat = scanner.nextLine();
                    logger.info("New Category Name");
                    String newName=scanner.nextLine();
                    admin.editProductCategory(addCat,newName);
                    break;
                case "4":
                    logger.log(Level.INFO, "You chose to Delete an existing product category");
                    displayEnterYourValue();
                    addCat = scanner.nextLine();
                    admin.deleteProductCategory(addCat);
                    break;
                case "5":
                    logger.log(Level.INFO, "You chose see all products");
                    productCatalogs.printAllProductData();
                    break;
                case "6":
                    logger.log(Level.INFO, "You chose to Add a product listing");
                    readInputFromUser();
                    admin.addProduct(id, name, description, price, category, availability);
                    break;
                case "7":
                    logger.log(Level.INFO, "You chose to Update a product listing");

                    break;
                case "8":
                    logger.log(Level.INFO, "You chose to View customer accounts");
                    registrationService.printRegisteredUsers();
                    break;
                case "9":
                    logger.log(Level.INFO, "You chose to Search for a specific customer account");
                    displayEnterYourValue();
                    addCat = scanner.nextLine();
                    registrationService.printRegisteredUser(addCat);
                    break;
                case "10":
                    logger.log(Level.INFO, "You chose to Delete a customer account");
                    displayEnterYourValue();
                    addCat = scanner.nextLine();
                    registrationService.deleteUser(addCat);
                    break;
                case "11":
                    logger.log(Level.INFO, "You chose to Add a new customer account");
                    register();
                    break;
                case "12":
                    // Handle option 10
                    logger.log(Level.INFO, "You chose to Schedule a new installation appointment");

                    break;
                case "13":
                    // Handle option 11
                    logger.log(Level.INFO, "You chose to Update an existing installation appointment");
                    // Add your logic here
                    break;
                case "14":
                    // Handle option 12
                    logger.log(Level.INFO, "You chose to Cancel an existing installation appointment");
                    // Add your logic here
                    break;
                case "0":
                    logger.log(Level.INFO, "Exiting the Admin Dashboard. Goodbye!");
                    adminFlag=false;
                    loggedIn=false;
                    break;
                default:
                    // Handle invalid input
                    logger.log(Level.INFO, "Invalid choice. Please choose a valid option.");
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
        logger.log(Level.INFO, "1. See all Categories  2. Add a new catalog 3. Edit an existing product category");
        logger.log(Level.INFO, "");
        logger.log(Level.INFO, "4. Delete an existing product category 5.See all product listing 6. Add a product listing ");
        logger.log(Level.INFO, "");
        logger.log(Level.INFO, "7. Update a product listing 8. View customer accounts 9. Search for a specific customer account");
        logger.log(Level.INFO, "");
        logger.log(Level.INFO, "10. Delete a customer account 11. Add a new customer account 12. Schedule a new installation appointment");
        logger.log(Level.INFO, "");
        logger.log(Level.INFO, "13. Update an existing installation appointment 14. Cancel an existing installation appointment");
        logger.log(Level.INFO, "");
        logger.log(Level.INFO, "0. Exit");

    }
    public void displayEnterYourValue(){
        logger.info(" ");
        logger.info("Please enter what you want");
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
    public static void readInputFromUser() {
        Scanner scanner = new Scanner(System.in);

        logger.info("Enter an ID (integer): ");
        id = scanner.nextInt();

        logger.info("Enter a Name (string): ");
        scanner.nextLine(); // Consume the newline character
        name = scanner.nextLine();

        logger.info("Enter a Description (string): ");
        description = scanner.nextLine();

        logger.info("Enter a Price (Integer): ");
        price = scanner.nextInt();

        logger.info("Enter a Category (string): ");
        scanner.nextLine(); // Consume the newline character
        category = scanner.nextLine();

        logger.info("Enter Availability (String): ");
        availability = scanner.nextLine();
    }

    public static void exit() {
        isExitPage =true;
        logger.info("Exit function called.");
    }

}

