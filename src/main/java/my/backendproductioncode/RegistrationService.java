package my.backendproductioncode;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class RegistrationService {
    char name;
    private final Map<String, User> registeredUsers = new HashMap<>();
    private static final Logger logger = Logger.getLogger(RegistrationService.class.getName());

    public RegistrationService() {
        registeredUsers.put("User1", new User("User1","user1@example.com", "password1", "password1"));
        registeredUsers.put("User2", new User("User2","user2@example.com", "password2", "password2"));
    }

    public String registerUser(String username, String email, String password, String confirmPassword) {
        if (password.equals(confirmPassword)) {
            if (!isUsernameTaken(username)) {
                if (isValidEmail(email)) {
                    User newUser = new User(username, email, password, confirmPassword);
                    registeredUsers.put(username, newUser);
                    logger.info("Registration successful");
                    printRegisteredUsers();
                    return "Registration successful";
                } else {
                    logger.warning("Invalid email address");
                    return "Invalid email address";
                }
            } else {
                logger.warning("Username already exists");
                return "Username already exists";
            }
        } else {
            logger.warning("Password and confirm password do not match");
            return "Password and confirm password do not match";
        }
    }

    private boolean isUsernameTaken(String username) {
        return registeredUsers.containsKey(username);
    }

    private boolean isValidEmail(String email) {
        return email.contains("@");
    }

    public void printRegisteredUsers() {
        for (Map.Entry<String, User> entry : registeredUsers.entrySet()) {
            String username = entry.getKey();
            User user = entry.getValue();
            logger.info("Username: " + username);
            logger.info("Email: " + user.email());
        }
    }
    public boolean printRegisteredUser(String targetUsername) {
        for (Map.Entry<String, User> entry : registeredUsers.entrySet()) {
            String username = entry.getKey();
            User user = entry.getValue();
            if (username.equals(targetUsername)) {
                logger.info("Username: " + username);
                logger.info("Email: " + user.email());
                logger.info("Password: " + user.password());
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
        registeredUsers.computeIfPresent(username, (key, oldUser) -> {
            User newUser = new User(username, newEmail, newPassword, oldUser.confirmPassword()); // Create a new User with updated data
            logger.info("User Updated Successfully");
            return newUser; // Replace the old User with the updated User
        });
        return registeredUsers.containsKey(username);
    }
    public boolean deleteUser(String username) {
        if (registeredUsers.containsKey(username)) {
            registeredUsers.remove(username);
            logger.info("User Deleted Successfully");
            return true; // User deleted successfully
        }
        logger.info("User not found for deletion");
        return false; // User not found
    }


}


