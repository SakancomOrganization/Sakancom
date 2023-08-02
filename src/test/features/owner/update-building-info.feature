Feature: Update Building Info
  Background: Shared Database
    Given Database is already filled

  Scenario Outline: Update Success
    When current owner who wants to update the building info is <username>
    And the buildingId is <buildingId>
    And the field of updating the building is <field>
    And the value of updating the building is <value>
    Then the building information will be updated successfully

    Examples:
    | username | buildingId | field | value |
    | 'haya-sam' | 1 | 'name' | 'Great Building' |
    | 'haya-sam' | 1 | 'city' | 'Ramallah' |
    | 'haya-sam' | 1 | 'street' | 'AlManara' |

  Scenario Outline: Update Failed
    When current owner who wants to update the building info is <username>
    And the buildingId is <buildingId>
    And the field of updating the building is <field>
    And the value of updating the building is <value>
    Then the building information will not be updated successfully and building not found exception will be thrown

    Examples:
      | username | buildingId | field | value |
      | 'haya-sam' | 2 | 'name' | 'Great Building' |
      | 'najat-mansour' | 1 | 'city' | 'Ramallah' |