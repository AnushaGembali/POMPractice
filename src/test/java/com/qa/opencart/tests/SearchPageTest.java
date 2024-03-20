package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constans.OpenCartConstants;

public class SearchPageTest extends BaseTest{
	
	@BeforeClass
	public void productPageSetup() {
		accsPage = loginPage.doLogin(prop.getProperty("userName").trim(), prop.getProperty("password").trim());
	}
	
	@Test(priority = 3)
	public void getProdPageURL() {
		String actualURL = searchPage.getSearchPageURL();
		Assert.assertTrue(actualURL.contains(OpenCartConstants.SEARCH_PAGE_URL_FRACTION_VALUE));
	}
	
	@Test(priority = 1, dataProvider = "getProductNamesTestData")
	public void searchProductsCountTest(String searchKey, String ProductName) {
		searchPage = accsPage.doSearch(searchKey);
		int count = searchPage.getSearchProductsCount();
		Assert.assertTrue(count>0);
	}
	
	@Test(priority = 2, dataProvider = "getProductNamesTestData")
	public void selectProductTest(String searchKey, String ProductName) {
		searchPage = accsPage.doSearch(searchKey);
		if(searchPage.getSearchProductsCount() > 0) {
			productPage = searchPage.selectProduct(ProductName);
			Assert.assertEquals(productPage.getProductHeaderVal(), ProductName);;
		}
	}
	
	@DataProvider
	public Object[][] getProductNamesTestData(){
		return new Object[][] {
			{"MacBook","MacBook"},
			{"MacBook Air","MacBook Air"},
			{"MacBook Pro","MacBook Pro"},
			{"iMac","iMac"},
			{"Anusha","Anusha"}
		};
	}

}
