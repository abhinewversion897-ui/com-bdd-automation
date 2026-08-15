Feature:Validation of Dashboard

Background: 
 Given User launches OrangeHRM application
 When User Enters Valid username and password
  And User clicks on Login button


 
 
 @First
 Scenario:Verify the Dashboard page sections
 Then User should be able to view below following Sections in Dashboard page
 |Dashboard title|
 |Upgrade button|
 |User profile section|
 |Help icon|
 |Upgrade|
 
 @First
 Scenario:Verify user navigates to upgrade-to-advanced page when clicks on Upgrade button
  When User clicks on upgrade button
  Then User navigates to upgrade to advanced page.
  
  @First1
 Scenario:Verify user navigates to help page when clicks on Help icon
  When User clicks on Help icon button
  Then User navigates to help page.
 