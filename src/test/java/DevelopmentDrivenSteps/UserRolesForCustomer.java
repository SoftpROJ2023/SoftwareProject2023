package DevelopmentDrivenSteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import my.backendproductioncode.AdminDashboard;
import my.backendproductioncode.ProductCatalog;
import my.backendproductioncode.Purchase;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class UserRolesForCustomer {
    AdminDashboard admin=new AdminDashboard();
    ProductCatalog productCatalog=new ProductCatalog(admin);
    Purchase purchase=new Purchase();
    boolean isLogged=false;
    boolean availableProductsCataloges=false;
    boolean addCart=false;
    boolean Orders=false;
    String productName;
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


}
