Feature: As a User I want to login and place an football bet
          and my account should reflects and get updated accordingly

  @wip @HappyPath
  Scenario Outline: Login and place bet
    Given User is on "home" page
    When User clicks login button
    And Input correct username and password and submit
    Then User is redirected to bets page
    And User navigates to "<Sport>" event
    When User adds first active selection to bet slip
    And User places a bet of "<Bet Amount>"
    Then Total stake and Total return is validated
    And User balance is reduced by "<Bet Amount>"
    Examples:
    |Sport|Bet Amount|
    |Football|0.05   |
#    |Tennis  |0.05   |