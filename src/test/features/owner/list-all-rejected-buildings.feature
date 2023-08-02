Feature: List All Rejected Buildings
  Background: Shared Database
    Given Database is already filled

  Scenario Outline: result size is not 0
    Given Admin change building with id 1 info status to rejected
    When username of owner who wants to list the rejected houses is <username>
    And owner wants to list all the rejected buildings
    Then result size of the rejected buildings must be <resultSize>

  Examples:
    | username | resultSize |
    | 'haya-sam' | 1 |
    | 'mo-alawneh' | 0 |

  Scenario Outline: result size is 0
    Given Admin change building with id 1 info status to accepted
    When username of owner who wants to list the rejected houses is <username>
    And owner wants to list all the rejected buildings
    Then result size of the rejected buildings must be <resultSize>

  Examples:
    | username | resultSize |
    | 'haya-sam' | 0 |