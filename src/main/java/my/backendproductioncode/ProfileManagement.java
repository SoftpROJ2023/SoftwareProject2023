package my.backendproductioncode;

import java.util.ArrayList;
import java.util.List;

public class ProfileManagement {

    private List<Userinformation> users = new ArrayList<>();

    public void addUser(Userinformation user) {
        users.add(user);
    }

    public Userinformation getUser(String Email) {
        for (Userinformation user : users) {
            if (user.getEmail().equals(Email)) {
                return user;
            }
        }
        return null;
    }

}
