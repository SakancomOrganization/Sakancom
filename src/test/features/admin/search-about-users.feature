Feature: Search About Users
  Background: Shared Database
    Given Database is already filled

  Scenario Outline: Search About Users
    When username is <username>
    And user type is <userType>
    And name is <firstName> <middleName> <lastName>
    And email is <email>
    And phoneNumber is <phoneNumber>
    And email is <email>
    And major is <major>
    Then the result users size must be <expectedSize>

    Examples:
    | username | userType | firstName | middleName | lastName | email | phoneNumber | major | expectedSize |
    | 'mo-alawneh' | 'admin' | 'MohaMMad' | 'AbdAllateef' | 'ala' | 'mo.a.alawneh@gmail.com' | '0592838433' | 'Computer Engineering' | 1 |
    | 'mo-alawneh' | '' | '' | '' | '' | '' | '' | '' | 1 |
    | '' | 'admin' | '' | '' | '' | '' | '' | '' | 2 |
    | '' | 'owner' | '' | '' | '' | '' | '' | '' | 1 |
    | '' | 'tenant' | '' | '' | '' | '' | '' | '' | 1 |
    | '' | 'admin' | 'najat' | '' | '' | '' | '' | '' | 1 |
    | '' | 'admin' | '' | 'sam' | '' | '' | '' | '' | 1 |
    | '' | 'admin' | '' | '' | 'ans' | '' | '' | '' | 1 |
    | '' | '' | '' | '' | '' | 'mo.a.alawneh' | '' | '' | 1 |
    | '' | '' | '' | '' | '' | '' | '0592838433' | '' | 1 |
    | '' | '' | '' | '' | '' | '' | '' | 'Engineering' | 4 |
    | '' | 'admin' | '' | '' | '' | '' | '' | 'computer engineering' | 2 |
    | '' | '' | 'mohammad' | '' | '' | '' | '' | 'computer engineering' | 1 |
    | '' | '' | '' | '' | '' | '' | '' | '' | 4 |
    | '' | 'admin' | 'mahmoud' | '' | '' | '' | '' | '' | 0 |
    | 'haya-sam' | 'admin' | '' | '' | '' | '' | '' | '' | 0 |
    | 'than@mare' | 'tenant' | '' | '' | '' | '' | '0592' | 'Computer Engineering' | 0 |