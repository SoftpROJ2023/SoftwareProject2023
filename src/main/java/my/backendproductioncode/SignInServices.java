package my.backendproductioncode;

import java.util.Map;

public class SignInServices {

    RegistrationService registrationService = new RegistrationService();
    Map<String, User> users = registrationService.getRegisteredUsers();

    public SignInServices() {
    //
    }


    public String signInUser(String username, String password) {
        User user = users.get(username);
        if (user != null) {
            if (user.password().equals(password)) {
                return "Password is valid. User can be signed in.";
            } else {
                return "Invalid password. User cannot be signed in.";
            }
        } else {
            return "User not found. User cannot be signed in.";
        }
    }




}
