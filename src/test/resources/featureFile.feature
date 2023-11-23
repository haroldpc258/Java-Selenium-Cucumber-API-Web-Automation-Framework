Feature: Cucumber and Restassured integration

  Background:
    Given I am an user at the Wikipedia WebPage

  Scenario Outline: Check that the Wikipedia article corresponding to the searched character is displayed
    When I search for the name of the character with path <number>
    Then I should be able to see the article page correctly displayed for the requested character
    Examples:
      | number |
      | 1      |
      | 2      |
      | 3      |
      | 4      |
      | 5      |

  Scenario: Check the search and edit of a random film
    When I search for a random SW film
    Then I will be able to see the article correctly displayed for the requested film
    When I try to edit the article
    Then I will be redirected to the editing page

  Scenario: Check the search and history of a random film
    When I search for a random SW film
    Then I will be able to see the article correctly displayed for the requested film
    When I try to see the history of the article
    Then I will be redirected to the history page
