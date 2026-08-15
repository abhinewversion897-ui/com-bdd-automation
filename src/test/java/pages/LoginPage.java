package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	private By txtUsername = By.name("username");
	private By txtPassword = By.name("password");
	private By btnLogin = By.xpath("//button[@type='submit']");
	private By dashboard = By.xpath("//h6[text()='Dashboard']");
	private By invalidWarningMessage = By.xpath("//p[contains(@class,'oxd-alert-content-text')]");
	private By requiredWarningMessage = By.xpath("//span[contains(@class,'oxd-input-group__message')]");

	public void enterUsername(String username) {
		enterText(txtUsername, username);
	}

	public void enterPassword(String password) {
		enterText(txtPassword, password);
	}

	public void clickLogin() {
		click(btnLogin);
	}

	public boolean isDashboardDisplayed() {
		return isDisplayed(dashboard);
	}
	
	public boolean isInvalidmssgDisplayed() {
		
		return isDisplayed(invalidWarningMessage);
	}
	
	public void enterUsername_and_Password(String username,String password) {
		enterText(txtUsername, username);
		enterText(txtPassword, password);
	}
	
	public WebElement getInvalidWarningMessageElement() {

	    return getElement(invalidWarningMessage);

	}
	
	public List<WebElement> getRequiredMessages() {
	    return getElements(requiredWarningMessage);
	}
}
