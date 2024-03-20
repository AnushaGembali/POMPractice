package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constans.OpenCartConstants;
import com.qa.opencart.util.ElementUtil;

public class ProductPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String,String> productDataInfoVal;
	
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By productHeader = By.tagName("h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaEles = By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceEles = By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[2]/li");

	public String getProductPageTitle() {
		String title = driver.getTitle();
		return title;
	}
	
	public String getProductPageURL() {
		String url = driver.getCurrentUrl();
		return url;
	}
	
	public String getProductHeaderVal() {
		String productHeaderVal = eleUtil.doGetText(productHeader);
		return productHeaderVal;
	}
	
	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForElementsVisible(productImages, OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		return imagesCount;
	}
	
	public Map<String,String> getProductInfo() {
//		productDataInfoVal = new HashMap<String, String>();   	//elements are not stored in the order they are inserted
		productDataInfoVal = new LinkedHashMap<String, String>(); 	//elements are stored in the same order they are inserted
//		productDataInfoVal = new TreeMap<String, String>();			//elements are stored in alphabetical order
		
		//fetching Product Header:
		productDataInfoVal.put("productname", getProductHeaderVal());
		getProductMetaInfo();
		getProductPriceInfo();	
		
		System.out.println(productDataInfoVal);
		return productDataInfoVal;
	}
	
	// fetching Product meta data:
	private void getProductMetaInfo() {
		List<WebElement> productMetaElesList = eleUtil.doGetAllElements(productMetaEles);
		for(WebElement e : productMetaElesList) {
			String meta = e.getText();
			String metaInfo[] = meta.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			productDataInfoVal.put(key, value);
		}	
	}
	
	//fetching product price data
	private void getProductPriceInfo() {
		List<WebElement> productPriceElesList = eleUtil.doGetAllElements(productPriceEles);
		String productPrice = productPriceElesList.get(0).getText();
		productDataInfoVal.put("productPrice", productPrice);
		
		String exTax = productPriceElesList.get(1).getText();
		String exTaxVal = exTax.split(":")[1].trim();
		productDataInfoVal.put("exTax", exTaxVal);
	}

}
