package my.backendproductioncode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ProductCatalog {
    public static final Logger logger = Logger.getLogger(ProductCatalog.class.getName());

    AdminDashboard adminDashboard = new AdminDashboard();
    List<String> categories = adminDashboard.getProductCategories();
    Map<Integer, Product> products = adminDashboard.getProductMap();

    public boolean printProductCategories() {
        for (String category : categories) {
            logger.info(category);
        }
        return true;
    }

    public boolean getProductsRelatedToSpecificCategory(String categoryName) {
        String desiredCategory = categoryName;
        boolean foundProducts = false; // Initialize a flag

        for (Map.Entry<Integer, Product> entry : products.entrySet()) {
            Product product = entry.getValue();
            if (product.getCategory().equalsIgnoreCase(desiredCategory)) {
                logger.info("Product Name: " + product.getName());
                foundProducts = true; // Set the flag to true if products are found
            }
        }
        return foundProducts; // Return whether products were found
    }

    public boolean printAllProductData() {
        boolean foundProducts = false; // Initialize a flag
        for (Map.Entry<Integer, Product> entry : products.entrySet()) {
            Product product = entry.getValue();
            logger.info("Name: " + product.getName());
            logger.info("Description: " + product.getDescription());
            logger.info("Price: " + product.getPrice());
            logger.info("Category: " + product.getCategory());
            logger.info("Availability: " + product.getAvailability());
            foundProducts = true; // Set the flag to true as we found products
        }
        return foundProducts; // Return whether products were found
    }

    public boolean searchProductByNameAndPrintDetails(String productName) {
        boolean foundProduct = false; // Initialize a flag
        for (Product product : products.values()) {
            if (product.getName().equalsIgnoreCase(productName)) {
                logger.info("Product found:");
                logger.info("Name: " + product.getName());
                logger.info("Description: " + product.getDescription());
                logger.info("Price: " + product.getPrice());
                logger.info("Category: " + product.getCategory());
                logger.info("Availability: " + product.getAvailability());

                foundProduct = true; // Set the flag to true as we found the product
                break; // No need to continue searching once a match is found
            }
        }

        return foundProduct; // Return whether the product was found
    }

    public boolean filterProductsByAvailabilityAndPrintProductNames(String availability) {
        List<Product> inStockProducts = new ArrayList<>();

        for (Product product : products.values()) {
            if (product.getAvailability().equalsIgnoreCase(availability)) {
                inStockProducts.add(product);
            }
        }
        if (!inStockProducts.isEmpty()) {
            logger.info("Products in stock:");
            for (Product product : inStockProducts) {
                logger.info("Product Name: " + product.getName());
            }
            return true; // Products in stock were found
        } else {
            logger.warning("No products are currently in stock.");
            return false; // No products in stock were found
        }
    }
}

