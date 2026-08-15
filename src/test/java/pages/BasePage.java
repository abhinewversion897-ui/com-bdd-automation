package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.WaitUtils;

public class BasePage {

	protected WebDriver driver;
	protected WaitUtils waitUtils;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.waitUtils = new WaitUtils(driver);
	}

	public void click(By locator) {

		WebElement element = driver.findElement(locator);

		waitUtils.waitForClickable(element);

		element.click();

	}

	public void enterText(By locator, String text) {

		WebElement element = driver.findElement(locator);

		waitUtils.waitForVisibility(element);

		element.clear();

		element.sendKeys(text);

	}

	public String getText(By locator) {

		WebElement element = driver.findElement(locator);

		waitUtils.waitForVisibility(element);

		return element.getText();

	}

	public boolean isDisplayed(By locator) {

		WebElement element = driver.findElement(locator);

		waitUtils.waitForVisibility(element);

		return element.isDisplayed();

	}

	public WebElement getElement(By locator) {

		WebElement element = driver.findElement(locator);

		waitUtils.waitForVisibility(element);

		return element;

	}

	public List<WebElement> getElements(By locators) {

		return waitUtils.waitForAllElements(locators);
	}

}
