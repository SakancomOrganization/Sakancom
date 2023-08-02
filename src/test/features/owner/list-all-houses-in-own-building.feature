Feature: List All Houses In Own Building
  Background: Shared Database
    Given Database is already filled

  Scenario Outline: List Success
    When the current owner who wants to list houses in a building is <username>
    And building id to list its houses is <buildingId>
    And the user list all houses in an own building
    Then the result size of the houses is <resultSize>

  Examples:
    | username | buildingId | resultSize |
    | 'haya-sam' | 1 | 1 |

  Scenario Outline: List Failed Due to Building Not Found Exception
    When the current owner who wants to list houses in a building is <username>
    And building id to list its houses is <buildingId>
    Then the list of houses in a building failed and building not found exception will be thrown

    Examples:
    | username | buildingId |
    # building id is correct but 'mo-alawneh' is not an onwer
    | 'mo-alawneh' | 1 |
    # building id is not correct
    | 'haya-sam' | 2 |