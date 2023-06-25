Feature: Login
  Scenario Outline: Login Successfully
    When username is <username>
    And password is <password>
    And type is <type>
    Then the user will log in successfully

    Examples:
      | username | password | type |
      | 'mohammad-al' | 'Mohammad62002' | 'admin' |
      | 'najat-mansour' | 'Najat12003' | 'admin' |
      | 'hay-sam' | 'HaySam' | 'owner' |
      | 'than@mare' | 'tHaNaMaRee' | 'tenant' |

  Scenario Outline: Login Failed
    When username is <username>
    And password is <password>
    And type is <type>
    Then the user will not log in successfully

    Examples:
      | username | password | type |
      | 'mohammad-al' | 'Mohammad62002' | 'owner' |
      | 'najat-mansour' | 'Najat22003' | 'admin' |
      | 'haySam' | 'HaySam' | 'owner' |
      | 'than@mare' | 'tHaNaMaRee' | 'admin' |