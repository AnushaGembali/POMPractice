package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	
	public OptionsManager( Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromOptions() {
		 co = new ChromeOptions();
		 if(Boolean.parseBoolean(prop.getProperty("headless").trim()))
		 {
			 co.addArguments("--headless");
		 }
		 
		 if(prop.getProperty("incognito").trim().equalsIgnoreCase("true"))
		 {
			 co.addArguments("--incognito");
		 }
		 
		 return co;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		 fo = new FirefoxOptions();
		 if(Boolean.parseBoolean(prop.getProperty("headless").trim()))
		 {
			 fo.addArguments("--headless");
		 }
		 
		 if(prop.getProperty("incognito").trim().equalsIgnoreCase("true"))
		 {
			 fo.addArguments("--incognito");
		 }
		 
		 return fo;
	}

}
