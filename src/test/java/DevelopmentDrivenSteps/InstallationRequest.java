package DevelopmentDrivenSteps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;


public class InstallationRequest {

    @Given("the customer wants to request installation services")
    public void givenTheCustomerWantsToRequestInstallationServices() {
    }

    @When("the customer fills out the installation request form with the following details:")
    public void whenTheCustomerFillsOutTheInstallationRequestFormWithTheFollowingDetails(DataTable dataTable) {

    }

    @When("the installer is available on the preferred date")
    public void whenTheInstallerIsAvailableOnThePreferredDate() {
    }

    @When("the installer is not available on the preferred date")
    public void whenTheInstallerIsNotAvailableOnThePreferredDate() {
    }

    @Then("the installation request should be submitted successfully")
    public void thenTheInstallationRequestShouldBeSubmittedSuccessfully() {
    }

    @Then("the customer should receive a confirmation email")
    public void thenTheCustomerShouldReceiveAConfirmationEmail() {
    }

    @Then("the customer should be informed that the preferred date is not available")
    public void thenTheCustomerShouldBeInformedThatThePreferredDateIsNotAvailable() {
    }

    @Then("the customer should be prompted to choose an alternative date")
    public void thenTheCustomerShouldBePromptedToChooseAnAlternativeDate() {
    }

    @Then("the customer should be prompted to provide the missing details")
    public void thenTheCustomerShouldBePromptedToProvideTheMissingDetails() {
    }

    @Then("the installation request should not be submitted")
    public void thenTheInstallationRequestShouldNotBeSubmitted() {

    }

    @When("the customer has an existing installation request")
    public void whenTheCustomerHasAnExistingInstallationRequest() {
    }

    @When("the customer cancels the installation request")
    public void whenTheCustomerCancelsTheInstallationRequest() {
    }

    @Then("the installation request should be canceled")
    public void thenTheInstallationRequestShouldBeCanceled() {
    }

    @Then("the customer should receive a cancellation confirmation")
    public void thenTheCustomerShouldReceiveACancellationConfirmation() {
    }
}
