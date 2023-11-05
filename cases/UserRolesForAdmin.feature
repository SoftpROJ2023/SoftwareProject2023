Feature: Admin role functionality
  Background:
    Given the user is logged in as an admin

  Scenario: Admin adds a new product
    When the admin adds a new product with the following details:
      | Name | Description | Price | Category    | Availability |
      | A    | Product A   | 10.0  | electronics | In Stock     |
    Then the product should be added successfully

  Scenario: Admin updates a category
    When the admin updates the category with the following details:
      | Old Category Name | New Category Name |
      | electronics       | Gadgets           |
    Then the category should be updated successfully

  Scenario: Admin registers a new user
    When the admin provides the following details for user registration:
      | username | email                 | password | confirm password |
      | John     | john@example.com      | Password1 | Password1       |
    Then the user should see success message