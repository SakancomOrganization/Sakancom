Feature: Accept House Updates
  Background: Shared Database
    Given Database is already filled

  Scenario: Accept House Updates Success
    Given info status of house 1 in building 1 to be accepted is set to be dirty
    When admin accept the changes of the houses
    Then info status of the house must be accepted

  Scenario: Accept House Updates Failed Due to Building Not Found Exception
    When building id is 2 and house id is 1 to be accepted
    Then info status of the house will not be accepted a building not found exception will be thrown

  Scenario: Accept House Updates Failed Due to House Not Found Exception
    When building id is 1 and house id is 2 to be accepted
    Then info status of the house will not be accepted a house not found exception will be thrown