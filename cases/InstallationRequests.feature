Feature: Installation Requests
  As a customer
  In order to request installation services
  I want to provide installation details and schedule an appointment

  Scenario: Customer submits installation request
    Given the customer wants to request installation services
    When the customer fills out the installation request form with the following details:
      | Product   | Car Make/Model | Preferred Date   |
      | Product A | Toyota Camry    | 2023-01-01       |
    And the installer is available on the preferred date
    Then the installation request should be submitted successfully
    And the customer should receive a confirmation email

  Scenario: Installer is unavailable on the preferred date
    Given the customer wants to request installation services
    When the customer fills out the installation request form with the following details:
      | Product   | Car Make/Model | Preferred Date   |
      | Product B | Honda Accord    | 2023-01-01       |
    And the installer is not available on the preferred date
    Then the customer should be informed that the preferred date is not available
    And the customer should be prompted to choose an alternative date

  Scenario: Customer provides incomplete installation details
    Given the customer wants to request installation services
    When the customer fills out the installation request form with missing details
    Then the customer should be prompted to provide the missing details
    And the installation request should not be submitted

  Scenario: Customer cancels installation request
    Given the customer has an existing installation request
    When the customer cancels the installation request
    Then the installation request should be canceled
    And the customer should receive a cancellation confirmation
