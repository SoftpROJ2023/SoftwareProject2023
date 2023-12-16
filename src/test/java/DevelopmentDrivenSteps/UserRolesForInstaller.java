package DevelopmentDrivenSteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import my.backendproductioncode.AdminDashboard;
import my.backendproductioncode.Appointment;
import org.junit.Assert;

import java.util.List;

public class UserRolesForInstaller {
    AdminDashboard admin=new AdminDashboard();
    boolean isAccessList=false;
    boolean isLogged=false;
    public String appointment;

    @Given("that the user is logged in as an installer")
    public void that_the_user_is_logged_in_as_an_installer() {
        isLogged=true;
    }

    @When("the installer accesses the installation request list")
    public void the_installer_accesses_the_installation_request_list() {
                isAccessList=admin.logAppointments();
    }

    @Then("the installer should be able to view a list of pending installations")
    public void the_installer_should_be_able_to_view_a_list_of_pending_installations() {
        Assert.assertTrue(isAccessList);
    }

    @When("I provide the following appointment details:")
    public void i_provide_the_following_appointment_details(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> appointmentData = dataTable.asLists(String.class);
        int appointmentId = Integer.parseInt(appointmentData.get(1).get(0));
        String customerName = appointmentData.get(1).get(1);
        String product = appointmentData.get(1).get(2);
        String scheduledDate = appointmentData.get(1).get(3);
        String scheduledTime = appointmentData.get(1).get(4);
        String status = appointmentData.get(1).get(5);

        Appointment appointment = new Appointment(appointmentId, customerName, product, scheduledDate, scheduledTime, status);
        this.appointment =admin.addAppointment(appointment);
    }

    @Then("You should observe the new appointment listed among the installation appointments.")
    public void you_should_observe_the_new_appointment_listed_among_the_installation_appointments() {
        Assert.assertEquals("Added successfully", appointment);
    }

}
