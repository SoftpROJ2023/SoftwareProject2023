Feature: Installer role functionality

  Background:
    Given the user is logged in as an installer

  Scenario: Installer views installation requests
    When the installer accesses the installation request list
    Then the installer should see a list of pending installation requests

  Scenario: Installer schedules an appointment
    When the installer schedules an appointment for the following request:
      | Request ID | Date       | Time     |
      | 12345      | 2023-11-10 | 14:00:00 |
    Then the appointment should be scheduled successfully

