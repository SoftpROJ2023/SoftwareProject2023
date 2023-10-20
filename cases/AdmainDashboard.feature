Feature: Admin Dashboard - Manage Product Categories

  Scenario: Add a new product category
    Given the admin is on the dashboard
    When the admin adds a new product category with name "External"
    Then the product category "External" should be listed on the dashboard

  Scenario: Edit an existing product category
    Given the admin is on the dashboard
    And there is an existing product category with name "Electronics"
    When the admin edits the product category with name "Electronics" to "Gadgets"
    Then the product category "Gadgets" should be edited on the dashboard

  Scenario: Delete an existing product category
    Given the admin is on the dashboard
    And there is an existing product category with name "Electronics"
    When the admin deletes the product category with name "Electronics"
    Then the product category "Electronics" should not be listed on the dashboard


  Scenario: Add a product listing
    Given the admin is on the dashboard
    When they add a new product with the following details:
      | Name       | Description  | Price | Category  | Availability |
      | A | Product A | 10.0  | Electronics | In Stock   |
    Then the product should be listed on the dashboard

  Scenario: Update a product listing
    Given the admin is on the dashboard
    When they update the product with the ID 1 with the following details:
      |Name| Description  | Price | Category    | Availability |
      |AAA| Updated Prod | 15.0  | Home Decor | Out of Stock |
    Then the product with the ID 1 should be updated on the dashboard


  Scenario: View Customer Accounts
    Given the application is running
    When I enter the command "view_accounts"
    Then I should see a list of all customer accounts

  Scenario: Search for a Specific Customer Account
    Given the application is running
    When I enter the username "User1"
    Then I should see the details of the customer's account

  Scenario: Edit a Customer Account
    Given the application is running
    When the user provides the following details for editing:
      | username | email             | password   |
      | User1    | user1@example.com | password11 |
    Then the user should see an account update success message

  Scenario: Delete a Customer Account
    Given the application is running
    When the user provides the following details for deletion:
      | username |  |
      | User1    |  |
    Then the user should see an account deletion success message


  Scenario: Add a New Customer Account
    Given the application is running
    When the user provides the following details for add new user:
      | username | email                 | password | confirm password |
      | John     | john@example.com      | Password1 | Password1       |
    Then the user should see a success message


