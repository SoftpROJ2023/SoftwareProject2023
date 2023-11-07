package my.backendproductioncode;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Purchase {
     List<Product> cart;
    private static final Logger logger = Logger.getLogger(Purchase.class.getName());
    public Purchase() {
        cart = new ArrayList<>();
        Product initialProduct1 = new Product(1, "InitialProduct1", "Description 1", 10.0, "Category1", "In Stock");
        Product initialProduct2 = new Product(2, "InitialProduct2", "Description 2", 15.0, "Category2", "In Stock");
        cart.add(initialProduct1);
        cart.add(initialProduct2);
    }
    public boolean addProductToCart(Product product) {
        if (product.getAvailability().equals("In Stock")) {
            logger.info("Your purchase was completed successfully");
            cart.add(product);
            return true; // Product added to cart successfully
        } else {
            logger.info("The purchase failed");
            return false; // Product is not in stock
        }
    }
    public   boolean printOrders() {
        try {
            logger.info("Purchased products");
            for (Product product : cart) {
                logger.info("-" + product.getName());
            }
            return true;
        } catch (Exception e) {
            logger.severe("Error while logging cart contents: " + e.getMessage());
            return false;
        }
    }
}
