package DevelopmentDrivenSteps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import my.backendproductioncode.RegistrationService;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public  class UserRegistrationSteps {
    boolean inRegPage=false;
    String registrationResult;

    @Given("the user is on the registration page")
    public void theUserIsOnTheRegistrationPage() {
        inRegPage=true;
    }

    @When("the user provides the following details for registration:")
    public void theUserProvidesTheFollowingDetailsForRegistration(DataTable dataTable) {
        List<Map<String, String>> registrationDataList = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> registrationData : registrationDataList) {
            String username = registrationData.get("username");
            String email = registrationData.get("email");
            String password = registrationData.get("password");
            String confirmPassword = registrationData.get("confirm password");
            RegistrationService registrationService = new RegistrationService();
            registrationResult = registrationService.registerUser(username, email, password, confirmPassword);
        }
    }

    @Then("the user should see a registration success message")
    public void theUserShouldSeeARegistrationSuccessMessage() {
        // Use the assert() method to verify the registration result
        Assert.assertEquals("Registration successful", registrationResult);
    }

    @Then("the user should see an error message indicating password mismatch")
    public void theUserShouldSeeAnErrorMessageIndicatingPasswordMismatch() {
        Assert.assertEquals("Password and confirm password do not match", registrationResult);
    }


    @Then("the user should see an error message indicating an invalid email address")
    public void theUserShouldSeeAnErrorMessageIndicatingAnInvalidEmailAddress() {
        Assert.assertEquals("Invalid email address", registrationResult);
    }

}