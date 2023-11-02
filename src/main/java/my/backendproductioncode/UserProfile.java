package my.backendproductioncode;

import java.util.Scanner;
import java.util.logging.Level;

import static my.backendproductioncode.AdminDashboard.logger;

public class UserProfile {

    public UserProfile() {

    }
    public String editPassword(String Email,String oldpassword,String newpassword){
        RegistrationService userregestration=new RegistrationService();
        boolean finduser = userregestration.getUser(Email);
        if(finduser){
            if(userregestration.chickOldpassword(oldpassword)) return "Updated successfully.";
            else return "Old password is incorrect. Password not changed.";
        }else{
            return "User not found";
        }
    }
    public String editUsername(String Email,String newusername){
        RegistrationService userregestration=new RegistrationService();
        boolean finduser = userregestration.getUser(Email);
        if(finduser){
            Userinformation user=new Userinformation();
            user.setUsername(newusername);
            return "Updated successfully";
        }else{
            return "User not found";
        }
    }
    Cartbackend carts;
    public String viewCart(){
        if(carts.isEmpty()){
            return "The Cart is Empty";
        }else{
            carts.display();
        }
        return "Done";
    }
}
