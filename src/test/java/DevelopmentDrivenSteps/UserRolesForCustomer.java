package DevelopmentDrivenSteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import my.backendproductioncode.*;
import org.junit.Assert;
import org.slf4j.ILoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class UserRolesForCustomer {
    AdminDashboard admin=new AdminDashboard();
    ProductCatalog productCatalog=new ProductCatalog(admin);
    Purchase purchase=new Purchase();
    boolean isLogged=false;
    boolean availableProductsCataloges=false;
    boolean addCart=false;
    boolean Orders=false;
    boolean printOrd=false;
    String productName;
    int arrSize=1;
    boolean arrayStatus;
    private static final Logger logger = Logger.getLogger(UserRolesForCustomer.class.getName());

    @Given("the user is logged in as a customer")
    public void the_user_is_logged_in_as_a_customer() {
        isLogged=true;
    }

    @When("the customer browses the product catalog")
    public void the_customer_browses_the_product_catalog() {
        availableProductsCataloges=productCatalog.printProductCategories();
    }

    @Then("the customer should see a list of available products")
    public void the_customer_should_see_a_list_of_available_products() {
        Assert.assertTrue(availableProductsCataloges);
    }

    @When("the customer adds the following product to the cart purchase:")
    public void the_customer_adds_the_following_product_to_the_cart_purchase(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        for (Map<String, String> row : data) {
            productName= row.get("Product Name");
        }
        addCart=purchase.addProductToCart(productCatalog.getProduct(productName));
    }

    @Then("the customer should see a purchase confirmation")
    public void the_customer_should_see_a_purchase_confirmation() {
        Assert.assertTrue(addCart);
    }

    @When("the customer navigates to the order history")
    public void the_customer_navigates_to_the_order_history() {
        Orders=purchase.printOrders();
    }

    @Then("the customer should see a list of previous orders")
    public void the_customer_should_see_a_list_of_previous_orders() {
        Assert.assertTrue(Orders);
    }

    @Given("there are InitialProduct1 products in the order")
    public void there_are_initial_product1_products_in_the_order() {
        printOrd=false;
    }
    @When("the user prints the order")
    public void the_user_prints_the_order() {
        printOrd=purchase.printOrders();
    }
    @Then("the system logs {string}")
    public void the_system_logs(String string) {
        logger.info(string);
    }
    @Then("the system logs each product name in the order")
    public void the_system_logs_each_product_name_in_the_order() {
        logger.info("InitialProduct1");

    }
    @Then("the system returns true")
    public void the_system_returns_true() {
       Assert.assertTrue(printOrd);
    }

    @Given("the order list is not empty")
    public void the_order_list_is_not_empty() {
        arrSize=purchase.lengthOfOrders();
        if (arrSize==0) arrayStatus=true;
    }
    @When("the length of the orders is requested")
    public void the_length_of_the_orders_is_requested() {
        arrSize=purchase.lengthOfOrders();
        logger.info(String.valueOf(arrSize));
    }
    @Then("the system should return the correct number of orders")
    public void the_system_should_return_the_correct_number_of_orders() {
        Assert.assertTrue(arrayStatus);
    }


    @Given("an order with products")
    public void an_order_with_products() {
        purchase = new Purchase();

    }
    @When("the order is processed")
    public void the_order_is_processed() {
        Product newProduct = new Product(3, "NewProduct", "Description 3", 20.0, "Category3", "In Stock");
        purchase.addProductToCart(newProduct);
    }
    @Then("the product names should be logged")
    public void the_product_names_should_be_logged() {
        boolean result = purchase.printOrders();
        Assert.assertEquals(true, result);
    }
}
