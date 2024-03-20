package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constans.OpenCartConstants;
import com.qa.opencart.util.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By logoutLink = By.linkText("Logout");
	private By accsPageHeaders = By.cssSelector("#content h2");
	private By searchBox = By.name("search");
	private By searchIcon = By.cssSelector("#search button");
	
	public String getAccsPageTitle() {
		String title = eleUtil.waitForTitleContainsAndFetch(OpenCartConstants.DEFAULT_SHORT_TIME_OUT,OpenCartConstants.ACCOUNTS_PAGE_TITLE_VALUE);
		System.out.println("Accounts Page title: " + title);
		return title;
	}
	
	public String getAccsPageURL() {
		String url = eleUtil.waitForURLContainsAndFetch(OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT,OpenCartConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE);
		System.out.println("Accounts Page URL: " + url);
		return url;
	}
	
	public List<String> getAccsPageHeaderList() {
		List<WebElement> accsPageHeadersList = eleUtil.waitForElementsVisible(accsPageHeaders, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT);
		List<String> accsPageHeadersValList = new ArrayList<String>();
		accsPageHeadersValList = accsPageHeadersList.stream()
														.map(e -> e.getText())
															.collect(Collectors.toList());
		return accsPageHeadersValList;
									
	}
	
	public boolean logoutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink, OpenCartConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}
	
	public SearchPage doSearch(String searchKey) {
		eleUtil.doSendkeys(searchBox, searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchPage(driver);
	}
	

}
