package my.backendproductioncode;

public class Product {
    private final int productId;

    private  String name;
    private String description;
    private double price;
    private String category;
    private String availability;

    public Product(int productId,String name,String description, double price, String category, String availability) {
        this.productId = productId;
        this.name=name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.availability = availability;
    }


    public int getProductId() {
        return productId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

}
