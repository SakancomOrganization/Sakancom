Feature: Add House
  Background: Shared Database
    Given Database is already filled

  Scenario Outline: Add Success
    When house added to building with id <buildingId>
    And current owner who wants to add a house is <username>
    And added house services are <withElectricity> <withWater> <hasInternet> <hasTelephone> <hasBalcony> <bedroomsNum> <bathroomsNum>
    And monthlyRent is <monthlyRent>
    And floor num is <floorNum>
    And house classification by gender of the added house is <houseClassificationByGender>
    Then house will be added successfully

    Examples:
    | buildingId  | username | withElectricity | withWater | hasInternet | hasTelephone | hasBalcony | bedroomsNum | bathroomsNum | monthlyRent | floorNum | houseClassificationByGender |
    | 1 | 'haya-sam' | 'true' | 'false' | 'true' | 'false' | 'true' | 2 | 1 | 2400 | 3 | 'Family' |

  Scenario Outline: Add Failed Due to Building Not Found Exception
    When house added to building with id <buildingId>
    And current owner who wants to add a house is <username>
    And added house services are <withElectricity> <withWater> <hasInternet> <hasTelephone> <hasBalcony> <bedroomsNum> <bathroomsNum>
    And monthlyRent is <monthlyRent>
    And floor num is <floorNum>
    And house classification by gender of the added house is <houseClassificationByGender>
    Then house will not be added successfully and building not found exception will be thrown

    Examples:
    | buildingId | username | withElectricity | withWater | hasInternet | hasTelephone | hasBalcony | bedroomsNum | bathroomsNum | monthlyRent | floorNum | houseClassificationByGender |
    # invalid id
    | 2 | 'haya-sam' | 'true' | 'false' | 'true' | 'false' | 'true' | 2 | 1 | 2400 | 3 | 'Family' |
    # non-owner
    | 1 | 'mo-alawneh' | 'true' | 'false' | 'true' | 'false' | 'true' | 2 | 1 | 2400 | 3 | 'Family' |

    Scenario Outline: Add Failed Due to Unacceptable Value Exception
      When house added to building with id <buildingId>
      And current owner who wants to add a house is <username>
      And added house services are <withElectricity> <withWater> <hasInternet> <hasTelephone> <hasBalcony> <bedroomsNum> <bathroomsNum>
      And monthlyRent is <monthlyRent>
      And floor num is <floorNum>
      And house classification by gender of the added house is <houseClassificationByGender>
      Then house will not be added successfully and unacceptable value exception will be thrown

      Examples:
      | buildingId | username | withElectricity | withWater | hasInternet | hasTelephone | hasBalcony | bedroomsNum | bathroomsNum | monthlyRent | floorNum | houseClassificationByGender |
      | 1 | 'haya-sam' | 'true' | 'false' | 'true' | 'false' | 'true' | -1 | 2 | 2400 | 3 | 'Family' |
      | 1 | 'haya-sam' | 'true' | 'false' | 'true' | 'false' | 'true' | 1 | -2 | 2400 | 3 | 'Family' |
      | 1 | 'haya-sam' | 'true' | 'false' | 'true' | 'false' | 'true' | 1 | 2 | -100 | 3 | 'Family' |
      | 1 | 'haya-sam' | 'true' | 'false' | 'true' | 'false' | 'true' | 1 | 2 | 2400 | -3 | 'Family' |