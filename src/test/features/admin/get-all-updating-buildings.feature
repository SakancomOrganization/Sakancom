Feature: Get All Updating Buildings
  Background: Shared Database
    Given Database is already filled

  Scenario: The result is not 0
    When building 1 info status is set to be dirty
    And the current admin try to get all updating buildings
    Then the result size of the updating houses must be 1

  Scenario: The result is 0
    When building 1 info status is set to be accepted
    And the current admin try to get all updating buildings
    Then the result size of the updating houses must be 0