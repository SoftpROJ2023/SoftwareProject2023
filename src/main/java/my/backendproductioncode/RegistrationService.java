package my.backendproductioncode;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class RegistrationService {

    private final Map<String, User> registeredUsers = new HashMap<>();
    private static final Logger logger = Logger.getLogger(RegistrationService.class.getName());


    public RegistrationService() {
        // Add initial users to the map
        registeredUsers.put("User1", new User("User1","user1@example.com", "password1", "password1"));
        registeredUsers.put("User2", new User("User2","user2@example.com", "password2", "password2"));
    }
    public String registerUser(String username, String email, String password, String confirmPassword) {
        if (password.equals(confirmPassword)) {
            if (!registeredUsers.containsKey(username)) {
                if (isValidEmail(email)) {
                    User newUser = new User(username,email, password, confirmPassword);
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
        User user = registeredUsers.get(username);
        return user != null && user.password().equals(password);
    }
    public void printRegisteredUsers() {
        for (Map.Entry<String, User> entry : registeredUsers.entrySet()) {
            String username = entry.getKey();
            User user = entry.getValue();
            System.out.println("Username: " + username);
            System.out.println("Email: " + user.email());
            System.out.println("Password: " + user.password());
        }
    }
    public boolean printRegisteredUser(String targetUsername) {
        for (Map.Entry<String, User> entry : registeredUsers.entrySet()) {
            String username = entry.getKey();
            User user = entry.getValue();
            if (username.equals(targetUsername)) {
                System.out.println("Username: " + username);
                System.out.println("Email: " + user.email());
                System.out.println("Password: " + user.password());
                System.out.println(); // Add an empty line to separate users
                return true;
            }
        }
        return false;
    }


    public Map<String, User> getRegisteredUsers() {
    return registeredUsers;
    }
    public boolean isViewAccountsCommand(String command) {
        return "view_accounts".equalsIgnoreCase(command);
    }

    public boolean updateUser(String username, String newEmail, String newPassword) {
        if (registeredUsers.containsKey(username)) {
            User oldUser = registeredUsers.get(username);
            User newUser = new User(username, newEmail, newPassword, oldUser.confirmPassword()); // Create a new User with updated data
            registeredUsers.put(username, newUser); // Replace the old User with the updated User
            logger.info("User Updated Successfully");
            return true; // User updated successfully
        }
        return false; // User not found
    }
    public boolean deleteUser(String username) {
        if (registeredUsers.containsKey(username)) {
            User user = registeredUsers.get(username);
                registeredUsers.remove(username);
                logger.info("User Deleted Successfully");
                return true; // User deleted successfully
        }
        logger.info("User not found for deletion");
        return false; // User not found
    }

}


