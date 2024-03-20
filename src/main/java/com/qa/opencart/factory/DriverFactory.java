package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
	
	private WebDriver driver;
	private Properties prop;
	private OptionsManager optionsManager;
	public static boolean highlight;
	
	/**
	 * This method is for initializing the driver based on the given browser name
	 * @param browserName
	 * @return WebDriver
	 */
	public WebDriver initDriver() {
		
		String browserName = prop.getProperty("browserName").trim().toLowerCase();
		System.out.println("Browser Name is: "+ browserName);
		highlight = Boolean.parseBoolean(prop.getProperty("highlight").trim());
		optionsManager = new OptionsManager(prop);
		
		switch(browserName) {
		case "chrome": 
			driver = new ChromeDriver(optionsManager.getChromOptions());
			break;
		case "safari": 
			driver = new SafariDriver();
			break;
		case "firefox": 
			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			break;
		default:
			System.out.println("Please pass the right browser " + browserName);
			break;
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		
		return driver;
	}
	
	/**
	 * This method is for reading data from the properties file
	 * @return
	 */
	public Properties initProperties()  {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}

}
