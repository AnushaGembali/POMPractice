package com.qa.opencart.constans;

import java.util.Arrays;
import java.util.List;

public class OpenCartConstants {
	
	public static final int DEFAULT_MEDIUM_TIME_OUT = 10;
	public static final int DEFAULT_SHORT_TIME_OUT = 5;
	public static final int DEFAULT_LONG_TIME_OUT = 20;
	
	public static final String LOGIN_PAGE_TITLE_VALUE = "Account Login";
	public static final String ACCOUNTS_PAGE_TITLE_VALUE =  "My Account";
	public static final String LOGIN_PAGE_URL_FRACTION_VALUE =  "route=account/login";
	public static final String ACCOUNTS_PAGE_URL_FRACTION_VALUE =  "route=account/account";
	public static final int ACCOUNTS_PAGE_HEADERS_COUNT = 4;
	public static final List<String> EXPECTED_ACOOUNTS_PAGE_HEADER_LIST
												= Arrays.asList("My Account","My Orders", "My Affiliate Account","Newsletter");
	
	public static final String SEARCH_PAGE_URL_FRACTION_VALUE = "route=product/search&search";
	public static final String PRODUCT_PAGE_URL_FRACTION_VALUE =  "route=product/product";
	 
	
}
