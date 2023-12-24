package DevelopmentDrivenSteps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import my.backendproductioncode.AdminDashboard;
import my.backendproductioncode.Appointment;
import my.backendproductioncode.RegistrationService;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class AdminDashboardStepDefinitions {
    private final AdminDashboard adminDashboard = new AdminDashboard();
    private final RegistrationService registrationService = new RegistrationService();
    private boolean registeredUsers;
    private boolean printUserData;
    private boolean updatedUser=false;
    private boolean deletedUser=false;

    private String oldCategoryName;
    private String newCategoryName;
    boolean flag =false;
    private static int currentProductId=10;
    private String name;
    private String description;
    private double price;
    private String category;
    private String availability;

    private String registrationResult;
    public String appointment;
    @Given("the admin is on the dashboard")
    public void theAdminIsOnTheDashboard() {
        flag =true;
    }

    @When("the admin adds a new product category with name {string}")
    public void theAdminAddsANewProductCategoryWithName(String category) {
        adminDashboard.addProductCategory(category);
    }

    @Then("the product category {string} should be listed on the dashboard")
    public void theProductCategoryShouldBeListedOnTheDashboard(String expectedCategory) {
        List<String> productCategories = adminDashboard.getProductCategories();
        assert productCategories.contains(expectedCategory);
    }


    @Given("there is an existing product category with name {string}")
    public void thereIsAnExistingProductCategoryWithName(String categoryName) {

        List<String> productCategories = adminDashboard.getProductCategories();
        if (!productCategories.contains(categoryName)) {
            // If the category doesn't exist, you can handle it as needed.
            throw new RuntimeException("Category does not exist: " + categoryName);
        }

    }


    @When("the admin edits the product category with name {string} to {string}")
    public void theAdminEditsTheProductCategoryWithNameTo(String oldCategory, String newCategory) {
        // Perform the action of editing an existing product category.
        // In this example, we update the category name.
        adminDashboard.editProductCategory(oldCategory, newCategory);
        oldCategoryName = oldCategory;
        newCategoryName = newCategory;
    }

    @Then("the product category {string} should be edited on the dashboard")
    public void theProductCategoryShouldBeEditedOnTheDashboard(String ignoredExpectedCategory) {
        List<String> productCategories = adminDashboard.getProductCategories();
        assert productCategories.contains(newCategoryName);
        assert !productCategories.contains(oldCategoryName);
    }


    @When("the admin deletes the product category with name {string}")
    public void the_admin_deletes_the_product_category_with_name(String categoryName) {
        adminDashboard.deleteProductCategory(categoryName);
    }
    @Then("the product category {string} should not be listed on the dashboard")
    public void the_product_category_should_not_be_listed_on_the_dashboard(String deletedCategoryName) {
        // Write code here that turns the phrase above into concrete actions
        List<String> productCategories = adminDashboard.getProductCategories();
        assert !productCategories.contains(deletedCategoryName);
    }




@When("they add a new product with the following details:")
    public void theyAddANewProductWithTheFollowingDetails(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> productDetails = dataTable.asMaps();
        Map<String, String> details = productDetails.get(0);
         name = details.get("Name");
         description = details.get("Description");
         price = Double.parseDouble(details.get("Price"));
         category = details.get("Category");
         availability = details.get("Availability");
        currentProductId++;
        adminDashboard.addProduct(currentProductId, name, description,price, category, availability);
    }

    @Then("the product should be listed on the dashboard")
    public void productShouldBeListed() {
        assertTrue(adminDashboard.isProductListed(currentProductId));
    }

    @When("they update the product with the ID {int} with the following details:")
    public void updateProduct(int productId, io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> updatedDetails = dataTable.asMaps();
        Map<String, String> details = updatedDetails.get(0);
         name=details.get("Name");
         description = details.get("Description");
         price = Double.parseDouble(details.get("Price"));
         category = details.get("Category");
         availability = details.get("Availability");
         adminDashboard.updateProduct(productId,name,description, price, category, availability);
    }


    @Then("the product with the ID {int} should be updated on the dashboard")
    public void the_product_with_the_id_should_be_updated_on_the_dashboard(Integer int1) {
        assertTrue(adminDashboard.isProductUpdated(int1,name,description, price, category, availability));
    }


    @Given("the application is running")
    public void the_application_is_running() {
        flag = true;
    }

    @When("I enter the command {string}")
    public void i_enter_the_command(String command) {
        if (registrationService.isViewAccountsCommand(command)) {
            registrationService.printRegisteredUsers();
            registeredUsers=true;
        }
    }

    @Then("I should see a list of all customer accounts")
    public void i_should_see_a_list_of_all_customer_accounts() {
        Assert.assertTrue(registeredUsers);
    }

    @When("I enter the username {string}")
    public void i_enter_the_username(String string) {
        printUserData=registrationService.printRegisteredUser(string);
        if(printUserData){
            System.out.println(printUserData);
        }else {
            System.out.println(printUserData);
        }
    }

    @Then("I should see the details of the customer's account")
    public void i_should_see_the_details_of_the_customer_s_account() {
        Assert.assertTrue(printUserData);
    }

    @When("the user provides the following details for editing:")
    public void the_user_provides_the_following_details_for_editing(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> updatedUserDetails = dataTable.asMaps();
        Map<String, String> details = updatedUserDetails.get(0);
        String username=details.get("username");
        String email=details.get("email");
        String password=details.get("password");
        updatedUser=registrationService.updateUser(username,email,password);
    }

    @Then("the user should see an account update success message")
    public void the_user_should_see_an_account_update_success_message() {
        Assert.assertTrue(updatedUser);
    }

    @When("the user provides the following details for deletion:")
    public void the_user_provides_the_following_details_for_deletion(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> updatedUserDetails = dataTable.asMaps();
        Map<String, String> details = updatedUserDetails.get(0);
        String username=details.get("username");
        deletedUser=registrationService.deleteUser(username);
    }

    @Then("the user should see an account deletion success message")
    public void the_user_should_see_an_account_deletion_success_message() {
        Assert.assertTrue(deletedUser);

    }
    @When("the user provides the following details for add new user:")
    public void the_user_provides_the_following_details_for_add_new_user(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> registerNewUser = dataTable.asMaps();
        if (!registerNewUser.isEmpty()) {
            Map<String, String> details = registerNewUser.get(0);
            String username = details.get("username");
            String email = details.get("email");
            String password = details.get("password");
            String confirmPassword = details.get("confirm password");
            registrationResult = registrationService.registerUser(username, email, password, confirmPassword);
        }

    }
    @Then("the user should see a success message")
    public void the_user_should_see_a_success_message() {
        Assert.assertEquals("Registration successful", registrationResult);
    }



    @Given("I am logged in as an admin")
    public void i_am_logged_in_as_an_admin() {
        flag=true;
    }

    @When("I provide the appointment details as follows:")
    public void i_provide_the_appointment_details_as_follows(DataTable dataTable) {
        List<List<String>> appointmentData = dataTable.asLists(String.class);
        int appointmentId = Integer.parseInt(appointmentData.get(1).get(0));
        System.out.println(appointmentId);
        String customerName = appointmentData.get(1).get(1);
        String product = appointmentData.get(1).get(2);
        String scheduledDate = appointmentData.get(1).get(3);
        String scheduledTime = appointmentData.get(1).get(4);
        String status = appointmentData.get(1).get(5);

        Appointment appointment = new Appointment(appointmentId, customerName, product, scheduledDate, scheduledTime, status);
        System.out.println(appointment);

        this.appointment =adminDashboard.addAppointment(appointment);
    }
    @Then("I should see the new appointment in the list of installation appointments")
    public void i_should_see_the_new_appointment_in_the_list_of_installation_appointments() {
        Assert.assertEquals("Added successfully", appointment);
    }


    @When("I provide the updated appointment details as follows:")
    public void i_provide_the_updated_appointment_details_as_follows(DataTable dataTable) {
        List<List<String>> appointmentData = dataTable.asLists(String.class);
        int appointmentId = Integer.parseInt(appointmentData.get(1).get(0));
        String customerName = appointmentData.get(1).get(1);
        String product = appointmentData.get(1).get(2);
        String scheduledDate = appointmentData.get(1).get(3);
        String scheduledTime = appointmentData.get(1).get(4);
        String status = appointmentData.get(1).get(5);
        Appointment updatedAppointment = new Appointment(appointmentId, customerName, product, scheduledDate, scheduledTime, status);
        appointment=adminDashboard.updateAppointment(appointmentId, updatedAppointment);
    }

    @Then("I should see the updated appointment details in the list of installation appointments")
    public void i_should_see_the_updated_appointment_details_in_the_list_of_installation_appointments() {
        Assert.assertEquals("Updated successfully", appointment);
    }

    @When("I enter a {string} followed by the appointment ID in the console")
    public void i_enter_a_followed_by_the_appointment_id_in_the_console(String command) {
        int appointmentId = Integer.parseInt(command);
        appointment=adminDashboard.deleteAppointment(appointmentId);
    }

    @Then("The appointment should be removed from the list of installation appointments")
    public void the_appointment_should_be_removed_from_the_list_of_installation_appointments() {
        Assert.assertEquals("Deleted successfully", appointment);
    }



    boolean printCategories;
    @Given("the product categories are loaded")
    public void the_product_categories_are_loaded() {
        printCategories=false;
    }
    @When("the printProductCategories method is called")
    public void the_print_product_categories_method_is_called() {
        adminDashboard.printProductCategories();
        printCategories=true;
    }
    @Then("the product categories should be logged")
    public void the_product_categories_should_be_logged() {
            Assert.assertTrue(printCategories);
    }
}


