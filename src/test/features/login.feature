Feature: Login
  Scenario Outline: Login Successfully
    Given Database is already filled
    When username is <username>
    And password is <password>
    Then the user will log in successfully

    Examples:
      | username | password |
      | 'mo-alawneh' | 'Mohammad62002' |
      | 'najat-mansour' | 'Najat12003' |
      | 'haya-sam' | 'HaySam' |
      | 'than@mare' | 'tHaNaMaRee' |

  Scenario Outline: Login Failed
    Given Database is already filled
    When username is <username>
    And password is <password>
    Then the user will not log in successfully

    Examples:
      | username | password |
      | 'mohammad-al' | 'Mohammad62002' |
      | 'najat-mansour' | 'Najat22003' |
      | 'haySam' | 'HaySam' |
      | 'than@mare' | 'thanaMaree' |

    Scenario Outline: Forget Password With Valid Username
      Given Database is already filled
      When username is <username>
      Then the user will receive a new password on the email

      Examples:
        | username |
        | 'mo-alawneh' |
        | 'najat-mansour' |

    Scenario Outline: Forget Password With Invalid Username
      Given Database is already filled
      When username is <username>
      Then the user will not receive a new password on the email

      Examples:
        | username |
        | 'haySam' |
        | 'thana@mare' |