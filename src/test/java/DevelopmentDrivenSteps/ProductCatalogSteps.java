package DevelopmentDrivenSteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import my.backendproductioncode.AdminDashboard;
import my.backendproductioncode.ProductCatalog;
import org.junit.Assert;

public class ProductCatalogSteps {
    AdminDashboard admin=new AdminDashboard();
    ProductCatalog productCatalog=new ProductCatalog(admin);
    boolean userInPage=false;
    boolean dataIsPrinted=false;
    boolean productIsListed=false;
    boolean productPrintedWithDescription=false;
    boolean filterProduct=false;
    boolean productSearch=false;
    @Given("the user is on the Product Catalog page")
    public void the_user_is_on_the_product_catalog_page() {
        userInPage=true;
    }

    @When("they access the Categories section")
    public void they_access_the_categories_section() {
        dataIsPrinted=productCatalog.printProductCategories();
    }

    @Then("they should see categories\"")
    public void they_should_see_categories() {
        Assert.assertTrue(dataIsPrinted);
    }

    @When("they click on the {string} category")
    public void they_click_on_the_category(String string) {
        productIsListed= productCatalog.getProductsRelatedToSpecificCategory(string);
    }

    @Then("they should see a list of products related to electronics")
    public void they_should_see_a_list_of_products_related_to_electronics() {
        Assert.assertTrue(productIsListed);

    }

    @When("they select a product from the catalog")
    public void they_select_a_product_from_the_catalog() {
        productPrintedWithDescription=productCatalog.printAllProductData();
    }

    @Then("they should see detailed product information")
    public void they_should_see_detailed_product_information() {
        Assert.assertTrue(productPrintedWithDescription);
    }

    @When("they enter {string} into the search bar")
    public void they_enter_into_the_search_bar(String string) {
        productSearch=productCatalog.searchProductByNameAndPrintDetails(string);
    }

    @Then("they should see a list of products related to product")
    public void they_should_see_a_list_of_products_related_to_product() {
        Assert.assertTrue(productSearch);
    }

    @When("they select the Availability filter and choose {string}")
    public void they_select_the_availability_filter_and_choose(String string) {
        filterProduct=productCatalog.filterProductsByAvailabilityAndPrintProductNames(string);
    }
    @Then("they should see a list of products")
    public void they_should_see_a_list_of_products() {
        Assert.assertTrue(filterProduct);
    }

}
