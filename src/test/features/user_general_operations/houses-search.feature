Feature: Houses Search
  Scenario Outline: Houses Search
    Given Database is already filled
    And another house is added
    When services are <withElectricity> <withWater> <hasInternet> <hasTelephone> <hasBalcony> <bedroomsNum> <bathroomsNum>
    And monthly rent is <monthlyRent>
    And location is <city> <street>
    And house classification by gender is <houseClassificationByGender>
    Then the resulted list size will be <resultedListSize>

  Examples:
    | withElectricity |withWater | hasInternet | hasTelephone | hasBalcony | bedroomsNum | bathroomsNum | monthlyRent | city | street | houseClassificationByGender | resultedListSize |
    | 'true' | 'true' | 'true' | 'true' | 'true' | 3 | 2 | 2000 | 'Nablus' | 'Rafidia' | 'Family' | 1 |
    | 'true' | 'false' | 'true' | 'true' | 'true' | 3 | 2 | 2000 | 'Nablus' | 'Rafidia' | 'Family' | 0 |
    | 'true' | 'true' | 'true' | 'false' | 'true' | 3 | 2 | 2000 | 'Nablus' | 'Rafidia' | 'Family' | 0 |
    | 'true' | 'true' | 'true' | 'true' | 'true' | 1 | 2 | 2000 | 'Nablus' | 'Rafidia' | 'Family' | 1 |
    | 'true' | 'true' | 'true' | 'true' | 'true' | 4 | 2 | 2000 | 'Nablus' | 'Rafidia' | 'Family' | 0 |
    | 'true' | 'true' | 'true' | 'true' | 'true' | 3 | 2 | 2500 | 'Nablus' | 'Rafidia' | 'Family' | 1 |
    | 'true' | 'true' | 'true' | 'true' | 'true' | 3 | 2 | 2000 | 'Jenin' | 'AbuBaker' | 'Family' | 0 |
    | 'true' | 'true' | 'true' | 'true' | 'true' | 3 | 2 | 2000 | 'Nablus' | 'Rafidia' | 'Female' | 0 |
    | 'true' | 'true' | 'true' | 'true' | 'true' | 3 | 2 | 2500 | 'Nablus' | '' | 'Family' | 2 |