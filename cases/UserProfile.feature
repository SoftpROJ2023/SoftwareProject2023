Feature: User Profile
  Scenario: User edits their profile Username
    Given the User logged in as a user
    When the User navigate to his profile
    And the User update his Username
    And the User save the changes
    Then his profile should be updated successfully

  Scenario: User edits their profile Password
    Given the User logged in as a user
    When the User navigate to his profile
    And the User update his Password
    And the User save the change password
    Then his profile should be updated password successfully


  Scenario: User fails to update profile information with incorrect old password
    Given the User logged in as a user
    When the User navigate to his profile
    And the User enters an incorrect old password
    Then the system should display an error message
