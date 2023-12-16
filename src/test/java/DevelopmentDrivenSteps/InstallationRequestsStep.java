package DevelopmentDrivenSteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import my.backendproductioncode.AdminDashboard;
import my.backendproductioncode.Appointment;
import org.junit.Assert;

import java.util.List;

public class InstallationRequestsStep {
    private final AdminDashboard adminDashboard = new AdminDashboard();
    public String appointment;

    boolean userLogged=false;
    @Given("I am authenticated as a user")
    public void i_am_authenticated_as_a_user() {
        userLogged=true;
    }
    @When("I input the appointment details through the CLI:")
    public void i_input_the_appointment_details_through_the_cli(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> appointmentData = dataTable.asLists(String.class);
        int appointmentId = Integer.parseInt(appointmentData.get(1).get(0));
        System.out.println(appointmentId);
        String customerName = appointmentData.get(1).get(1);
        String product = appointmentData.get(1).get(2);
        String scheduledDate = appointmentData.get(1).get(3);
        String scheduledTime = appointmentData.get(1).get(4);
        String status = appointmentData.get(1).get(5);

        Appointment appointment = new Appointment(appointmentId, customerName, product, scheduledDate, scheduledTime, status);
        this.appointment =adminDashboard.addAppointment(appointment);
    }
    @Then("the new appointment should be visible in my scheduled installations")
    public void the_new_appointment_should_be_visible_in_my_scheduled_installations() {
        Assert.assertEquals("Added successfully", appointment);
    }

}
