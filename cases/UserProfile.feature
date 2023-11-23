Feature: User Profile
  Scenario: Edit a Customer Account
    Given the user is logged in
    When the user navigates to the profile editing page:
      | username | email             | password   |
      | Admin    | admin@example.com | password11 |
    Then the user should see an account updated

  Scenario: Customer views orders
    Given the user is logged in
    When the user navigates to the order history page
    Then the customer should see a list of previous orders on screen