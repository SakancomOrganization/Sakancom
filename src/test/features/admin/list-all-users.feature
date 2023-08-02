Feature: List All Users
  Background: Shared Database
    Given Database is already filled

  Scenario: List All Users
    When the admin wants to list all users
    Then the result size of the users must be 4