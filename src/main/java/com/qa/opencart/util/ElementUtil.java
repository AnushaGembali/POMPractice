package com.qa.opencart.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavascriptExecutorUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		if (DriverFactory.highlight) {
			jsUtil = new JavascriptExecutorUtil(driver);
		}
	}

	public WebElement getElement(By locator) {
		return this.driver.findElement(locator);
	}

	public void doSendkeys(By locator, String value) {
		WebElement element = driver.findElement(locator);
		element.clear();
		if (DriverFactory.highlight) {
			jsUtil.flash(element);
		}
		element.sendKeys(value);
	}

	public void doActionsSendKeys(By locator, String value) {
		Actions action = new Actions(driver);
		action.sendKeys(getElement(locator), value).build().perform();
	}

	public void doClick(By locator) {
		WebElement element = this.getElement(locator);
		if (DriverFactory.highlight) {
			jsUtil.flash(element);
		}
		element.click();		
	}

	public void doActionsClick(By locator) {
		Actions action = new Actions(driver);
		action.click(getElement(locator)).build().perform();
	}

	public String doGetText(By locator) {
		return this.getElement(locator).getText();
	}

	public boolean doElementIsDisplayed(By locator) {
		return this.getElement(locator).isDisplayed();
	}

	public String doGetElementAttribute(By locator, String attributeName) {
		return this.getElement(locator).getAttribute(attributeName);
	}

	public List<WebElement> doGetAllElements(By locator) {
		return this.driver.findElements(locator);
	}

	public int doGetTotalElementsCount(By locator) {
		return this.doGetAllElements(locator).size();
	}

	public List<String> doGetElementAttributes(By locator, String AttributeName) {
		List<String> attrValueList = new ArrayList<>();
		List<WebElement> allElements = this.doGetAllElements(locator);
		for (WebElement eachElement : allElements) {
			String attributeValue = eachElement.getAttribute(AttributeName);
			attrValueList.add(AttributeName);
//			System.out.println(attributeValue);
		}
		return attrValueList;
	}

	public List<String> doGetElementsTextList(By locator) {
		List<String> eleTextList = new ArrayList<>();
		List<WebElement> allElements = this.doGetAllElements(locator);
		for (WebElement eachElement : allElements) {
			String text = eachElement.getText();
//			System.out.println(text);
			eleTextList.add(text.trim());
		}
		return eleTextList;

	}

	// ************************** SELECT BASED DROP DOWN UTILS
	// ****************************//

	public void doSelectDropDownByIndex(By locator, int index) {
		Select select = new Select(this.getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectDropDownByValue(By locator, String value) {
		Select select = new Select(this.getElement(locator));
		select.selectByValue(value);
	}

	public void doSelectDropDownByVisibleText(By locator, String text) {
		Select select = new Select(this.getElement(locator));
		select.selectByVisibleText(text);
	}

	public List<WebElement> getDrpDownOptions(By locator) {
		Select select = new Select(this.getElement(locator));
		return select.getOptions();
	}

	public int getDrpDownOptionsCount(By locator) {
		return getDrpDownOptions(locator).size();
	}

	public List<String> getDrpDownOptionsTextList(By locator) {
		List<String> optionsTextList = new ArrayList<>();
		List<WebElement> allOptions = this.getDrpDownOptions(locator);
		for (WebElement option : allOptions) {
			optionsTextList.add(option.getText());
		}
		return optionsTextList;
	}

	public void selectDropDownValue(By locator, String expvalue) {
		List<WebElement> allOptions = this.getDrpDownOptions(locator);
		for (WebElement option : allOptions) {
			if (option.getText().trim().equalsIgnoreCase(expvalue.trim())) {
				option.click();
				break;
			}
		}
	}

	public void doSearch(By locator, String suggName) {
		List<WebElement> allSuggestions = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		for (WebElement eachSuggestion : allSuggestions) {
			String suggText = eachSuggestion.getText().trim();
			if (!suggText.isBlank() && suggText.equalsIgnoreCase(suggName)) {
				eachSuggestion.click();
				break;
			}
		}
	}

	// *********************** WAIT UTILS ************************//

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * 
	 * @param locator
	 * @param timeout
	 * @return WebElement
	 */
	public WebElement waitForElementPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and is visible. Visibility means that element has some length and width
	 * 
	 * @param locator
	 * @param timeout
	 * @return WebElement
	 */
	public WebElement waitForElementVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that there is at least one element present on a
	 * web page.
	 * 
	 * @param locator
	 * @param timeout
	 * @return List<WebElement>
	 */
	public List<WebElement> waitForElementsPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locatorare visible. Visibility means that the elements are not only
	 * displayed but also have a heightand width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeout
	 * @return List<WebElement>
	 */
	public List<WebElement> waitForElementsVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	// *********************** Alert UTILS ************************//

	public Alert waitForAlertPresence(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertText(int timeout) {
		return waitForAlertPresence(timeout).getText();
	}

	public void acceptAlert(int timeout) {
		waitForAlertPresence(timeout).accept();
	}

	public void dismissAlert(int timeout) {
		waitForAlertPresence(timeout).dismiss();
	}

	public void alertSendKeys(int timeout, String value) {
		waitForAlertPresence(timeout).sendKeys(value);
	}

	// *************************** Browser Utils ***************************//

	public String waitForTitleContainsAndFetch(int timeout, String fractionValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.titleContains(fractionValue));
		return driver.getTitle();
	}

	public String waitForTitleIsAndFetch(int timeout, String value) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.titleIs(value));
		return driver.getTitle();
	}

	public String waitForURLContainsAndFetch(int timeout, String fractionValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.urlContains(fractionValue));
		return driver.getCurrentUrl();
	}

	public String waitForURLIsAndFetch(int timeout, String value) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.urlToBe(value));
		return driver.getCurrentUrl();
	}

	public boolean waitForURLContains(int timeout, String fractionValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.urlContains(fractionValue));
	}

	// **************************** Frame Utils ********************************//

	public void waitForFrameAndSwitchToItByFrameElement(int timeout, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	}

	public void waitForFrameAndSwitchToItByFrameLocator(int timeout, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public void waitForFrameAndSwitchToItByIndex(int timeout, int index) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}

	public void waitForFrameAndSwitchToItByIROrName(int timeout, String IDOrName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IDOrName));
	}

	public void clickWhenReady(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public WebElement waitForElementToBeClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void doClickWithActionsAndWait(By locator, int timeout) {
		WebElement ele = waitForElementToBeClickable(locator, timeout);
		Actions act = new Actions(driver);
		act.click(ele).build().perform();
	}

}
