Feature: Reject House Updates
  Background: Shared Database
    Given Database is already filled

  Scenario: Reject House Updates Success
    Given info status of house 1 in building 1 to be rejected is set to be dirty
    When admin reject the changes of the houses
    Then info status of the house must be rejected

  Scenario: Reject House Updates Failed Due to Building Not Found Exception
    When building id is 2 and house id is 1 to be rejected
    Then info status of the house will not be rejected a building not found exception will be thrown

  Scenario: Reject House Updates Failed Due to House Not Found Exception
    When building id is 1 and house id is 2 to be rejected
    Then info status of the house will not be rejected a house not found exception will be thrown