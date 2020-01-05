Feature: Test CRUD methods in User REST API testing
Scenario: Add user record
Given I Set POST user service api endpoint
When I set request Headers 
And Send a POST HTTP request
Then I receive valid Response