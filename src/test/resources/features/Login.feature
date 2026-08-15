Feature: OrangeHRM Login Functionality

Background:
   Given User launches OrangeHRM application

@Smoke
Scenario: Verify user can login with valid credentials
 

  When User Enters Valid username and password
  And User clicks on Login button
  Then User should navigate to OrangeHRM Dashboard page
 
 @Regression
 Scenario Outline: Verify invalid warning message when user login with invalid username and valid password
 

  When User Enters invalid username "<username>" and valid password "<password>"
  And  User clicks on Login button
  Then invalid warning message should be displayed
  
  Examples:
  |username|password|
  |Adminn|Admin1234| 
  
  @Smoke
  Scenario Outline: Verify when user enters valid username "<username>" and invalid password "<password>"

    When User Enters valid username "<username>" and invalid password "<password>"
    And  User clicks on Login button
    Then invalid warning message should be displayed
    
    Examples:
    |username|password|
    |Admin|Admin123487|
  
  @claria_plus 
  Scenario Outline: Verify validation when username and password is blank
  
 
    When User does not enters username "<username>" and password "<password>"
    And  User clicks on Login button
    Then valid Required warning message should be displayed
    
    Examples:
    |username|password|
    | | |
  