package DevelopmentDrivenSteps;

import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NotificationsStep {

    private boolean orderPlaced;
    private boolean orderConfirmed;
    private boolean emailSent;
    private boolean installerNotified;

    @Given("the customer has placed an order")
    public void the_customer_has_placed_an_order() {
        orderPlaced = true;
    }

    @When("the order is confirmed")
    public void the_order_is_confirmed() {
        if (orderPlaced) {
            orderConfirmed = true;
        } else {
            // Handle the case where the order hasn't been placed yet
        }
    }

    @Then("the customer should receive an email with the order confirmation")
    public void the_customer_should_receive_an_email_with_the_order_confirmation() {
        if (orderConfirmed) {
            // Implement code to send an email to the customer
            emailSent = true;
            Assert.assertTrue("Email should be sent to the customer", emailSent);
        } else {
            // Handle the case where the order is not confirmed
            Assert.fail("Order is not confirmed");
        }
    }

    @Given("there is a new installation request")
    public void there_is_a_new_installation_request() {
        // Implement code to create a new installation request
    }

    @When("the installer is notified")
    public void the_installer_is_notified() {
        // Implement code to notify the installer about the new installation request
        installerNotified = true;
    }

    @Then("the installer should receive information about the new installation request")
    public void the_installer_should_receive_information_about_the_new_installation_request() {
        if (installerNotified) {
            // Implement code to provide information to the installer
            Assert.assertTrue("Installer should be notified", installerNotified);
        } else {
            // Handle the case where the installer is not notified
            Assert.fail("Installer is not notified");
        }
    }
}
