package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {

	private WebDriver driver;
	private JavascriptExecutor js;

	public JavaScriptUtils(WebDriver driver) {
		this.driver = driver;
		this.js = (JavascriptExecutor) driver;
	}

	// JavaScript Click
	public void clickElement(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	}

	// Scroll to Element
	public void scrollToElement(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	// Scroll to Bottom
	public void scrollToBottom() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	// Scroll to Top
	public void scrollToTop() {
		js.executeScript("window.scrollTo(0, 0);");
	}

	// Scroll by Pixels
	public void scrollBy(int x, int y) {
		js.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
	}

	// Highlight Element
	public void highlightElement(WebElement element) {
		js.executeScript("arguments[0].style.border='3px solid red';", element);
	}

	// Set Value
	public void setValue(WebElement element, String value) {
		js.executeScript("arguments[0].value=arguments[1];", element, value);
	}

	// Get Page Title
	public String getTitle() {
		return (String) js.executeScript("return document.title;");
	}

	// Refresh Browser
	public void refreshPage() {
		js.executeScript("history.go(0);");
	}

	// Zoom Page
	public void zoomPage(String percentage) {
		js.executeScript("document.body.style.zoom='" + percentage + "'");
	}
}