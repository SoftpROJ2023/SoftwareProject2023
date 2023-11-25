package my.backendproductioncode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Installer {
    private String name;
    private List<InstallationRequest> installationRequests;

    public Installer(String name) {
        this.name = name;
        this.installationRequests = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void scheduleInstallation(InstallationRequest request) {
        installationRequests.add(request);
        request.getCustomer().getOrderHistory().add(request);
        System.out.println("Installation scheduled for " + request.getCustomer().getUsername() +
                " on " + request.getPreferredDate() + " by " + this.name);
    }
    private void viewAndCancelInstallationRequests(Scanner scanner, Userinformation customer) {
        List<InstallationRequest> customerRequests = getInstallationRequestsForCustomer(customer);

        if (customerRequests.isEmpty()) {
            System.out.println("No installation requests available.");
            return;
        }

        System.out.println("Your Installation Requests:");
        for (int i = 0; i < customerRequests.size(); i++) {
            InstallationRequest request = customerRequests.get(i);
            System.out.println((i + 1) + ". " + request.getProduct().getName() +
                    " - Quantity: " + request.getQuantity() +
                    " - Date: " + request.getPreferredDate());
        }

        System.out.print("Enter the number of the request to cancel (0 to cancel): ");
        int requestNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (requestNumber >= 1 && requestNumber <= customerRequests.size()) {
            InstallationRequest requestToRemove = customerRequests.get(requestNumber - 1);
            customerRequests.remove(requestToRemove);
            installationRequests.remove(requestToRemove);
            System.out.println("Installation request canceled successfully.");
        } else if (requestNumber == 0) {
            System.out.println("Request cancellation canceled.");
        } else {
            System.out.println("Invalid request number. Please try again.");
        }
    }
    public List<InstallationRequest> getInstallationRequestsForCustomer(Userinformation customer) {
        List<InstallationRequest> customerRequests = new ArrayList<>();
        for (InstallationRequest request : installationRequests) {
            if (request.getCustomer().getUsername().equals(customer.getUsername())) {
                customerRequests.add(request);
            }
        }
        return customerRequests;
    }
}
