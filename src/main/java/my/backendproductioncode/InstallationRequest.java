package my.backendproductioncode;

import my.backendproductioncode.Product;
import my.backendproductioncode.Userinformation;

import java.util.*;

public class InstallationRequest {
    private Userinformation customer;
    private Product product;
    private int quantity;
    private String carMakeModel;
    private String preferredDate;

    public InstallationRequest(Userinformation customer, Product product, int quantity, String carMakeModel, String preferredDate) {
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.carMakeModel = carMakeModel;
        this.preferredDate = preferredDate;
    }

    public Userinformation getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCarMakeModel() {
        return carMakeModel;
    }

    public String getPreferredDate() {
        return preferredDate;
    }
}
