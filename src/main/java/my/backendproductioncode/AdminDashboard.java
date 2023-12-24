package my.backendproductioncode;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminDashboard {
    public static final Logger logger = Logger.getLogger(AdminDashboard.class.getName());
    String interior="interior";
    String exterior="exterior";
    String electronics="electronics";
    String inStock="In Stock";
    String outOfStock="Out Of Stock";
    private final ArrayList<String> productCategories = new ArrayList<>(Arrays.asList(interior, exterior ,electronics));
    private final Map<Integer, Product> productMap = new HashMap<>();
    {
        productMap.put(1, new Product(1, "Car Alarms", "It is Car Alarms", 10.0, electronics, inStock));
        productMap.put(2, new Product(2, "GPS Navigation Devices", "It is GPS Navigation Devices", 150.0, electronics, outOfStock));
        productMap.put(3, new Product(3, "LED Headlights", "It is LED Headlights", 150.0, exterior, outOfStock));
        productMap.put(4, new Product(4, "Car Cover", "It is Car Cover", 150.0, exterior, inStock));
        productMap.put(5, new Product(5, "Seat Covers", "It is Seat Covers", 150.0, interior, inStock));
        productMap.put(6, new Product(6, "Car Audio System", "It is Car Audio System", 150.0, interior, outOfStock));
    }

    private final List<Appointment> appointments;
    private List<Appointment> storedAppointments = new ArrayList<>(); // Initialize storedAppointments
    List<Appointment> modifiableAppointments= new ArrayList<>();
    private static final String ACCEPTED = "Accepted";
    private static final String REJECTED = "Rejected";
    private static final String STILL_WAITING = "Waiting";

    public AdminDashboard() {
        this.appointments = new ArrayList<>();
        appointments.add(new Appointment(1, "Alice Smith", "Product A", "2023-10-25", "10:00 AM - 12:00 PM", REJECTED));
        appointments.add(new Appointment(2, "Bob Johnson", "Product B", "2023-10-26", "02:00 PM - 04:00 PM", ACCEPTED));
        appointments.add(new Appointment(3, "Charlie Brown", "Product C", "2023-10-27", "09:00 AM - 11:00 AM", STILL_WAITING));
    }

    public void addProductCategory(String category) {
        productCategories.add(category);
        String message = "Product Category " + category + " Added Successfully";
        // Log the message with INFO level
        logger.log(Level.INFO, message);
    }

    public List<String> getProductCategories() {
        return productCategories;
    }

    public void editProductCategory(String oldCategory, String newCategory) {
        int index = productCategories.indexOf(oldCategory);
        if (index != -1) {
            productCategories.set(index, newCategory);
        }
    }
    public void printProductCategories() {
        for (String category : productCategories) {
            logger.info(category);
        }
    }
    public void deleteProductCategory(String categoryName) {
        if (productCategories.contains(categoryName)) {
            productCategories.remove(categoryName);
            String message = "Product Category " + categoryName + " Deleted Successfully";
            logger.log(Level.INFO, message);
        } else {
            logger.warning( "Category does not exist");
        }

    }


    public Map<Integer, Product> getProductMap() {
        return productMap;
    }
    public void addProduct(int productId, String name,String description, double price, String category, String availability) {
        Product product = new Product(productId,name,description, price, category, availability);
        productMap.put(productId, product);
        for (Product product1 : productMap.values()) {
            String logMessage = String.format("ID: %s, Name: %s, Description: %s, Price: %s, Category: %s, Availability: %s",
                    product1.getProductId(),
                    product1.getName(),
                    product1.getDescription(),
                    product1.getPrice(),
                    product1.getCategory(),
                    product1.getAvailability()
            );
            logger.log(Level.INFO, logMessage);
        }

    }
    public int getProductByName(String name) {
        // Assuming productMap is a Map<String, Product> where String is the product ID
        for (Product product : productMap.values()) {
            if (product.getName().equals(name)) {

                return product.getProductId();
            }
        }
        // Handle the case where the product with the given name is not found in productMap
        logger.info("Product not found: " + name);
        return 0; // Or throw an exception or handle the case as appropriate
    }
    public String updateProduct(int productId, String name, String description, double price, String category, String availability) {
        if (productMap.containsKey(productId)) {
            Product product = productMap.get(productId);
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setCategory(category);
            product.setAvailability(availability);
            return "Product updated successfully";
        } else {
            return "Product with ID " + productId + " not found";
        }
    }

    public boolean isProductListed(int productId) {
        return productMap.containsKey(productId);
    }

    public boolean isProductUpdated(int productId, String name,String description, double price, String category, String availability) {
        if (productMap.containsKey(productId)) {
            Product product = productMap.get(productId);
            return Objects.equals(product.getName(), name)
                    && Objects.equals(product.getDescription(), description)
                    && product.getPrice() == price
                    && Objects.equals(product.getCategory(), category)
                    && Objects.equals(product.getAvailability(), availability);
        }
        return false;
    }

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
    public String  addAppointment(Appointment appointment) {
        for (Appointment existingAppointment : appointments) {
            if (hasTimeConflict(existingAppointment, appointment)) {
                return "Error: Time conflict with an existing appointment.";
            }
        }
        appointments.add(appointment);
        storedAppointments = storeAppointment(appointment); // Update storedAppointments
        return "Added successfully";
    }
    private List<Appointment> storeAppointment(Appointment appointment) {
        storedAppointments.add(appointment);
        return new ArrayList<>(storedAppointments); // Return a copy of storedAppointments
    }
    public void storeAppointment() {
        for (Appointment storedAppointment : storedAppointments) {
            logger.info(storedAppointment.toString());
        }
    }
    public Appointment getAppointmentById(int id) {
        for (Appointment appointment : appointments) {
            if (appointment.appointmentId() == id) {
                return appointment;
            }
        }
        return null; // Return null if no appointment with the specified ID is found
    }
    @SuppressWarnings("all")

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
    public int lengthOfStoredAppointment(){
        return  storedAppointments.size();
    }
    public String updateAppointment(int appointmentId, Appointment updatedAppointment) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).appointmentId() == appointmentId) {
                appointments.set(i, updatedAppointment);
                return "Updated successfully";
            }
        }
        return "Appointment not found"; // Return a message indicating that the appointment was not found.
    }


    public String deleteAppointment(int appointmentId) {
        appointments.removeIf(appointment -> appointment.appointmentId() == appointmentId);
        return "Deleted successfully";
    }
    // Function to check if there is a time conflict between two appointments
    private static boolean hasTimeConflict(Appointment existingAppointment, Appointment newAppointment) {
        String existingStartTime = existingAppointment.scheduledTime().split(" - ")[0];
        String existingEndTime = existingAppointment.scheduledTime().split(" - ")[1];

        String newStartTime = newAppointment.scheduledTime().split(" - ")[0];
        String newEndTime = newAppointment.scheduledTime().split(" - ")[1];

        return existingStartTime.equals(newStartTime) || existingEndTime.equals(newEndTime) ||
                (existingStartTime.compareTo(newStartTime) < 0 && existingEndTime.compareTo(newStartTime) > 0) ||
                (existingStartTime.compareTo(newEndTime) < 0 && existingEndTime.compareTo(newEndTime) > 0);
    }
    @SuppressWarnings("all")
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
}
