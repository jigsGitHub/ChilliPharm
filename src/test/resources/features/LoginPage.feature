Feature: ChilliPharm Login Page
  As a User
  I want to test login functionality of ChilliPharm
  So that different levels of work can be accomplished on ChilliPharm website pages

  @SmokeTest
  Scenario Outline: Verifying Empty fields of Login screen
    Given I am on ChilliPharm home page
    And I navigate to Login page
    When I enter email "<EMAIL>" and password "<PASSWORD>"
    Then I should see the error message for "<EMAIL>" and password "<PASSWORD>" on Login screen

    Examples:
      | EMAIL             | PASSWORD |
      |                   |          |
      | testing@gmail.com |          |
      |                   | Test123  |

  @SmokeTest
  Scenario Outline: Verifying Invalid Login credentials
    Given I am on ChilliPharm home page
    And I navigate to Login page
    When I enter email "<EMAIL>" and password "<PASSWORD>"
    Then I should see the invalid credentials error message

    Examples:
      | EMAIL                  | PASSWORD           |
      | invalidemail@gmail.com | correctpassword123 |
      | validemail@yahoo.com   | invalidpassword123 |

  @SmokeTest
  Scenario: Verifying third consecutive failed attempt
    Given I am on ChilliPharm home page
    And I navigate to Login page
    When I enter invalid credentials three time consecutive with same email
    Then I should see the account lockout warning after third consecutive failed attempt

  @SmokeTest
  Scenario: Verifying fifth consecutive failed attempt
    Given I am on ChilliPharm home page
    And I navigate to Login page
    When I enter invalid credentials five time consecutive with same email
    Then I should see the account lockout warning after fifth consecutive failed attempt
