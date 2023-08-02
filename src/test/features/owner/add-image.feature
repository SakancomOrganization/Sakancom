Feature: Add Image Feature
  Background: Shared Database
    Given Database is already filled

    Scenario Outline: Add Image Success
      When current owner who wants to add an image is <username>
      And building contains the house of the added image is <buildingId>
      And house contains the added image is <houseId>
      And image is <image>
      Then the image will be added successfully

      Examples:
      | username | buildingId | houseId | image |
      | 'haya-sam' | 1 | 1 | 'bedroom 1 image' |
      | 'haya-sam' | 1 | 1 | 'bedroom 2 image' |

  Scenario Outline: Add Image Failed Due to Already Found Element Exception
    When current owner who wants to add an image is <username>
    And building contains the house of the added image is <buildingId>
    And house contains the added image is <houseId>
    And <image> is already added to <houseId> in <buildingId>
    And image is <image>
    Then the image will not be added successfully and already found exception element will thrown

    Examples:
    | username | buildingId | houseId | image |
    | 'haya-sam' | 1 | 1 | 'bedroom 1 image' |

  Scenario Outline: Add Image Failed Due to Building Not Found Exception
    When current owner who wants to add an image is <username>
    And building contains the house of the added image is <buildingId>
    And house contains the added image is <houseId>
    And image is <image>
    Then the image will not be added successfully and building not found exception will be thrown

    Examples:
    | username | buildingId | houseId | image |
    | 'haya-sam' | 2 | 1 | 'bedroom 1 image' |
    | 'mo-alawneh' | 1 | 1 | 'bedroom 1 image' |

  Scenario Outline: Add Image Failed Due to House Not Found Exception
    When current owner who wants to add an image is <username>
    And building contains the house of the added image is <buildingId>
    And house contains the added image is <houseId>
    And image is <image>
    Then the image will not be added successfully and house not found exception will be thrown

    Examples:
    | username | buildingId | houseId | image |
    | 'haya-sam' | 1 | 2 | 'bedroom 1 image' |