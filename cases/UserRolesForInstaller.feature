Feature: Installer Role Functionality

  Scenario: Viewing Installation Requests
    Given that the user is logged in as an installer
    When the installer accesses the installation request list
    Then the installer should be able to view a list of pending installations

  Scenario: Scheduling an Appointment
    Given that the user is logged in as an installer
    When I provide the following appointment details:
      | Appointment ID | Customer Name | Product | Scheduled Date | Scheduled Time | Status |
      | 12345 | John Doe | CA Accessory XYZ | 25th Oct 2023 | 10:00 AM - 12:00 PM | Scheduled |
    Then You should observe the new appointment listed among the installation appointments.