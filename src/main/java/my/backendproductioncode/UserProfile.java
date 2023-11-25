package my.backendproductioncode;

import java.util.Scanner;
import java.util.logging.Level;

import static my.backendproductioncode.AdminDashboard.logger;

public class UserProfile {

    private Userinformation customer;

    public UserProfile(Userinformation customer) {
        this.customer = customer;
    }

    public void editProfile(Scanner scanner) {
        System.out.println("Select the information to edit:");
        System.out.println("1. Name");
        System.out.println("2. Phone Number");
        System.out.println("0. Cancel");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter your new name: ");
                String newName = scanner.nextLine();
                customer.setUsername(newName);
                System.out.println("Name updated successfully.");
                break;

            case 2:
                System.out.print("Enter your new phone number: ");
                String newPhoneNumber = scanner.nextLine();
                customer.setEmail(newPhoneNumber);
                System.out.println("Phone number updated successfully.");
                break;

            case 0:
                System.out.println("Editing profile canceled.");
                break;

            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
    }

    public void viewOrderHistory() {
        for (InstallationRequest request : customer.getOrderHistory()) {
            System.out.println("Product: " + request.getProduct().getName() +
                    " | Quantity: " + request.getQuantity() +
                    " | Date: " + request.getPreferredDate());
        }
        if (customer.getOrderHistory().isEmpty()) {
            System.out.println("No order history available.");
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
