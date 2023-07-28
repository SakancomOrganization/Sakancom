Feature: Sign Up
  Scenario Outline: Sign Up Successfully
    Given Database is already filled
    When newUsername is <username>
    And newPassword is <password>
    And usertype is <userType>
    And name is <firstName> <secondName> <lastName>
    And location is <city> <street> <building> <floor>
    And contactInfo are <email> <phoneNumber> <birthDate> <major>
    Then the user will sign up successfully

    Examples:
    | username | password | userType | firstName | secondName | lastName | city | street | building | floor | email | phoneNumber | birthDate | major |
    | 'anas-al' | 'ANAS1234' | 'admin' | 'Anas' | 'AbdAllateef' | 'Alawneh' | 'Jenin' | 'AbuBaker' | '4070' | 2 | 'mohammad12002jaba@stu.najah.edu' | '0599716597' | '25/07/2009' | 'School Student' |
    | 'naya2020' | 'NaYa2020' | 'owner' | 'Naya' | 'Khaled' | 'Khader' | 'Nablus' | 'AlEtihad' | '1122' | 1 | 's12028067@stu.najah.edu' | '0599552020' | '17/06/2020' | ' N/A ' |

  Scenario Outline: Sign Up Failed
    Given Database is already filled
    When newUsername is <username>
    And newPassword is <password>
    And usertype is <userType>
    And name is <firstName> <secondName> <lastName>
    And location is <city> <street> <building> <floor>
    And contactInfo are <email> <phoneNumber> <birthDate> <major>
    Then the user will not sign up

    Examples:
      | username | password | userType | firstName | secondName | lastName | city | street | building | floor | email | phoneNumber | birthDate | major |
      | 'mo-alawneh' | 'ANAS1234' | 'admin' | 'Anas' | 'AbdAllateef' | 'Alawneh' | 'Jenin' | 'AbuBaker' | '4070' | 2 | 'mohammad12002jaba@stu.najah.edu' | '0599716597' | '25/07/2009' | 'School Student' |
      | 'najat-mansour' | 'NaYa2020' | 'owner' | 'Naya' | 'Khaled' | 'Khader' | 'Nablus' | 'AlEtihad' | '1122' | 1 | 's12028067@stu.najah.edu' | '0599552020' | '17/06/2020' | ' N/A ' |