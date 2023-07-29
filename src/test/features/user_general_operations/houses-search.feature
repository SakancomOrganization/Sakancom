#Feature: Houses Search
#  Scenario Outline: Houses Search
#    Given Database is already filled
#    And another house is added
#    When services are <withElectricity> <withWater> <hasInternet> <hasTelephone> <hasBalcony> <bedroomsNum> <bathroomsNum>
#    And monthly rent is <monthlyRent>
#    And location is <city> <street> <building> <floor>
#    And house classification by gender is <houseClassificationByGender>
#    Then the resulted list size will be <resultedListSize>
#
#  Examples:
#    | withElectricity |withWater | hasInternet | hasTelephone | hasBalcony | bedroomsNum | bathroomsNum | monthlyRent | city | street | building | floor | houseClassificationByGender | resultedListSize |
