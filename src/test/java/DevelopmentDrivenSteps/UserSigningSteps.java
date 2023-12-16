package DevelopmentDrivenSteps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import my.backendproductioncode.SignInServices;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class UserSigningSteps {

    boolean isOnTheSignIn=false;
    String signInResult;

    @Given("the user is on the sign-in page")
    public void the_user_is_on_the_sign_in_page() {
        isOnTheSignIn=true;
    }
    @When("the user provides the following details for sign-in:")
    public void the_user_provides_the_following_details_for_sign_in(DataTable dataTable) {
        List<Map<String, String>> registrationDataList = dataTable.asMaps(String.class, String.class);
        String username1 = registrationDataList.get(0).get("username");
        String password1 = registrationDataList.get(0).get("password");
        SignInServices userSignIn=new SignInServices();
        signInResult =userSignIn.signInUser(username1,password1);
        System.out.println(signInResult);
    }

    @Then("the user should be successfully signed in")
    public void the_user_should_be_successfully_signed_in() {
        Assert.assertEquals("Password is valid. User can be signed in.", signInResult);

    }

    @Then("the user should see an error message")
    public void the_user_should_see_an_error_message() {
        Assert.assertEquals("User not found. User cannot be signed in.", signInResult);

    }


}
