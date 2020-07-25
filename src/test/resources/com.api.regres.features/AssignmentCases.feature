Feature: GET, PUT, POST and DELETE user Operations

  @post
  Scenario: POST User
    Given I setup the "postUserRequestBody" for POST User operation
    When I send POST HTTP Request
    Then I should get a response with "201" status
    And The POST response should be valid as per the request

  @put
  Scenario: PUT User
    Given I setup the "putUserRequestBody" for PUT User operation
    When I send PUT HTTP Request
    Then I should get a response with "200" status
    And The PUT response should have a valid updatedDateTime

  @get
  Scenario: GET User
    Given I setup the parameters for GET User operation
    When I send GET HTTP Request
    Then I should get a response with "200" status
    And The GET response should be valid

  @delete
  Scenario: DELETE User
    Given I setup the "deleteUserRequestBody" for DELETE User operation
    When I send DELETE HTTP Request
    Then I should get a response with "204" status