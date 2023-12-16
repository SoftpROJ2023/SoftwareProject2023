package DevelopmentDrivenSteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import my.backendproductioncode.Purchase;
import my.backendproductioncode.RegistrationService;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class UserProfileStep {
    private final RegistrationService registrationService = new RegistrationService();
    private boolean updatedUser=false;
    Purchase purchase=new Purchase();
    boolean Orders=false;

    boolean flag=false;
    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        flag =true;
    }

    @When("the user navigates to the profile editing page:")
    public void the_user_navigates_to_the_profile_editing_page(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> updatedUserDetails = dataTable.asMaps();
        Map<String, String> details = updatedUserDetails.get(0);
        String username=details.get("username");
        String email=details.get("email");
        String password=details.get("password");
        updatedUser=registrationService.updateUser(username,email,password);
    }

    @Then("the user should see an account updated")
    public void the_user_should_see_an_account_updated() {
        Assert.assertTrue(updatedUser);
    }

    @When("the user navigates to the order history page")
    public void the_user_navigates_to_the_order_history_page() {
        Orders=purchase.printOrders();
    }

    @Then("the customer should see a list of previous orders on screen")
    public void the_customer_should_see_a_list_of_previous_orders_on_screen() {
        Assert.assertTrue(Orders);
    }

}
