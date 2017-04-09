Feature: Login and list all the games A-Z
 
  Scenario: Login and list all the games A-Z
    Given I am on the web page http://games.williamhill.com
    Then I should see the web correctly charged
    When I log in the console the site version 
    And I log in the console the value of STACK cookie
    And I click login button and log into the lobby with login: testwilliam1234 and password: Test1234
    And I click over casino button
    Then I should see a list of games, count and list all games in A-Z