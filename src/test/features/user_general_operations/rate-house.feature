Feature: Rate House
  Background: Shared Database
    Given Database is already filled

 Scenario Outline: House Rate Success
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
    When building id is <buildingId>
    And house id is <houseId>
    And new rate is <newRate>
    Then a Building Not Found Exception will be thrown

    Examples:
    | buildingId | houseId | newRate |
    | 0 | 1 | 5.0 |

    Scenario Outline: House Rate failed due to Null Pointer Exception
      When building id is <buildingId>
      And house id is <houseId>
      And new rate is <newRate>
      Then a House Not Found Exception will be thrown

      Examples:
        | buildingId | houseId | newRate |
        | 1 | 0 | 4.0 |

  Scenario Outline: House Rate failed due to Number Format Exception
    When building id is <buildingId>
    And house id is <houseId>
    And new rate is <newRate>
    Then a Number Format Exception Will be thrown

    Examples:
      | buildingId | houseId | newRate |
      | 1 | 1 | -1.0 |
      | 1 | 1 | 6.0 |