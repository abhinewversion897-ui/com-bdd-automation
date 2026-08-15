package stepDefinitions;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BasePage;
import pages.DashboardPage;
import utilities.JavaScriptUtils;
import utilities.WaitUtils;
import utilities.WindowHandles;

public class DashboardDefinition {

	DashboardPage dashPage = new DashboardPage(DriverFactory.getDriver());
	JavaScriptUtils js = new JavaScriptUtils(DriverFactory.getDriver());
	WaitUtils utils = new WaitUtils(DriverFactory.getDriver());
	WindowHandles winhandle = new WindowHandles(DriverFactory.getDriver());

	@Then("User should view all the menuitems in OrangeHRM Dashboard page")
	public void user_should_view_all_the_menuitems_in_orange_hrm_dashboard_page(DataTable dataTable) {

		List<String> expectedMenus = dataTable.asList();

		List<WebElement> menuItemsList = dashPage.getMenuItems();

		List<String> actualMenus = menuItemsList.stream().map(WebElement::getText).toList();

		Assert.assertEquals(actualMenus, expectedMenus);

	}

	@Then("User scroll to Quick Launch section and verifies all the quick launch items are displayed")
	public void User_scroll_to_Quick_Launch_section_and_validate_all_the_quick_launch_items(DataTable datatable) {

		js.scrollToElement(dashPage.getQuickLunchHeading());

		List<String> expectedQuickLaunchItems = datatable.asList();

		List<String> actualQuickLaunchItems = dashPage.getAssignLeaveElements().stream().map(WebElement::getText)
				.toList();

		Assert.assertEquals(actualQuickLaunchItems, expectedQuickLaunchItems);

	}

	@When("User should be able to view below following Sections in Dashboard page")
	public void verifyDashboardSections(DataTable dataTable) {

		List<String> sections = dataTable.asList();

		for (String section : sections) {

			Assert.assertTrue(dashPage.isSectionDisplayed(section), "Dashboard section is not displayed: " + section);
		}
	}

	@When("User clicks on upgrade button")
	public void click_on_upgrade_button() {

		dashPage.getupgradeButton().click();

	}

	@Then("User navigates to upgrade to advanced page.")
	public void new_upgrade_to_advanced_page() {
		String parentWindow = "OrangeHRM";
		String actualUrl = "https://orangehrm.com/open-source/upgrade-to-advanced";
		winhandle.switchToChildWindow(parentWindow);

		Assert.assertTrue(actualUrl.contains("/upgrade-to-advanced"), "Expected Dashboard URL but found: " + actualUrl);

	}

	@When("User clicks on Help icon button")
	public void click_on_HelpIcon_button() {

		dashPage.gethelpIconButton().click();

	}

	@Then("User navigates to help page.")
	public void new_tab_navigates_to_help_page() throws InterruptedException {
		String actualUrl = "https://starterhelp.orangehrm.com/hc/en-us";
		Thread.sleep(2000);
		winhandle.switchToWindowByUrl(actualUrl);

		Assert.assertTrue(actualUrl.contains("/starterhelp.orangehrm.com"),
				"Expected Dashboard URL but found: " + actualUrl);

	}
}
