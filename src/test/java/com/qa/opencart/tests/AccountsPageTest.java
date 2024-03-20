package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constans.OpenCartConstants;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void accsPageSetUp() {
		accsPage = loginPage.doLogin(prop.getProperty("userName").trim(), prop.getProperty("password").trim());
	}
	
	@Test
	public void accsPageTitleTest() {
		String actualTitle = accsPage.getAccsPageTitle();
		Assert.assertEquals(actualTitle, OpenCartConstants.ACCOUNTS_PAGE_TITLE_VALUE);
	}
	
	@Test
	public void accsPageURLTest() {
		String url = accsPage.getAccsPageURL();
		Assert.assertTrue(url.contains(OpenCartConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE));
	}
	
	@Test
	public void doesLogoutLinkExist() {
		boolean flag = accsPage.logoutLinkExist();
		Assert.assertTrue(flag);
	}
	
	@Test
	public void accsPageHeadersCountTest() {
		List<String> actualAccsPageHeadersList = accsPage.getAccsPageHeaderList();
		Assert.assertEquals(actualAccsPageHeadersList.size(), OpenCartConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}
	
	
	@Test
	public void accsPageHeadersValueTest() {
		List<String> actualAccsPageHeadersList = accsPage.getAccsPageHeaderList();
		System.out.println("Acconuts page headers actual list: "+ actualAccsPageHeadersList );
		Assert.assertEquals(actualAccsPageHeadersList,OpenCartConstants.EXPECTED_ACOOUNTS_PAGE_HEADER_LIST);
	}
	
	@Test(dataProvider ="getSearchKeyTestData")
	public void doSearchTest(String searchKey) {
		searchPage = accsPage.doSearch(searchKey);
		Assert.assertTrue(searchPage.getSearchPageURL().contains(OpenCartConstants.SEARCH_PAGE_URL_FRACTION_VALUE));
	}
	
	@DataProvider
	public Object[][] getSearchKeyTestData(){
		return new Object[][] {
			{"MacBook"},
			{"MacBook Air"},
			{"MacBook Pro"},
			{"iMac"},
			{"Anusha"}
		};
	}

}
