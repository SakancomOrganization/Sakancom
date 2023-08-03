Feature: Accept Building Updates
  Background: Shared Database
    Given Database is already filled

  Scenario: Accept Building Updates Success
    Given info status of building to be accepted 1 is set to be dirty
    When admin accept the changes of the building
    Then info status of the building must be accepted

  Scenario: Accept Building Updates Failed Due to Building Not Found Exception
    When admin wants to accept the changes of building 2
    Then building info status will not be accepted and building not found exception will be thrown