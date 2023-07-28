Feature: Update Personal Information
 Scenario Outline: Update Success
   Given Database is already filled
   When username of who want to update is <username>
   And field is <field>
   And value is <value>
   Then the information will be updated successfully

   Examples:
   | username | field | value |
   | 'mo-alawneh' | 'firstName' | 'Mohammed' |
   | 'mo-alawneh' | 'secondName' | 'Abd-Allateef' |
   | 'mo-alawneh' | 'lastName' | 'Alawna' |
   | 'mo-alawneh' | 'city' | 'Nablus' |
   | 'mo-alawneh' | 'street' | 'Jamal AbdAlNasser' |
   | 'mo-alawneh' | 'building' | '1122' |
   | 'mo-alawneh' | 'floor' | '3' |
   | 'mo-alawneh' | 'email' | 'mohammad12002jaba@gmail.com' |
   | 'mo-alawneh' | 'phoneNumber' | '0592283848' |
   | 'mo-alawneh' | 'birthDate' | '11/11/2007' |
   | 'mo-alawneh' | 'major' | 'Electrical Engineering' |

  Scenario Outline: Update Failed Due to Invalid Username
    Given Database is already filled
    When username of who want to update is <username>
    And field is <field>
    And value is <value>
    Then the information will not be updated due to invalid username

    Examples:
    | username | field | value |
    | 'mohammad-al' | 'firstName' | 'Mohammed' |
    | 'moh2002' | 'email' | 'mohammad12002jaba@gmail.com' |

  Scenario Outline: Update Failed Due to Number Format Exception
    Given Database is already filled
    When username of who want to update is <username>
    And field is <field>
    And value is <value>
    Then the information will not be updated and number format exception will be thrown

    Examples:
    | username | field | value |
    | 'mo-alawneh' | 'floor' | 'f' |
    | 'mo-alawneh' | 'floor' | '-1' |

  Scenario Outline: Update Failed Due to Date Parse Exception
    Given Database is already filled
    When username of who want to update is <username>
    And field is <field>
    And value is <value>
    Then the information will not be updated and date parse exception will be thrown

    Examples:
    | username | field | value |
    | 'mo-alawneh' | 'birthDate' | '11-11-2007' |
    | 'mo-alawneh' | 'birthDate' | '2007\11\11' |