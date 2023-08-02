Feature: Break Sale Contract
  Background: Shared Database
    Given Database is already filled

  Scenario Outline: Accept Sale Request Success
    When current owner who wants to break a sale contract is <username>
    And sale status of <houseId> in <buildingId> is set to be unavailable
    And owner break the sale request
    Then the sale of status of this house must be available

    Examples:
    | username | houseId | buildingId |
    | 'haya-sam' | 1  | 1 |