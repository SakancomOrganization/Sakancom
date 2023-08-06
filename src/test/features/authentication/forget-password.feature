Feature: Forget Password
  Background: Shared Database
    Given Database is already filled

    Scenario Outline: Forget Password With Valid Username
      When username who forgets the password is <username>
      Then the user will receive a new password on the email

      Examples:
        | username |
        | 'mo-alawneh' |
        | 'najat-mansour' |

    Scenario Outline: Forget Password With Invalid Username
      When username who forgets the password is <username>
      Then the user will not receive a new password on the email and a user not found exception will be thrown

      Examples:
        | username |
        | 'haySam' |
        | 'thana@mare' |