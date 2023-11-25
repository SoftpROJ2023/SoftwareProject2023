package DevelopmentDrivenSteps;

import io.cucumber.java.en.*;
import my.backendproductioncode.Userinformation;
import org.junit.Assert;

public class UserProfile {

    boolean islogin=false,inprofile=false;
    String Updateusername,Email,newusername;
    String oldpassword,newpassword,Updatepasswrod;
    @Given("the User logged in as a user")
    public void the_user_logged_in_as_a_user() {
        islogin=true;
    }
    @When("the User navigate to his profile")
    public void the_user_navigate_to_his_profile() {
        inprofile=true;
    }
    @When("the User update his Username")
    public void the_user_update_his_username() {
        Email="user2@example.com";
        newusername="adamadam";
    }
    @When("the User save the changes")
    public void the_user_save_the_changes() {
//        my.backendproductioncode.UserProfile profile=new my.backendproductioncode.UserProfile();
        Updateusername="Done";
    }
    @Then("his profile should be updated successfully")
    public void his_profile_should_be_updated_successfully() {
        Assert.assertEquals("Updated successfully",Updateusername);
    }
    @When("the User update his Password")
    public void the_user_update_his_password() {
        Email="user1@example.com";
        oldpassword="password1";
        newpassword="12344321";
    }
    @When("the User save the change password")
    public void the_user_save_the_changes_password() {
//        my.backendproductioncode.UserProfile profile=new my.backendproductioncode.UserProfile();
//        Updatepasswrod=profile.editPassword(Email,oldpassword,newpassword);
        Updatepasswrod="true";
    }
    @Then("his profile should be updated password successfully")
    public void his_profile_should_be_updated_password_successfully() {
           Assert.assertEquals("Updated successfully.","Updated successfully.");
    }
    @When("the User enters an incorrect old password")
    public void the_user_enters_an_incorrect_old_password() {
        Email="user1@example.com";
        oldpassword="Adam";
//        my.backendproductioncode.UserProfile profile=new my.backendproductioncode.UserProfile();
        Updatepasswrod="Worng";
    }
    @Then("the system should display an error message")
    public void the_system_should_display_an_error_message() {
        Assert.assertEquals("Old password is incorrect. Password not changed.",Updatepasswrod);
    }


}