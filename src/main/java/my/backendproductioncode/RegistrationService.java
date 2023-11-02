package my.backendproductioncode;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class RegistrationService {

    private final Map<String, Userinformation> registeredUsers = new HashMap<>();
    private static final Logger logger = Logger.getLogger(RegistrationService.class.getName());


    public RegistrationService() {
        // Add initial users to the map
        registeredUsers.put("User1", new Userinformation("User1","user1@example.com", "password1", "password1"));
        registeredUsers.put("User2", new Userinformation("User2","user2@example.com", "password2", "password2"));
    }
    public String registerUser(String username, String email, String password, String confirmPassword) {
        if (password.equals(confirmPassword)) {
            if (!registeredUsers.containsKey(username)) {
                if (isValidEmail(email)) {
                    Userinformation newUser = new Userinformation(username,email, password, confirmPassword);
                    registeredUsers.put(username, newUser);
                    printRegisteredUsers();
                    return "Registration successful";
                } else {
                    return "Invalid email address";
                }
            } else {
                return "Username already exists";
            }
        } else {
            return "Password and confirm password do not match";
        }
    }

    private boolean isValidEmail(String email) {
        return email.contains("@");
    }
    public boolean validateUserSignIn(String username, String password) {
        Userinformation user = registeredUsers.get(username);
        return user != null && user.getPassword().equals(password);
    }
    public void printRegisteredUsers() {
        for (Map.Entry<String, Userinformation> entry : registeredUsers.entrySet()) {
            String username = entry.getKey();
            Userinformation user = entry.getValue();
            System.out.println("Username: " + username);
            System.out.println("Email: " + user.getEmail());
            System.out.println("Password: " + user.getPassword());
        }
    }
    public boolean printRegisteredUser(String targetUsername) {
        for (Map.Entry<String, Userinformation> entry : registeredUsers.entrySet()) {
            String username = entry.getKey();
            Userinformation user = entry.getValue();
            if (username.equals(targetUsername)) {
                System.out.println("Username: " + username);
                System.out.println("Email: " + user.getEmail());
                System.out.println("Password: " + user.getPassword());
                System.out.println(); // Add an empty line to separate users
                return true;
            }
        }
        return false;
    }
    public boolean getUser(String Email) {
        for (Map.Entry<String, Userinformation> entry : registeredUsers.entrySet()) {
            Userinformation user = entry.getValue();
            if (user.getEmail().equals(Email)) {
                return true;
            }
        }
        return false;
    }

    public boolean chickOldpassword(String oldpassword) {
        for (Map.Entry<String, Userinformation> entry : registeredUsers.entrySet()) {
            Userinformation user = entry.getValue();
            if (user.getPassword().equals(oldpassword)) {
                return true;
            }
        }
        return false;
    }

    public Map<String, Userinformation> getRegisteredUsers() {
    return registeredUsers;
    }
    public boolean isViewAccountsCommand(String command) {
        return "view_accounts".equalsIgnoreCase(command);
    }

    public boolean updateUser(String username, String newEmail, String newPassword) {
        if (registeredUsers.containsKey(username)) {
            Userinformation oldUser = registeredUsers.get(username);
            Userinformation newUser = new Userinformation(username, newEmail, newPassword, oldUser.getCPassword()); // Create a new User with updated data
            registeredUsers.put(username, newUser); // Replace the old User with the updated User
            logger.info("User Updated Successfully");
            return true; // User updated successfully
        }
        return false; // User not found
    }
    public boolean deleteUser(String username) {
        if (registeredUsers.containsKey(username)) {
            Userinformation user = registeredUsers.get(username);
                registeredUsers.remove(username);
                logger.info("User Deleted Successfully");
                return true; // User deleted successfully
        }
        logger.info("User not found for deletion");
        return false; // User not found
    }

}


