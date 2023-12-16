Feature: User Sign In

  Scenario: Successful Sign In
    Given a registered user with username "john_doe" and password "P@ssw0rd"
    When the user attempts to sign in with username "john_doe" and correct password "P@ssw0rd"
    Then the sign-in should be successful

  Scenario: Invalid Password
    Given a registered user with username "jane_smith" and password "Secure123"
    When the user attempts to sign in with username "jane_smith" and incorrect password "WrongPassword"
    Then the sign-in should fail with an invalid password message

  Scenario: User Not Found
    Given no registered user with username "nonexistent_user"
    When the user attempts to sign in with username "nonexistent_user" and any password
    Then the sign-in should fail with a user not found message

