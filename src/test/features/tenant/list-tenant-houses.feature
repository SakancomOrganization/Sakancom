Feature: List Tenant Houses
  Background: Shared Database
    Given Database is already filled

  Scenario:
    When 'than@mare' is the current user
    And she bought house 1 in building 1
    And she list her own houses as a tenant
    Then the result must be 1
