package my.backendproductioncode;

import java.time.Clock;

public class Userinformation {


    private String username;
    private String Email;
    private String Password;
    private String CPassword;


    public Userinformation(){}
    public Userinformation(String username,String Email,String Password,String CPassword){
        this.username=username;
        this.Email=Email;
        this.Password=Password;
        this.CPassword=CPassword;
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

}
