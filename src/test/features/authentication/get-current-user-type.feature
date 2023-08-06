Feature: Get Current User Type
  Background: Shared Database
    Given Database is already filled

  Scenario Outline:
    When current username is <username>
    Then current user type is <userType>

  Examples:
    | username | userType |
    | 'mo-alawneh' | 'admin' |
    | 'haya-sam' | 'owner' |
    | 'than@mare' | 'tenant' |