package my.backendproductioncode;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

public class Userinformation {


    private String username;
    private String Email;
    private String Password;
    private String CPassword;
    private List<InstallationRequest> orderHistory;
    private UserProfile userProfile;


    public Userinformation(){}
    public Userinformation(String username,String Email,String Password,String CPassword){
        this.username=username;
        this.Email=Email;
        this.Password=Password;
        this.CPassword=CPassword;
        this.orderHistory = new ArrayList<>();
        this.userProfile = new UserProfile(this);

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    public String getPassword() {
        return username;
    }
    public List<InstallationRequest> getOrderHistory() {
        return orderHistory;
    }
    public void setPassword(String password) {
        this.Password = password;
    }

    public String getCPassword() {
        return Password;
    }


    public boolean changePassword(String oldPassword, String newPassword) {
        if (oldPassword.equals(this.Password)) {
            setPassword(newPassword);
            return true;
        } else {
            return false;
        }
    }

//    public void viewOrderHistory() {
//        System.out.println("Order History for " + this.username + ":");
//        for (InstallationRequest request : orderHistory) {
//            System.out.println(request.getProduct().getName() + " - " + request.getPreferredDate());
//        }
//    }

}
