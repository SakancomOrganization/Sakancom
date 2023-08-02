Feature: List All Buildings
  Background: Shared Database
    Given Database is already filled

  Scenario: List All Buildings
    When the admin wants to list all buildings
    Then the result size of all the buildings must be 1