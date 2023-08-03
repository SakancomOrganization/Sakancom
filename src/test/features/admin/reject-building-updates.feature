Feature: Reject Building Updates
  Background: Shared Database
    Given Database is already filled

  Scenario: Reject Building Updates Success
    Given info status of building to be rejected 1 is set to be dirty
    When admin reject the changes of the building
    Then info status of the building must be rejected

  Scenario: Reject Building Updates Failed Due to Building Not Found Exception
    When admin wants to reject the changes of building 2
    Then building info status will not be rejected and building not found exception will be thrown