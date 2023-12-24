package my.backendproductioncode;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class HelperFunctions {
    AdminDashboard adminDashboard;
    List<Appointment> appointments;
    public HelperFunctions(AdminDashboard admin){
        this.adminDashboard=admin;
        appointments=adminDashboard.getAppointments();
    }
    public static final Logger logger = Logger.getLogger(HelperFunctions.class.getName());
    private static final String ACCEPTED = "Accepted";
    private static final String REJECTED = "Rejected";
    List<Appointment> modifiableAppointments= new ArrayList<>();

    public boolean logAppointments() {
        ArrayList<Integer> myArrayList = new ArrayList<>();
        try {
            for (Appointment appointment : modifiableAppointments) {
                int appointmentId = appointment.appointmentId();
                myArrayList.add(appointmentId);
                printAppointmentData(appointment);
            }
            for (Appointment lastAppointment : appointments) {
                int appointmentId = lastAppointment.appointmentId();
                if (myArrayList.contains(appointmentId)) {
                    continue;
                }
                printAppointmentData(lastAppointment);
            }
            return true; // Logging was successful
        } catch (Exception e) {
            logger.severe("Error logging appointments: " + e.getMessage());
            return false; // Logging encountered an error
        }

    }
    public boolean updateStatus(List<Appointment> appointments, int appointmentId, String newStatus) {
        modifiableAppointments = new ArrayList<>(appointments);
        List<Appointment> updatedAppointments = new ArrayList<>();
        boolean isUpdated = false;
        for (Appointment appointment : modifiableAppointments) {
            if (appointment.appointmentId() == appointmentId) {
                updatedAppointments.add(new Appointment(
                        appointment.appointmentId(),
                        appointment.customerName(),
                        appointment.product(),
                        appointment.scheduledDate(),
                        appointment.scheduledTime(),
                        newStatus
                ));
                isUpdated = true;
            } else {
                updatedAppointments.add(appointment);
            }
        }
        if (isUpdated) {
            modifiableAppointments.clear();
            modifiableAppointments.addAll(updatedAppointments);
        }
        return isUpdated;
    }
    public  void printAppointments() {
        for (Appointment appointment : modifiableAppointments) {
            if(appointment.status().equals(REJECTED)){
                logger.info("Your reservation has been declined");
            }
            if (appointment.status().equals(ACCEPTED)){
                logger.info("Your reservation has been accepted");
            }
        }
    }
    public void printAppointmentData(Appointment appointment){
        int appointmentId = appointment.appointmentId();
        String customerName = appointment.customerName();
        String product = appointment.product();
        String scheduledDate = appointment.scheduledDate();
        String scheduledTime = appointment.scheduledTime();
        String status = appointment.status();
        String result="Appointment ID: " + appointmentId +
                ", Customer Name: " + customerName +
                ", Product: " + product +
                ", Scheduled Date: " + scheduledDate +
                ", Scheduled Time: " + scheduledTime +
                ", Status: " + status;
        logger.info(result);
    }
}
