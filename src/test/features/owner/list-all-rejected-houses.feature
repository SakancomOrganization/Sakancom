Feature: List All Rejected Houses
  Background: Shared Database
    Given Database is already filled

  Scenario Outline: result size is not 0
    Given Admin change the info status of the house with id 1 in the building id 1 to be rejected
    When current owner who wants to list the rejected houses is <username>
    And owner list the rejected houses
    Then the result size of the rejected houses must be <resultSize>

  Examples:
    | username | resultSize |
    | 'haya-sam' | 1 |
    | 'mo-alawneh' | 0 |

  Scenario Outline: result size is 0
    Given Admin change the info status of the house with id 1 in the building id 1 to be accepted
    When current owner who wants to list the rejected houses is <username>
    And owner list the rejected houses
    Then the result size of the rejected houses must be <resultSize>

    Examples:
      | username | resultSize |
      | 'haya-sam' | 0 |