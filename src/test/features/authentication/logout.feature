Feature: Logout
 Scenario Outline: Logout
    Given Database is already filled
    And a user is already logged in with <username> and <password>
    When user logged out
    Then the current user will be null

   Examples:
   | username | password |
   | 'mo-alawneh' | 'Mohammad62002' |
   | 'najat-mansour' | 'Najat12003' |
   | 'haya-sam' | 'HaySam' |
   | 'than@mare' | 'tHaNaMaRee' |