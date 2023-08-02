Feature: Get All Sale Requests
  Background: Shared Database
    Given Database is already filled

  Scenario Outline: result is not 0
    When current owner who wants to get all the sale requests is <username>
    And the sale status of <houseId> in <buildingId> is set to be requested
    And owner select to get all sales requests
    Then the result size of the sales requests must be <resultSize>

    Examples:
    | username | houseId | buildingId | resultSize |
    | 'haya-sam' | 1 | 1 | 1 |
    | 'mo-alawneh' | 1 | 1 | 0 |

  Scenario Outline: result is 0
    When current owner who wants to get all the sale requests is <username>
    And the sale status of <houseId> in <buildingId> is set to be available
    And owner select to get all sales requests
    Then the result size of the sales requests must be <resultSize>

    Examples:
    | username | houseId | buildingId | resultSize |
    | 'haya-sam' | 1 | 1 | 0 |