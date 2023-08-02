Feature: Leave House
  Background: Shared Database
    Given Database is already filled
    And house 1 in building 1 is assigned to 'than@mare'

  Scenario Outline: Leave House Success
    When current tenant who wants to leave a house is <username>
    And sale status of <houseId> in <buildingId> is unavailable
    And tenant left the house
    Then the sale of status of this house left by the tenant must be available

    Examples:
    | username | houseId | buildingId |
    | 'than@mare' | 1  | 1 |

  Scenario Outline: Leave House Failed Due to Building Not Found Exception
    When current tenant who wants to leave a house is <username>
    And sale status of <houseId> in <buildingId> is unavailable
    Then the sale status must not change and building not found exception will be thrown

    Examples:
    | username | houseId | buildingId |
    | 'than@mare' | 1  | 2 |

  Scenario Outline: Leave House Failed Due to House Not Found Exception
    When current tenant who wants to leave a house is <username>
    And sale status of <houseId> in <buildingId> is unavailable
    Then the sale status must not change and house not found exception will be thrown

    Examples:
    | username | houseId | buildingId |
    # invalid house id
    | 'than@mare' | 2  | 1 |
    # invalid tenant
    | 'haya-sam' | 1 | 1 |