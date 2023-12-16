package my.backendproductioncode;

import java.util.Map;
import java.util.logging.Logger;

public class SignInServices {
    private static final Logger logger = Logger.getLogger(SignInServices.class.getName());
    private final RegistrationService registrationService =new RegistrationService();
    Map<String, User> users = registrationService.getRegisteredUsers();


    public String signInUser(String username, String password) {
        User user = users.get(username);
        if (user != null) {
            if (user.password().equals(password)) {
                logger.info("Password is valid. User can be signed in.");
                return "Password is valid. User can be signed in.";
            } else {
                logger.info("Invalid password. User cannot be signed in.");
                return "Invalid password. User cannot be signed in.";
            }
        } else {
            logger.info("User not found. User cannot be signed in.");
            return "User not found. User cannot be signed in.";
        }
    }
    public boolean signInUser(RegistrationService myReg, String username, String password) {
        // Step 1: Get the Map of registered users from myReg
        Map<String, User> registeredUsers = myReg.getRegisteredUsers();

        // Step 2: Check if the provided username exists in the Map
        if (registeredUsers.containsKey(username)) {
            User user = registeredUsers.get(username);
            // Step 3: Check if the provided password matches the user's password
            if (user.password().equals(password)) {
                // Username and password are valid for sign-in
                logger.info("Sign-in successful for user: " + username);
                return true;
            } 
        } 
    }


}






