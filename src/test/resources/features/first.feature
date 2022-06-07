Feature: Login to localhost

  Scenario: Login with valid credentials
   Given I am logged in
   When I open the sidebar menu
   Then I am able to navigate to Pages manager