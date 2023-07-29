Feature: Update Personal Information
  Background: Shared Database
    Given Database is already filled

 Scenario Outline: Update Success
   When <username> is already logged in
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
    When <username> is already logged in
    And field is <field>
    And value is <value>
    Then the information will not be updated due to invalid username

    Examples:
    | username | field | value |
    | 'mohammad-al' | 'firstName' | 'Mohammed' |
    | 'moh2002' | 'email' | 'mohammad12002jaba@gmail.com' |

  Scenario Outline: Update Failed Due to Number Format Exception
    When <username> is already logged in
    And field is <field>
    And value is <value>
    Then the information will not be updated and number format exception will be thrown

    Examples:
    | username | field | value |
    | 'mo-alawneh' | 'floor' | 'f' |

  Scenario Outline: Update Failed Due to Unacceptable Value Exception
    When <username> is already logged in
    And field is <field>
    And value is <value>
    Then the information will not be updated and unacceptable value exception will be thrown

    Examples:
    | username | field | value |
    | 'mo-alawneh' | 'floor' | '-1' |

  Scenario Outline: Update Failed Due to Date Parse Exception
    When <username> is already logged in
    And field is <field>
    And value is <value>
    Then the information will not be updated and date parse exception will be thrown

    Examples:
    | username | field | value |
    | 'mo-alawneh' | 'birthDate' | '11-11-2007' |
    | 'mo-alawneh' | 'birthDate' | '2007\11\11' |