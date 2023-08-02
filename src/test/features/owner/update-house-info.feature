Feature: Update House Info
  Background: Shared Database
    Given Database is already filled

  Scenario Outline: Update Success
    When current owner who wants to update the house info is <username>
    And building id of the updatedHouse building is <buildingId>
    And updated house id is <houseId>
    And field of updating the house is <field>
    And value of updating the house is <value>
    Then the house must be updated successfully

    Examples:
    | username | buildingId | houseId | field | value |
    | 'haya-sam' | 1 | 1 | 'monthlyRent' | '3400' |
    | 'haya-sam' | 1 | 1 | 'includesElectricity' | 'false' |
    | 'haya-sam' | 1 | 1 | 'includesWater' | 'false' |
    | 'haya-sam' | 1 | 1 | 'hasInternet' | 'true' |
    | 'haya-sam' | 1 | 1 | 'hasTelephone' | 'false' |
    | 'haya-sam' | 1 | 1 | 'hasBalcony' | 'true' |
    | 'haya-sam' | 1 | 1 | 'bedroomsNum' | '5' |
    | 'haya-sam' | 1 | 1 | 'bathroomsNum' | '1' |
    | 'haya-sam' | 1 | 1 | 'houseClassificationByGender' | 'Family' |
    | 'haya-sam' | 1 | 1 | 'houseClassificationByGender' | 'Female' |
    | 'haya-sam' | 1 | 1 | 'houseClassificationByGender' | 'Male' |

  Scenario Outline: Update Failed Due to Number Format Exception
    When current owner who wants to update the house info is <username>
    And building id of the updatedHouse building is <buildingId>
    And updated house id is <houseId>
    And field of updating the house is <field>
    And value of updating the house is <value>
    Then the house will not be updated and a number format exception will be thrown

    Examples:
    | username | buildingId | houseId | field | value |
    | 'haya-sam' | 1 | 1 | 'monthlyRent' | 'ffff' |

  Scenario Outline: Update Failed Due to Unacceptable Value Exception
    When current owner who wants to update the house info is <username>
    And building id of the updatedHouse building is <buildingId>
    And updated house id is <houseId>
    And field of updating the house is <field>
    And value of updating the house is <value>
    Then the house will not be updated and a unacceptable value exception will be thrown

    Examples:
    | username | buildingId | houseId | field | value |
    | 'haya-sam' | 1 | 1 | 'monthlyRent' | '-100' |

  Scenario Outline: Update Failed Due to Building Not Found Exception
    When current owner who wants to update the house info is <username>
    And building id of the updatedHouse building is <buildingId>
    And updated house id is <houseId>
    And field of updating the house is <field>
    And value of updating the house is <value>
    Then the house will not be updated and a building not found will be thrown

    Examples:
    | username | buildingId | houseId | field | value |
    | 'haya-sam' | 2 | 1 | 'monthlyRent' | '5000' |
    | 'mo-alawneh' | 1 | 1 | 'monthlyRent' | '6000' |

  Scenario Outline: Update Failed Due to House Not Found Exception
    When current owner who wants to update the house info is <username>
    And building id of the updatedHouse building is <buildingId>
    And updated house id is <houseId>
    And field of updating the house is <field>
    And value of updating the house is <value>
    Then the house will not be updated and a house not found will be thrown

    Examples:
    | username | buildingId | houseId | field | value |
    | 'haya-sam' | 1 | 2 | 'monthlyRent' | '5000' |