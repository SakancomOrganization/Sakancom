Feature: Get Statistics
  Background: Shared Database
    Given Database is already filled

  Scenario: Without Updates
    When the admin wants to see statistics about the app
    Then number of admins must be 2
    And number of owners must be 1
    And number of tenants must be 1
    And number of buildings must be 1
    And number of houses must be 1
    And number of available houses must be 1
    And number of unavailable houses must be 0
    And number of requested houses must be 0

  Scenario: Check Unavailable Houses
    When house 1 in building 1 is set to be unavailable
    Then number of unavailable houses must be 1

  Scenario: Check Requested Houses
    When house 1 in building 1 is set to be requested
    Then number of requested houses must be 1