package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constans.OpenCartConstants;
import com.qa.opencart.util.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1.Page Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//2.private by locators
	
	private By emailId  = By.id("input-email");
	private By password = By.id("input-password");
	private By forgotPswdLnk = By.xpath("//a[text()='Forgotten Password']");
	private By loginBtn = By.xpath("//input[@value='Login']");
	
	//3. Page Methods or Actions
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleContainsAndFetch(OpenCartConstants.DEFAULT_SHORT_TIME_OUT, OpenCartConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("Login Page Title is: "+title);
		return title;
	}
	
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContainsAndFetch(OpenCartConstants.DEFAULT_SHORT_TIME_OUT, OpenCartConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		System.out.println("Login Page URL is: "+url);
		return url;
	}
	
	public boolean isFrgtPswLnkExist() {
		return eleUtil.waitForElementVisible(forgotPswdLnk, OpenCartConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}
	
	public AccountsPage doLogin(String userName, String pswd) {
		eleUtil.waitForElementVisible(emailId,OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT ).sendKeys(userName);
		eleUtil.doSendkeys(password, pswd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
}
