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
    RegistrationService registrationService;
    String delResult;
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
        // Use the assert() method to verify the registration result
        Assert.assertEquals("Password and confirm password do not match", registrationResult);
    }


    @Then("the user should see an error message indicating an invalid email address")
    public void theUserShouldSeeAnErrorMessageIndicatingAnInvalidEmailAddress() {
        // Use the assert() method to verify the registration result
        Assert.assertEquals("Invalid email address", registrationResult);
    }
    @Then("the user should see an error message indicating the username is already taken")
    public void the_user_should_see_an_error_message_indicating_the_username_is_already_taken() {
        Assert.assertEquals("Username already exists", registrationResult);
    }


    Boolean deletionResult;
    @Given("the user with username exists")
    public void the_user_with_username_exists() {
        registrationService = new RegistrationService();
    }
    @When("the admin deletes the user with username {string}")
    public void the_admin_deletes_the_user_with_username(String string) {
        deletionResult= registrationService.deleteUser(string);
        if(deletionResult){
            delResult="User Deleted Successfully";
        }else {
            delResult="User not found for deletion";
        }
    }
    @Then("the user with username should be deleted successfully")
    public void the_user_with_username_should_be_deleted_successfully() {
        if(deletionResult){
            Assert.assertEquals("User Deleted Successfully",delResult);
        }else {
            Assert.assertEquals("User not found for deletion",delResult);
        }
    }


}