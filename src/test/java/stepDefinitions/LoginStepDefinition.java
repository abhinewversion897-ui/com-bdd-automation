package stepDefinitions;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.WaitUtils;

public class LoginStepDefinition {

	LoginPage loginpage = new LoginPage(DriverFactory.getDriver());
	WaitUtils utils = new WaitUtils(DriverFactory.getDriver());

	@Given("User launches OrangeHRM application")
	public void user_launches_OrangeHRM_application() {
		System.out.println("Application Launched Successfully");

	}

	@When("User Enters Valid username and password")
	public void user_enters_username_password() {

		loginpage.enterUsername(ConfigReader.getProperty("username"));
		loginpage.enterPassword(ConfigReader.getProperty("password"));
	}

	@And("User clicks on Login button")
	public void click_on_login_button() {
		loginpage.clickLogin();

	}

	@Then("User should navigate to OrangeHRM Dashboard page")
	public void verify_OrangeHRM_Dashboard_page() {

		Assert.assertTrue(loginpage.isDashboardDisplayed());
	}
	@When("User Enters valid username {string} and invalid password {string}")
	@When("User Enters invalid username {string} and valid password {string}")
	public void enters_username_password(String username, String password) {

		loginpage.enterUsername(username);
		loginpage.enterPassword(password);
	}
	
	@When("User does not enters username {string} and password {string}")
	public void user_does_not_enter_username_and_password(String username, String password) {
		loginpage.enterUsername(username);
		loginpage.enterPassword(password);
	}

	@Then("invalid warning message should be displayed")
	public void invalid_warning_message() {

		Assert.assertTrue(loginpage.isInvalidmssgDisplayed());
	}
	
	@Then("valid Required warning message should be displayed")
	public void verify_Required_warning_Message() {
		
		  List<WebElement> requiredMessages = loginpage.getRequiredMessages();

		    Assert.assertEquals(requiredMessages.size(), 2, "Two validation messages should be displayed.");

		    for (WebElement message : requiredMessages) {
		        Assert.assertEquals(message.getText().trim(), "Required");
		    }
		
		

		
	}

}
