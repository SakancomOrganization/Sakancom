Feature: Sign Up
  Background: Shared Database
    Given Database is already filled

  Scenario Outline: Sign Up Successfully
    When newUsername is <username>
    And newPassword is <password>
    And usertype is <userType>
    And name is <firstName> <secondName> <lastName>
    And location is <city> <street> <building> <floor>
    And contactInfo are <email> <phoneNumber> <birthDate> <major>
    Then the user will sign up successfully

    Examples:
    | username | password | userType | firstName | secondName | lastName | city | street | building | floor | email | phoneNumber | birthDate | major |
    | 'anas-al' | 'AnAs1234#' | 'admin' | 'Anas' | 'AbdAllateef' | 'Alawneh' | 'Jenin' | 'AbuBaker' | '4070' | 2 | 'mohammad12002jaba@stu.najah.edu' | '0599716597' | '25/07/2009' | 'School Student' |
    | 'naya2020' | 'NaYa2020@' | 'owner' | 'Naya' | 'Khaled' | 'Khader' | 'Nablus' | 'AlEtihad' | '1122' | 1 | 's12028067@stu.najah.edu' | '0599552020' | '17/06/2020' | ' N/A ' |

  Scenario Outline: Sign Up Failed Due to Already Found Username
    When newUsername is <username>
    And newPassword is <password>
    And usertype is <userType>
    And name is <firstName> <secondName> <lastName>
    And location is <city> <street> <building> <floor>
    And contactInfo are <email> <phoneNumber> <birthDate> <major>
    Then the user will not sign up due to already found username

    Examples:
      | username | password | userType | firstName | secondName | lastName | city | street | building | floor | email | phoneNumber | birthDate | major |
      | 'mo-alawneh' | 'Mohammad12002@' | 'admin' | 'Anas' | 'AbdAllateef' | 'Alawneh' | 'Jenin' | 'AbuBaker' | '4070' | 2 | 'mohammad12002jaba@stu.najah.edu' | '0599716597' | '25/07/2009' | 'School Student' |
      | 'najat-mansour' | 'Najat62003$' | 'owner' | 'Naya' | 'Khaled' | 'Khader' | 'Nablus' | 'AlEtihad' | '1122' | 1 | 's12028067@stu.najah.edu' | '0599552020' | '17/06/2020' | ' N/A ' |

  Scenario Outline: Sign Up Failed Due to Weak Password
    When newUsername is <username>
    And newPassword is <password>
    And usertype is <userType>
    And name is <firstName> <secondName> <lastName>
    And location is <city> <street> <building> <floor>
    And contactInfo are <email> <phoneNumber> <birthDate> <major>
    Then the user will not sign up due weak password and weak password exception will be thrown

    Examples:
      | username | password | userType | firstName | secondName | lastName | city | street | building | floor | email | phoneNumber | birthDate | major |
      | 'mo-al' | 'Mohammad12002' | 'admin' | 'Anas' | 'AbdAllateef' | 'Alawneh' | 'Jenin' | 'AbuBaker' | '4070' | 2 | 'mohammad12002jaba@stu.najah.edu' | '0599716597' | '25/07/2009' | 'School Student' |