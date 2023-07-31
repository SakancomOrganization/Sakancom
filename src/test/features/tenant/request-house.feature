Feature: Request House
  Background: Shared Database
    Given Database is already filled

  Scenario: Tenant Request Success
    When building id of the requested house is 1
    And house id of the requested house is 1
    And the current tenant username is 'than@mare'
    Then the sale status in the house sale contract of the house will be requested
    And the tenant in the house sale contract will be correct

  Scenario: Tenant Request Success Failed Due to Building Not Found Exception
    When building id of the requested house is 2
    And house id of the requested house is 1
    And the current tenant username is 'than@mare'
    Then the request failed and building not found exception will be thrown

  Scenario: Tenant Request Failed Due to House Not Found Exception
    When building id of the requested house is 1
    And house id of the requested house is 3
    And the current tenant username is 'than@mare'
    Then the request failed and house not found exception will be thrown