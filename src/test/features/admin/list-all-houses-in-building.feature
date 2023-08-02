Feature: List All Houses In Building
  Background: Shared Database
    Given Database is already filled

  Scenario: List Success
    When admin wants to list all the houses in building 1
    Then the result size must be 1

  Scenario: List Failed Due to Building Not Found Exception
    When admin wants to list all the houses in building 2
    Then there will be no houses list and building not found exception will be thrown