Feature: Accept Sale Request
  Background: Shared Database
    Given Database is already filled

  Scenario Outline: Accept Sale Request Success
    When current owner who wants to accept a sale request is <username>
    And sale status of <houseId> in <buildingId> is set to be requested
    And owner accept the sale request
    Then the sale of status of this house must be unavailable

    Examples:
    | username | houseId | buildingId |
    | 'haya-sam' | 1  | 1 |

  Scenario Outline: Accept Sale Request Failed Due to Building Not Found Exception
    When current owner who wants to accept a sale request is <username>
    And house to change its sale status is <houseId> and comes in <buildingId>
    Then the sale of status of this house must not change and building not found exception will be thrown

    Examples:
    | username | houseId | buildingId |
    | 'haya-sam' | 1  | 2 |
    | 'mo-alawneh' | 1  | 1 |

  Scenario Outline: Accept Sale Request Failed Due to House Not Found Exception
    When current owner who wants to accept a sale request is <username>
    And house to change its sale status is <houseId> and comes in <buildingId>
    Then the sale of status of this house must not change and house not found exception will be thrown

    Examples:
    | username | houseId | buildingId |
    | 'haya-sam' | 2  | 1 |