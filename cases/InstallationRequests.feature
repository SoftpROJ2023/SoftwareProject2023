Feature: Installation Requests

  Scenario: User requests a new installation appointment via CLI
    Given I am authenticated as a user
    When I input the appointment details through the CLI:
      | Request ID | Customer Name | Product          | Request Date  | Request Time        | Status    |
      | 101        | Jane Smith    | CB Accessory ABC | 28th Oct 2023 | 07:00 AM - 07:30 AM | Scheduled |
    Then the new appointment should be visible in my scheduled installations

  Scenario: Viewing Installation Requests
    Given that the user is logged in as an installer
    When the installer accesses the installation request list
    Then the installer should be able to view a list of pending installations