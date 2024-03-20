package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constans.OpenCartConstants;
import com.qa.opencart.util.ElementUtil;

public class SearchPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By searchResults = By.cssSelector("div#content div.product-layout");

	public String getSearchPageURL() {
		String title = driver.getCurrentUrl();
		return title;
	}

	public int getSearchProductsCount() {
		int searchCount = eleUtil.waitForElementsVisible(searchResults, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT)
				.size();
		return searchCount;
	}

	public ProductPage selectProduct(String productName) {
		By productLocator = By.linkText(productName);
		eleUtil.waitForElementVisible(productLocator, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT).click();
		return new ProductPage(driver);
	}

}
