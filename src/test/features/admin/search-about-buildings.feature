Feature: Search About Buildings
  Background: Shared Database
    Given Database is already filled
    And Another building is added

  Scenario Outline: Search About Buildings
    When building id to search about is <buildingId>
    And building name to search about is <buildingName>
    And owner username is <username>
    And owner name to search about is is <firstName> <middleName> <lastName>
    And location to search about is <city> <street>
    Then the result of searched buildings must be <resultSize>

    Examples:
    | buildingId | buildingName | username | firstName | middleName | lastName | city | street | resultSize |
    | 1 | 'Golden House' | 'haya-sam' | 'Haya' | 'Yaser' | 'Samaana' | 'Nablus' | 'Rafidia' | 1 |
    | 1 | '' | '' | '' | '' | '' | '' | '' | 1 |
    | -1 | 'Golden' | '' | '' | '' | '' | '' | '' | 1 |
    | -1 | 'House' | 'sam' | 'Haya' | 'Yas' | '' | 'blus' | 'Rafi' | 1 |
    | -1 | '' | '' | '' | '' | '' | 'Nablus' | '' | 2 |