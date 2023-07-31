Feature: Get All Neighbors
  Background: Shared Database
    Given Database is already filled
    And another building with two houses is added

  Scenario Outline: Get All Neighbors Success
    When building id is <buildingId>
    And house id is <houseId>
    When tenant wants to list all the neighbors houses in the database
    Then the size of the neighbors result <resultSize>

    Examples:
    | buildingId | houseId | resultSize |
    | 1 | 1 | 1 |
    | 2 | 1 | 0 |

  Scenario Outline: Get All Neighbors Fail Due to Building Not Found Exception
    When building id is <buildingId>
    And house id is <houseId>
    Then the neighbors will not be found and building not found exception will be thrown

    Examples:
    | buildingId | houseId |
    | 4 | 1 |

  Scenario Outline: Get All Neighbors Fail Due to House Not Found Exception
    When building id is <buildingId>
    And house id is <houseId>
    Then the neighbors will not be found and house not found exception will be thrown

    Examples:
    | buildingId | houseId |
    | 1 | 7 |