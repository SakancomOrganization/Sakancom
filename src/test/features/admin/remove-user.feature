Feature: Remove User
  Background: Shared Database
    Given Database is already filled

  Scenario: Remove User Successfully
    When username of the user to be removed is 'than@mare'
    Then this user will be removed

  Scenario: Remove User Failed Due to User Not Found Exception
    When username of the user to be removed is 'ahmad@1212'
    Then nothing will be removed and a user not found exception will be thrown

  Scenario: Remove User Failed Due to Admin Cannot Be Removed Exception
    When username of the user to be removed is 'najat-mansour'
    Then nothing will be removed and an admin cannot be removed exception will be thrown