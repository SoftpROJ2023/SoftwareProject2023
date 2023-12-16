package DevelopmentDrivenSteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import my.backendproductioncode.AdminDashboard;
import my.backendproductioncode.RegistrationService;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class UserRolesForAdmin {
    private final AdminDashboard adminDashboard = new AdminDashboard();
    private final RegistrationService registrationService = new RegistrationService();

    boolean isAdmin=false;
    String registrationResult;
    private static int currentProductId=50;
    String newCategoryName;
    String oldCategoryName;

    @Given("the user is logged in as an admin")
    public void the_user_is_logged_in_as_an_admin() {
        isAdmin=true;
    }

    @When("the admin adds a new product with the following details:")
    public void the_admin_adds_a_new_product_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> productDetails = dataTable.asMaps();
        Map<String, String> details = productDetails.get(0);
        String name = details.get("Name");
        String description = details.get("Description");
        double price = Double.parseDouble(details.get("Price"));
        String category = details.get("Category");
        String availability = details.get("Availability");
        currentProductId++;
        adminDashboard.addProduct(currentProductId, name, description,price, category, availability);
    }

    @Then("the product should be added successfully")
    public void the_product_should_be_added_successfully() {
        assertTrue(adminDashboard.isProductListed(currentProductId));
    }

    @When("the admin updates the category with the following details:")
    public void the_admin_updates_the_category_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> categoryDetails = dataTable.transpose().asMap(String.class, String.class);
         oldCategoryName = categoryDetails.get("Old Category Name");
         newCategoryName = categoryDetails.get("New Category Name");
         adminDashboard.editProductCategory(oldCategoryName, newCategoryName);
    }

    @Then("the category should be updated successfully")
    public void the_category_should_be_updated_successfully() {
        List<String> productCategories = adminDashboard.getProductCategories();
        assert productCategories.contains(newCategoryName);
        assert !productCategories.contains(oldCategoryName);
    }

    @When("the admin provides the following details for user registration:")
    public void the_admin_provides_the_following_details_for_user_registration(io.cucumber.datatable.DataTable dataTable) {
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

    @Then("the user should see success message")
    public void the_user_should_see_success_message() {
        Assert.assertEquals("Registration successful", registrationResult);
    }


}
