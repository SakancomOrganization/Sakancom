Feature: List All Available Houses
  Background: Shared Database
    Given Database is already filled

  Scenario: answer is more than zero
    When tenant wants to list all the available houses in the database
    Then the size of the available houses result 1

  Scenario: answer is zero
    Given the house sale status is set to be requested
    When tenant wants to list all the available houses in the database
    Then the size of the available houses result 0