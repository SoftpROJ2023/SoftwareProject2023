Feature: User Registration

  Scenario: User successfully registers with valid information
    Given the user is on the registration page
    When the user provides the following details for registration:
      | username | email                 | password | confirm password |
      | John     | john@example.com      | Password1 | Password1       |
    Then the user should see a registration success message

  Scenario: User registration fails due to password and confirm password mismatch
    Given the user is on the registration page
    When the user provides the following details for registration:
      | username | email                 | password    | confirm password |
      | Mary     | mary@example.com      | Secret123  | Mismatch123      |
    Then the user should see an error message indicating password mismatch

  Scenario: User registration fails due to an invalid email address
    Given the user is on the registration page
    When the user provides the following details for registration:
      | username | email                 | password   | confirm password |
      | Alice    | invalid_email        | Pass123   | Pass123         |
    Then the user should see an error message indicating an invalid email address

  Scenario: User registration fails due to an already taken username
    Given the user is on the registration page
    When the user provides the following details for registration:
      | username       | email                  | password   | confirm password |
      | User2   | User2@example.com    | Pass123   | Pass123         |
    Then the user should see an error message indicating the username is already taken

  Scenario: Delete existing user
    Given the user with username exists
    When the admin deletes the user with username "existingUser"
    Then the user with username should be deleted successfully


