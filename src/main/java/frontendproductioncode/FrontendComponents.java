package frontendproductioncode;

import my.backendproductioncode.*;

import java.util.Collections;
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
    HelperFunctions helperFunctions;
    ProductCatalog productCatalogs;
    Purchase purchase;
    Appointment appointment;
    static boolean isExitPage = false;
    boolean loggedIn;
    boolean adminFlag = false;
    boolean userFlag = false;
    boolean installerFlag = false;

    boolean isInUserDashboard=false;
    String message="Please Enter Your Choice:";
    //data for appointment
    int appointmentId ;
    String customerName;
    String product;
    String scheduledDate;
    String scheduledTime;
    String status;

    public FrontendComponents(){
        registrationService = new RegistrationService();
        signInService = new SignInServices();
        admin=new AdminDashboard();
        productCatalogs=new ProductCatalog(admin);
        helperFunctions=new HelperFunctions(admin);
        purchase=new Purchase();
    }


    public void frontendView() {
        loggedIn=false;
        while (!isExitPage) {
            if (!loggedIn) {
                handleWelcomePage();
            } else if ( userFlag) {
                handleUserDashboard();
            } else if (adminFlag) {
                handleAdminDashboard();
            }
            else if(installerFlag){
                handleInstallerPage();
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
                    break;
                case "2":
                    signIn();
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
            switch (choice) {
                case "1":
                    seeAllCategories();
                    break;
                case "2":
                    searchProductByCategory(scanner);
                    break;
                case "3":
                    seeAllProducts();
                    break;
                case "4":
                    searchForProduct(scanner);
                    break;
                case "5":
                    filterProductsByAvailability(scanner);
                    break;
                case "6":
                    purchaseProduct(scanner);
                    break;
                case "7":
                    viewAllInstallationAppointments();
                    scheduleNewInstallationAppointment();
                    break;
                case "8":
                    seeAllOrders();
                    installationRequests();
                    break;
                case "9":
                    editUserProfile(scanner);
                    break;
                case "10":
                    helperFunctions.printAppointments();
                    break;
                case "11":
                    logOut();
                    break;
                default:
                    logger.log(Level.INFO, "Invalid choice. Please enter a valid option (1-8).");
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

            switch (choice) {
                case "1":
                    seeAllCategoriesAdmin();
                    break;
                case "2":
                    addNewCatalog(scanner);
                    break;
                case "3":
                    editExistingProductCategory(scanner);
                    break;
                case "4":
                    deleteExistingProductCategory(scanner);
                    break;
                case "5":
                    seeAllProductsAdmin();
                    break;
                case "6":
                    addProductListing();
                    break;
                case "7":
                    updateProductListing(scanner);
                    break;
                case "8":
                    viewCustomerAccounts();
                    break;
                case "9":
                    searchForCustomerAccount(scanner);
                    break;
                case "10":
                    deleteCustomerAccount(scanner);
                    break;
                case "11":
                    addNewCustomerAccount();
                    break;
                case "12":
                    scheduleNewInstallationAppointment();
                    break;
                case "13":
                    viewAllInstallationAppointments();
                    break;
                case "14":
                    updateExistingInstallationAppointment(scanner);
                    break;
                case "15":
                    cancelExistingInstallationAppointment(scanner);
                    break;
                case "0":
                    logger.log(Level.INFO, "Exiting the Admin Dashboard. Goodbye!");
                    adminFlag = false;
                    loggedIn = false;
                    installerFlag=false;
                    break;
                default:
                    logger.log(Level.INFO, "Invalid choice. Please choose a valid option.");
            }
        }
    }
    private void handleInstallerPage(){
        displayInstallerDashboard();
        Scanner scanner = new Scanner(System.in);
        logger.info(message);
        if (scanner.hasNextLine()) {
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    admin.storeAppointment();
                    break;
                case "2":
                    updateAppointmentStatus(scanner);
                    break;
                case "0":
                    logger.log(Level.INFO, "Exiting The Installer Dashboard. Goodbye!");
                    adminFlag = false;
                    loggedIn = false;
                    installerFlag=false;
                    break;
                default:
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
        logger.log(Level.INFO, "1. See all categories");
        logger.log(Level.INFO, "2. Search for Product related to a specific category");
        logger.log(Level.INFO, "3. See all products");
        logger.log(Level.INFO, "4. Search for a product by name");
        logger.log(Level.INFO, "5. Filter products by availability (In Stock, Out Of Stock)");
        logger.log(Level.INFO, "6. Purchase a product");
        logger.log(Level.INFO, "7. Installation requests.");
        logger.log(Level.INFO, "8. View order history and installation requests");
        logger.log(Level.INFO, "9. Edit Your Profile");
        logger.log(Level.INFO, "10. Notifications");
        logger.log(Level.INFO, "11. Log out");
    }

    public void displayAdminDashboard(){
        logger.log(Level.INFO, "Welcome to Admin dashboard");
        logger.log(Level.INFO, "1. See all Categories  2. Add a new catalog 3. Edit an existing product category");
        logger.log(Level.INFO, "4. Delete an existing product category 5.See all product listing 6. Add a product listing ");
        logger.log(Level.INFO, "7. Update a product listing 8. View customer accounts 9. Search for a specific customer account");
        logger.log(Level.INFO, "10. Delete a customer account 11. Add a new customer account 12. Schedule a new installation appointment");
        logger.log(Level.INFO, "13. View All installation appointments 14. Update an existing installation appointment 15. Cancel an existing installation appointment");
        logger.log(Level.INFO, "0. Exit");
    }
    public void displayInstallerDashboard() {
        logger.log(Level.INFO, "Welcome to installer dashboard");
        logger.log(Level.INFO, "1. Notifications: View installation requests");
        logger.log(Level.INFO, "2. Schedule Appointments");
        logger.log(Level.INFO, "0. Log out");
    }
    public void displayEnterYourValue(){
        logger.info(" ");
        logger.info("Please enter your value");
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
        logger.info("Password: ");
        String password = scanner.nextLine();
        loggedIn=signInService.signInUser(registrationService,username,password);
        if(loggedIn){
            if (username.equals("Admin")) {
                adminFlag = true; // Set adminFlag to true if the username is "Admin"
            } else if (username.equals("Installer")) {
                installerFlag=true;
            } else {
                userFlag=true;
            }
        }

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
    public  Appointment readInputForAppointment() {
        Scanner scanner = new Scanner(System.in);
        logger.info("Enter an Appointment ID (integer): ");
        appointmentId = scanner.nextInt();

        logger.info("Enter Customer Name (string): ");
        scanner.nextLine(); // Consume the newline character
        customerName = scanner.nextLine();

        logger.info("Enter Product (string): ");
        product = scanner.nextLine();

        logger.info("Enter Scheduled Date (string): ");
        scheduledDate = scanner.nextLine();

        logger.info("Enter Scheduled Time (string): ");
        scheduledTime = scanner.nextLine();

        logger.info("Enter Status (string): ");
        status = scanner.nextLine();
        return new Appointment(appointmentId, customerName, product, scheduledDate, scheduledTime, status);
    }
    public static void exit() {
        isExitPage =true;
        logger.info("Exit function called.");
    }
    private void seeAllCategories( ) {
        logger.log(Level.INFO, "You selected 'See all categories'");
        productCatalogs.printProductCategories();
        isInUserDashboard = true;
    }

    private void searchProductByCategory(Scanner scanner) {
        logger.log(Level.INFO, "You selected 'Search for Product related to a specific category'");
        displayEnterYourValue();
        String search = scanner.nextLine();
        productCatalogs.getProductsRelatedToSpecificCategory(search);
    }

    private void seeAllProducts( ) {
        logger.log(Level.INFO, "You selected 'See all products'");
        productCatalogs.printAllProductData();
    }

    private void searchForProduct(Scanner scanner) {
        logger.log(Level.INFO, "You selected 'Search for a product'");
        displayEnterYourValue();
        String search = scanner.nextLine();
        productCatalogs.searchProductByNameAndPrintDetails(search);
    }

    private void filterProductsByAvailability(Scanner scanner) {
        logger.log(Level.INFO, "You selected 'Filter products by availability'");
        displayEnterYourValue();
        String search = scanner.nextLine();
        productCatalogs.filterProductsByAvailabilityAndPrintProductNames(search);
    }

    private void purchaseProduct(Scanner scanner) {
        logger.log(Level.INFO, "You selected 'Purchase a product'");
        displayEnterYourValue();
        String search = scanner.nextLine();
        purchase.addProductToCart(productCatalogs.getProduct(search));
    }

    private void seeAllOrders( ) {
        logger.log(Level.INFO, "You selected View order history and installation requests");
        purchase.printOrders();
        if (purchase.lengthOfOrders() <1){
            logger.log(Level.INFO, "You have not made any purchases yet");
        }
    }
    private void installationRequests( ) {
        admin.storeAppointment();
        if (admin.lengthOfStoredAppointment() <1){
            logger.log(Level.INFO, "You have not made any request yet");
        }
    }
    private void logOut( ) {
        logger.log(Level.INFO, "Logging out...");
        loggedIn = false;
        userFlag = false;
    }
    private void editUserProfile(Scanner scanner){
        logger.info("Please enter new information");
        String usernameToUpdate=scanner.nextLine();
        String newEmail=scanner.nextLine();
        String newPassword=scanner.nextLine();
        boolean updateSuccessful = registrationService.updateUser(usernameToUpdate, newEmail, newPassword);
        if (updateSuccessful) {
            logOut();
        } else {
            logger.info("User not found or update failed.");
            logOut();
        }
    }

    private void seeAllCategoriesAdmin() {
        logger.log(Level.INFO, "You chose see all categories");
        admin.printProductCategories();
    }

    private void addNewCatalog(Scanner scanner) {
        logger.log(Level.INFO, "You chose to Add a new catalog");
        displayEnterYourValue();
        String addCat = scanner.nextLine();
        admin.addProductCategory(addCat);
    }

    private void editExistingProductCategory(Scanner scanner) {
        logger.log(Level.INFO, "You chose to Edit an existing product category");
        logger.info("You Category");
        String addCat = scanner.nextLine();
        logger.info("New Category Name");
        String newName = scanner.nextLine();
        admin.editProductCategory(addCat, newName);
    }

    private void deleteExistingProductCategory(Scanner scanner) {
        logger.log(Level.INFO, "You chose to Delete an existing product category");
        displayEnterYourValue();
        String addCat = scanner.nextLine();
        admin.deleteProductCategory(addCat);
    }

    private void seeAllProductsAdmin() {
        logger.log(Level.INFO, "You chose see all products");
        productCatalogs.printAllProductData();
    }

    private void addProductListing( ) {
        logger.log(Level.INFO, "You chose to Add a product listing");
        readInputFromUser();
        admin.addProduct(id, name, description, price, category, availability);
    }

    private void updateProductListing(Scanner scanner) {
        logger.log(Level.INFO, "You chose to Update a product listing");
        logger.info("Enter the name of the product whose data you want to modify");
        String productNameForEdit = scanner.nextLine();
        int returnProduct = admin.getProductByName(productNameForEdit);
        if (returnProduct == 0) {
            String productNotFound="Product not found: " + productNameForEdit;
            logger.info(productNotFound);
            return;
        }
            String productModify="The product whose information you want to modify already exists, and This is the ID of the product should be entered in Id " + returnProduct;
            logger.info(productModify);
            readInputFromUser();
            String resultUpdated=admin.updateProduct(id, name, description, price, category, availability);
            logger.info(resultUpdated);



    }

    private void viewCustomerAccounts() {
        logger.log(Level.INFO, "You chose to View customer accounts");
        registrationService.printRegisteredUsers();
    }

    private void searchForCustomerAccount(Scanner scanner) {
        logger.log(Level.INFO, "You chose to Search for a specific customer account");
        displayEnterYourValue();
        String addCat = scanner.nextLine();
        registrationService.printRegisteredUser(addCat);
    }

    private void deleteCustomerAccount(Scanner scanner) {
        logger.log(Level.INFO, "You chose to Delete a customer account");
        displayEnterYourValue();
        String addCat = scanner.nextLine();
        registrationService.deleteUser(addCat);
    }

    private void addNewCustomerAccount() {
        logger.log(Level.INFO, "You chose to Add a new customer account");
        register();
    }

    private void scheduleNewInstallationAppointment( ) {
        appointment = readInputForAppointment();
        logger.info(admin.addAppointment(appointment));
    }

    private void viewAllInstallationAppointments() {
        helperFunctions.logAppointments();
    }

    private void updateExistingInstallationAppointment(Scanner scanner) {
        displayEnterYourValue();
        int idApp = scanner.nextInt();
        appointment = readInputForAppointment();
        String resultUpdatedAppointment=admin.updateAppointment(idApp, appointment);
        logger.info(resultUpdatedAppointment);
    }

    private void cancelExistingInstallationAppointment(Scanner scanner) {
        displayEnterYourValue();
        int idApp = scanner.nextInt();
        logger.info(admin.deleteAppointment(idApp));
    }
    private  void updateAppointmentStatus(Scanner scanner){
        helperFunctions.logAppointments();
        logger.info("Please select the appointment ID whose status you want to modify");
        int idAppointment = scanner.nextInt();
        if (idAppointment ==0){
            return;
        }
        scanner.nextLine(); // Consume the newline character
        Appointment appointments=admin.getAppointmentById(idAppointment);
        logger.info("Please enter the new status for appointment");
        String statusAppointment = scanner.nextLine();
        boolean newStatus=helperFunctions.updateStatus(Collections.singletonList(appointments),idAppointment,statusAppointment);
        if(newStatus){
            logger.info("Your status adjustment request has been successfully accepted");
        }else{
            logger.info("The request to modify the status was rejected. Verify the data entered");
        }
    }

}
