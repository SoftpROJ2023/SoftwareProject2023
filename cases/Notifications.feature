Feature: Notifications

  Scenario: Customer receives order confirmation via email
    Given the customer has placed an order
    When the order is confirmed
    Then the customer should receive an email with the order confirmation


  Scenario: Installer receives notification about new installation request
    Given there is a new installation request
    When the installer is notified
    Then the installer should receive information about the new installation request

