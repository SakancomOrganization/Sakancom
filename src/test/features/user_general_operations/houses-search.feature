Feature: Houses Search
  Background: Shared Database
    Given Database is already filled
    And another house is added

  Scenario Outline: Houses Search Success
    When services are <withElectricity> <withWater> <hasInternet> <hasTelephone> <hasBalcony> <bedroomsNum> <bathroomsNum>
    And monthly rent is <monthlyRent>
    And owner name is <firstName> <middleName> <lastName>
    And building name is <buildingName>
    And location is <city> <street>
    And house classification by gender is <houseClassificationByGender>
    Then the resulted list size will be <resultedListSize>

  Examples:
    | withElectricity | withWater | hasInternet | hasTelephone | hasBalcony | bedroomsNum | bathroomsNum | monthlyRent | firstName | middleName | lastName |buildingName | city | street | houseClassificationByGender | resultedListSize |
    | 'true' | 'true' | 'true' | 'true' | 'true' | 3 | 2 | 2000 | 'Haya' | 'Yaser' | 'Samaana' | 'Golden House' | 'Nablus' | 'Rafidia' | 'Family' | 1 |
    | 'true' | 'true' | 'true' | 'true' | 'true' | 3 | 2 | 2000 | 'Haya' | 'Yaser' | 'Samaana' | 'House' | 'Nablus' | 'Rafidia' | 'Family' | 1 |
    | 'true' | 'true' | 'true' | 'true' | 'true' | 3 | 2 | 2000 | 'Haya' | 'Yaser' | 'ana' |  'Golden' | 'Nablus' | 'Rafidia' | 'Family' | 1 |
    | 'true' | 'true' | 'true' | 'true' | 'true' | 3 | 2 | 2000 | 'Haya' | 'Yaser' | 'Samaana' | 'Golden House' | 'Nab' | 'Rafidia' | 'Family' | 1 |
    | 'true' | 'true' | 'true' | 'true' | 'true' | 3 | 2 | 2000 | 'Haya' | 'Yaser' | 'Samaana' | 'Golden House' | 'Nablus' | 'fidia' | 'Family' | 1 |
    | 'true' | 'false' | 'true' | 'true' | 'true' | 3 | 2 | 2000 | 'Hay' | 'Yaser' | 'sama' | 'Golden House' | 'Nablus' | 'Rafidia' | 'Family' | 0 |
    | 'true' | 'true' | 'true' | 'false' | 'true' | 3 | 2 | 2000 | 'Hay' | 'Yaser' | 'Samaana' |  'Golden House' | 'Nablus' | 'Rafidia' | 'Family' | 0 |
    | 'true' | 'true' | 'true' | 'true' | 'true' | 1 | 2 | 2000 | 'Hay' | '' | 'Samaana' |  'Golden House' | 'Nablus' | 'Rafidia' | 'Family' | 1 |
    | 'true' | 'true' | 'true' | 'true' | 'true' | 4 | 2 | 2000 | 'Haya' | '' | 'Samaana' |  'Golden House' | 'Nablus' | 'Rafidia' | 'Family' | 0 |
    | 'true' | 'true' | 'true' | 'true' | 'true' | 3 | 2 | 2500 | 'aya' | '' | '' |  'Golden House' | 'Nablus' | 'Rafidia' | 'Family' | 1 |
    | 'true' | 'true' | 'true' | 'true' | 'true' | 3 | 2 | 2000 | 'aya' | '' | 'Samaana' | 'Golden House' | 'Jenin' | 'AbuBaker' | 'Family' | 0 |
    | 'true' | 'true' | 'true' | 'true' | 'true' | 3 | 2 | 2000 | 'aya' | '' | 'Samaana' | 'Golden House' | 'Nablus' | 'Rafidia' | 'Female' | 0 |
    | 'true' | 'true' | 'true' | 'true' | 'true' | 3 | 2 | 2500 | '' | '' | '' | '' | 'Nablus' | '' | 'Family' | 2 |