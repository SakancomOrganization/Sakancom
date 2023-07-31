Feature: Houses Search
  Background: Shared Database
    Given Database is already filled
    And another house is added

  Scenario Outline: Houses Search Success
    When services are <withElectricity> <withWater> <hasInternet> <hasTelephone> <hasBalcony> <bedroomsNum> <bathroomsNum>
    And monthly rent is <monthlyRent>
    And Owner is <username>
    And location is <city> <street>
    And house classification by gender is <houseClassificationByGender>
    Then the resulted list size will be <resultedListSize>

  Examples:
    | withElectricity |withWater | hasInternet | hasTelephone | hasBalcony | bedroomsNum | bathroomsNum | monthlyRent | username | city | street | houseClassificationByGender | resultedListSize |
    | 'true' | 'true' | 'true' | 'true' | 'true' | 3 | 2 | 2000 | 'haya-sam' | 'Nablus' | 'Rafidia' | 'Family' | 1 |
    | 'true' | 'false' | 'true' | 'true' | 'true' | 3 | 2 | 2000 | 'haya-sam' | 'Nablus' | 'Rafidia' | 'Family' | 0 |
    | 'true' | 'true' | 'true' | 'false' | 'true' | 3 | 2 | 2000 | 'haya-sam' | 'Nablus' | 'Rafidia' | 'Family' | 0 |
    | 'true' | 'true' | 'true' | 'true' | 'true' | 1 | 2 | 2000 | 'haya-sam' | 'Nablus' | 'Rafidia' | 'Family' | 1 |
    | 'true' | 'true' | 'true' | 'true' | 'true' | 4 | 2 | 2000 | 'haya-sam' | 'Nablus' | 'Rafidia' | 'Family' | 0 |
    | 'true' | 'true' | 'true' | 'true' | 'true' | 3 | 2 | 2500 | 'haya-sam' | 'Nablus' | 'Rafidia' | 'Family' | 1 |
    | 'true' | 'true' | 'true' | 'true' | 'true' | 3 | 2 | 2000 | 'haya-sam' | 'Jenin' | 'AbuBaker' | 'Family' | 0 |
    | 'true' | 'true' | 'true' | 'true' | 'true' | 3 | 2 | 2000 | 'haya-sam' | 'Nablus' | 'Rafidia' | 'Female' | 0 |
    | 'true' | 'true' | 'true' | 'true' | 'true' | 3 | 2 | 2500 | 'haya-sam' | 'Nablus' | '' | 'Family' | 2 |


    Scenario Outline: Houses Search Failed Due to Invalid Username
      When services are <withElectricity> <withWater> <hasInternet> <hasTelephone> <hasBalcony> <bedroomsNum> <bathroomsNum>
      And monthly rent is <monthlyRent>
      And Owner is <username>
      And location is <city> <street>
      And house classification by gender is <houseClassificationByGender>
      Then a user not found exception will be thrown

    Examples:
      | withElectricity |withWater | hasInternet | hasTelephone | hasBalcony | bedroomsNum | bathroomsNum | monthlyRent | username | city | street | houseClassificationByGender |
      | 'true' | 'true' | 'true' | 'true' | 'true' | 3 | 2 | 2000 | 'ali-ahmad' | 'Nablus' | 'Rafidia' | 'Family' |