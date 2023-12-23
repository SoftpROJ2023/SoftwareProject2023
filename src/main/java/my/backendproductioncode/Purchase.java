package my.backendproductioncode;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Purchase {
     List<Product> cart;
     List <Product> order;
     String inStock="In Stock";
    private static final Logger logger = Logger.getLogger(Purchase.class.getName());
    public Purchase() {
        cart = new ArrayList<>();
        order=new ArrayList<>();
        Product initialProduct1 = new Product(1, "InitialProduct1", "Description 1", 10.0, "Category1", inStock);
        Product initialProduct2 = new Product(2, "InitialProduct2", "Description 2", 15.0, "Category2", inStock);
        cart.add(initialProduct1);
        cart.add(initialProduct2);
    }
    public boolean addProductToCart(Product product) {
            logger.info("Your purchase was completed successfully");
            cart.add(product);
            order.add(product);
            return true; // Product added to cart successfully

    }
    public  boolean printOrders() {
            logger.info("Purchased products");
            for (Product product : order) {
                logger.info("-" + product.getName());
            }
            return true;
    }
    public int lengthOfOrders(){
        return  order.size();
    }
}
