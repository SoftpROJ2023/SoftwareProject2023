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

  Scenario: Printing purchased products
    Given there are InitialProduct1 products in the order
    When the user prints the order
    Then the system logs "Purchased products"
    And the system logs each product name in the order
    And the system returns true
  Scenario: Customer views orders
    When the customer navigates to the order history
    Then the customer should see a list of previous orders

  Scenario: Retrieve the length of the orders list
    Given the order list is not empty
    When the length of the orders is requested
    Then the system should return the correct number of orders

  Scenario: Log product names in an order
    Given an order with products
    When the order is processed
    Then the product names should be logged