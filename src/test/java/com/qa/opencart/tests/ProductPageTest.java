package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constans.OpenCartConstants;

public class ProductPageTest extends BaseTest{
	
	
	@BeforeClass
	public void productPageSetUp() {
		accsPage = loginPage.doLogin(prop.getProperty("userName").trim(), prop.getProperty("password").trim());
	}
	
	@Test(priority=3)
	public void productPageURLTest() {
		String url = productPage.getProductPageURL();
		Assert.assertTrue(url.contains(OpenCartConstants.PRODUCT_PAGE_URL_FRACTION_VALUE));
	}
	
	@Test(priority=2, dataProvider ="getProductImagesCountTestData")
	public void productHeaderValTest(String searchKey, String productName, int imagesCount) {
		searchPage = accsPage.doSearch(searchKey);
		productPage = searchPage.selectProduct(productName);
		String actualProductHeaderVal = productPage.getProductHeaderVal();
		Assert.assertEquals(actualProductHeaderVal, productName);
	}
	
	@Test(priority=1, dataProvider ="getProductImagesCountTestData")
	public void productImagesCountTest(String searchKey, String productName, int imagesCount) {
		searchPage = accsPage.doSearch(searchKey);
		productPage = searchPage.selectProduct(productName);
		int actualProductImagesCount = productPage.getProductImagesCount();
		Assert.assertEquals(actualProductImagesCount, imagesCount);
	}

	@DataProvider
	public Object[][] getProductImagesCountTestData(){
		return new Object[][] {
			{"MacBook","MacBook",5},
			{"MacBook Air","MacBook Air",4},
			{"MacBook Pro","MacBook Pro",4},
			{"iMac","iMac",3}
		};
	}
	
	@Test
	public void productInfoTest() {
		searchPage = accsPage.doSearch("MacBook");
		productPage = searchPage.selectProduct("MacBook Pro");
		Map<String, String> actualProductInfo = productPage.getProductInfo();
		softAssert.assertEquals(actualProductInfo.get("Brand"), "Apple");
		softAssert.assertEquals(actualProductInfo.get("Product Code"), "Product 18");
		softAssert.assertEquals(actualProductInfo.get("productname"), "MacBook Pro");
		softAssert.assertEquals(actualProductInfo.get("productPrice"), "$2,000.00");
		
		softAssert.assertAll();
	}
}
