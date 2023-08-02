Feature: Add Building
  Background: Shared Database
    Given Database is already filled

  Scenario Outline: Add Success
    When added building name is <name>
    And added building owner has a username <username>
    And added building location is <city> <street>
    Then Building will be added successfully

  Examples:
  | name | username | city | street |
  | 'love house' | 'haya-sam' | 'Nablus' | 'Schools Street' |

  Scenario Outline: Add Failed Due to Already Found Element Exception
    When added building name is <name>
    And added building owner has a username <username>
    And added building location is <city> <street>
    Then Building will not be added successfully and already found element exception will be thrown

  Examples:
  | name | username | city | street |
  | 'golden house' | 'haya-sam' | 'Nablus' | 'Schools Street' |