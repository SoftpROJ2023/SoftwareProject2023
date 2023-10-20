package my.backendproductioncode;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminDashboard {
    private final ArrayList<String> productCategories = new ArrayList<>(Arrays.asList("Electronics", "External"));
    public static final Logger logger = Logger.getLogger(AdminDashboard.class.getName());
    private final Map<Integer, Product> productMap = new HashMap<>();
    {
        productMap.put(1, new Product(1, "AA", "Product 1", 10.0, "ele", "true"));

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



    public boolean updateProduct(int productId,String name, String description, double price, String category, String availability) {
        if (productMap.containsKey(productId)) {
            Product product = productMap.get(productId);
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setCategory(category);
            product.setAvailability(availability);
            return true;
        }
        return false;
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
}
