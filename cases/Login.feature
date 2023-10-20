Feature: Sign-In Functionality

  Scenario: User successfully signs in
    Given the user is on the sign-in page
    When the user provides the following details for sign-in:
      | username | password |
      | User1    | password1   |
    Then the user should be successfully signed in

  Scenario: User enters an invalid username and password
    Given the user is on the sign-in page
    When the user provides the following details for sign-in:
      | username | password |
      | John     | invalid  |
    Then the user should see an error message


