package my.backendproductioncode;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminDashboard {
    public static final Logger logger = Logger.getLogger(AdminDashboard.class.getName());
    private final ArrayList<String> productCategories = new ArrayList<>(Arrays.asList("Electronics", "External"));
    private final Map<Integer, Product> productMap = new HashMap<>();
    {
        productMap.put(1, new Product(1, "AA", "Product 1", 10.0, "ele", "true"));
    }
    private final List<Appointment> appointments;
    private static final String SCHEDULED = "Scheduled";
    private static final String UNSCHEDULED = "UnScheduled";

    public AdminDashboard() {
        this.appointments = new ArrayList<>();
        appointments.add(new Appointment(1, "Alice Smith", "Product A", "2023-10-25", "10:00 AM - 12:00 PM", SCHEDULED));
        appointments.add(new Appointment(2, "Bob Johnson", "Product B", "2023-10-26", "02:00 PM - 04:00 PM", UNSCHEDULED));
        appointments.add(new Appointment(3, "Charlie Brown", "Product C", "2023-10-27", "09:00 AM - 11:00 AM", SCHEDULED));
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

    public void deleteProductCategory(String categoryName) {
        if (productCategories.contains(categoryName)) {
            productCategories.remove(categoryName);
            String message = "Product Category " + categoryName + " Deleted Successfully";
            logger.log(Level.INFO, message);
        } else {
            logger.warning( "Category does not exist");
        }

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

    public void updateProduct(int productId, String name, String description, double price, String category, String availability) {
        if (productMap.containsKey(productId)) {
            Product product = productMap.get(productId);
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setCategory(category);
            product.setAvailability(availability);
        }
    }

    public boolean isProductListed(int productId) {
        return productMap.containsKey(productId);
    }

    public boolean isProductUpdated(int productId, String name,String description, double price, String category, String availability) {
        if (productMap.containsKey(productId)) {
            Product product = productMap.get(productId);
            return product.getName().equals(name)
                    &&product.getDescription().equals(description)
                    && product.getPrice() == price
                    && product.getCategory().equals(category)
                    && product.getAvailability().equals(availability);
        }
        return false;
    }

    public String  addAppointment(Appointment appointment) {
        appointments.add(appointment);
        return "Added successfully";
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

}
