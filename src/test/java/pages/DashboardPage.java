package pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {

	WebDriver driver;

	private By menuItems = By.xpath("//span[contains(@class,'oxd-main-menu-item--name')]");
	private By quickLaunchHeading = By.xpath("// p[text()='Quick Launch']");
	private By quickLunchItems = By.cssSelector(".orangehrm-quick-launch-heading");
	private By dashboardTitle = By.xpath("//h6[contains(@class,'oxd-topbar-header-breadcrumb-module')]");
	private By upgradeButton = By.xpath("//button[contains(@class,'orangehrm-upgrade-button')]");
	private By userPofileSection = By.xpath("//div[contains(@class,'oxd-topbar-header-userarea')]/ul/li");
	private By helpIconButton = By.xpath("//div[contains(@class,'oxd-topbar-body-nav-slot')]/button");
	private Map<String, By> dashboardSections = new HashMap();

	{
		dashboardSections.put("Dashboard title",
				By.xpath("//h6[contains(@class,'oxd-topbar-header-breadcrumb-module')]"));

		dashboardSections.put("Upgrade button", By.xpath("//button[contains(@class,'orangehrm-upgrade-button')]"));

		dashboardSections.put("User profile section", By.xpath("//span[contains(@class,'oxd-userdropdown-tab')]"));

		dashboardSections.put("Help icon", By.xpath("//div[contains(@class,'oxd-topbar-body-nav-slot')]/button"));

		dashboardSections.put("Upgrade", By.xpath("//div[contains(@class,'oxd-topbar-header-userarea')]/ul/li"));
	}

	public DashboardPage(WebDriver driver) {
		this.driver = driver;

	}

	public List<WebElement> getMenuItems() {
		return driver.findElements(menuItems);

	}

	public List<WebElement> getAssignLeaveElements() {

		return driver.findElements(quickLunchItems);
	}

	public WebElement getQuickLunchHeading() {

		return driver.findElement(quickLaunchHeading);
	}

	public WebElement getDashboardTitle() {

		return driver.findElement(dashboardTitle);

	}

	public WebElement getupgradeButton() {

		return driver.findElement(upgradeButton);
	}

	public WebElement getuserPofileSection() {

		return driver.findElement(userPofileSection);
	}

	public WebElement gethelpIconButton() {

		return driver.findElement(helpIconButton);
	}

	public boolean isSectionDisplayed(String sectionName) {

		By locator = dashboardSections.get(sectionName);

		if (locator == null) {
			throw new IllegalArgumentException("No locator found for dashboard section: " + sectionName);
		}

		return driver.findElement(locator).isDisplayed();
	}
}
