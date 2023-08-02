Feature: List All Buildings
  Background: Shared Database
    Given Database is already filled

  Scenario Outline:
    When the current owner who wants to list own buildings is <username>
    And the user wants to list all own buildings
    Then the result size of the buildings must be <resultSize>

  Examples:
    | username | resultSize |
    | 'haya-sam' | 1 |
    | 'mo-alawneh' | 0 |