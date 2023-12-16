Feature: Customer role functionality

  Background:
    Given the user is logged in as a customer

  Scenario: Customer browses products
    When the customer browses the product catalog
    Then the customer should see a list of available products

  Scenario: Customer makes a purchase
    When the customer adds the following product to the cart purchase:
      | Product Name |  |
      | Car Cover    |  |
    Then the customer should see a purchase confirmation

  Scenario: Customer views orders
    When the customer navigates to the order history
    Then the customer should see a list of previous orders

