package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductPage;
import com.qa.opencart.pages.SearchPage;

public class BaseTest {
	
	private DriverFactory df;
	private WebDriver driver;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountsPage accsPage;
	protected SearchPage searchPage;
	protected ProductPage productPage;
	protected SoftAssert softAssert;
	
	@BeforeTest
	public void setUp() {
		df = new DriverFactory();
		prop = df.initProperties();
		driver = df.initDriver();
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
