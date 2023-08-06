Feature: Update Email
  Background: Shared Database
    Given Database is already filled

    Scenario Outline: Update Email Success
      When username of user who wants to update the email is <username>
      And the new email is <email>
      Then the email will be updated successfully

      Examples:
        | username | email |
        | 'najat-mansour' | 'mo.a.alawneh@gmail.com' |
        | 'mo-alawneh' | 's12028067@stu.najah.edu' |

    Scenario Outline: Update Email Failed Due to User Not Found Exception
      When username of user who wants to update the email is <username>
      And the new email is <email>
      Then the email will not be updated and a user not found exception will be thrown

      Examples:
        | username | email |
        | 'mansour-najat' | 'mo.a.alawneh@gmail.com' |
        | 'ahmad' | 's12028067@stu.najah.edu' |

    Scenario Outline: Update Email Failed Due to Invalid Email Format Exception
      When username of user who wants to update the email is <username>
      And the new email is <email>
      Then the email will not be updated and an invalid email format exception will be thrown

      Examples:
        | username | email |
        | 'najat-mansour' | 'f@f' |
        | 'mo-alawneh' | 's12028067@najah.com' |