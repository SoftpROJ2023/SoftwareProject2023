Feature: Admin Dashboard - Manage Product Categories

  Scenario: Add a new product category
    Given the admin is on the dashboard
    When the admin adds a new product category with name "exterior"
    Then the product category "exterior" should be listed on the dashboard

  Scenario: Edit an existing product category
    Given the admin is on the dashboard
    And there is an existing product category with name "electronics"
    When the admin edits the product category with name "electronics" to "exterior"
    Then the product category "electronics" should be edited on the dashboard

  Scenario: Delete an existing product category
    Given the admin is on the dashboard
    And there is an existing product category with name "electronics"
    When the admin deletes the product category with name "electronics"
    Then the product category "electronics" should not be listed on the dashboard


  Scenario: Add a product listing
    Given the admin is on the dashboard
    When they add a new product with the following details:
      | Name       | Description  | Price | Category  | Availability |
      | A | Product A | 10.0  | Electronics | In Stock   |
    Then the product should be listed on the dashboard

  Scenario: Update a product listing
    Given the admin is on the dashboard
    When they update the product with the ID 1 with the following details:
      | Name       | Description      | Price | Category | Availability |
      | Car Alarms | It is Car Alarms | 10.0  | interior | Out of Stock |
    Then the product with the ID 1 should be updated on the dashboard


  Scenario: View Customer Accounts
    Given the application is running
    When I enter the command "view_accounts"
    Then I should see a list of all customer accounts

  Scenario: Search for a Specific Customer Account
    Given the application is running
    When I enter the username "Admin"
    Then I should see the details of the customer's account

  Scenario: Edit a Customer Account
    Given the application is running
    When the user provides the following details for editing:
      | username | email             | password   |
      | Admin    | admin@example.com | password11 |
    Then the user should see an account update success message

  Scenario: Delete a Customer Account
    Given the application is running
    When the user provides the following details for deletion:
      | username |  |
      | Admin    |  |
    Then the user should see an account deletion success message


  Scenario: Add a New Customer Account
    Given the application is running
    When the user provides the following details for add new user:
      | username | email                 | password | confirm password |
      | John     | john@example.com      | Password1 | Password1       |
    Then the user should see a success message


  Scenario: Schedule a new installation appointment
    Given I am logged in as an admin
    When I provide the appointment details as follows:
      | Appointment ID | Customer Name | Product          |  | Scheduled Date | Scheduled Time      | Status    |
      | 12345          | John Doe      | CA Accessory XYZ |  | 25th Oct 2023  | 10:00 AM - 12:00 PM | Scheduled |
    Then I should see the new appointment in the list of installation appointments

  Scenario: Update an existing installation appointment
    Given I am logged in as an admin
    When I provide the updated appointment details as follows:
      | Appointment ID | Customer Name | Product   |  | Scheduled Date | Scheduled Time      | Status      |
      | 2              | Bob Johnson   | Product B |  | 2023-10-26  | 02:00 PM - 04:00 PM | Scheduled |
    Then I should see the updated appointment details in the list of installation appointments

  Scenario: Cancel an existing installation appointment
    Given I am logged in as an admin
    When I enter a "2" followed by the appointment ID in the console
    Then The appointment should be removed from the list of installation appointments

