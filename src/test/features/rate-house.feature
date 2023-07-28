Feature: Rate House
 Scenario Outline: House Rate Success
   Given Database is already filled
   When building id is <buildingId>
   And house id is <houseId>
   And new rate is <newRate>
   Then the house rate will be <updatedHouseRate>

   Examples:
   | buildingId | houseId | newRate | updatedHouseRate |
   | 1 | 1 | 5.0 | 5.0 |
   | 1 | 1 | 4.0 | 4.5 |
   | 1 | 1 | 3.0 | 4.0 |

  Scenario Outline: House Rate failed due to Null Pointer Exception
    Given Database is already filled
    When building id is <buildingId>
    And house id is <houseId>
    And new rate is <newRate>
    Then a Null Pointer Exception Will be thrown

    Examples:
    | buildingId | houseId | newRate |
    | 0 | 1 | 5.0 |
    | 1 | 0 | 4.0 |

  Scenario Outline: House Rate failed due to Number Format Exception
    Given Database is already filled
    When building id is <buildingId>
    And house id is <houseId>
    And new rate is <newRate>
    Then a Number Format Exception Will be thrown

    Examples:
      | buildingId | houseId | newRate |
      | 1 | 1 | -1.0 |
      | 1 | 1 | 6.0 |