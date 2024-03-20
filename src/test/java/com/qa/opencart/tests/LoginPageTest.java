package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LoginPageTest extends BaseTest{
	
	@Test(priority=1)
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle,"Account Login");
	}
	
	@Test(priority=2)
	public void forgotPwdExist() {
		boolean flag = loginPage.isFrgtPswLnkExist();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	public void loginPageURLTest() {
		String actualURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actualURL.contains("route=account/login"));
	}
	
	@Test(priority=4)
	public void doLoginTest() {
		accsPage = loginPage.doLogin(prop.getProperty("userName").trim(), prop.getProperty("password").trim());
		Assert.assertEquals(accsPage.getAccsPageTitle(), "My Account");
		
	}

}
