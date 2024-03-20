package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.frameworkexception.FrameworkException;

public class DriverFactory {
	
	private WebDriver driver;
	private Properties prop;
	private OptionsManager optionsManager;
	public static boolean highlight;
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
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
			tlDriver.set(new ChromeDriver(optionsManager.getChromOptions()));
			break;
		case "safari": 
			tlDriver.set(new SafariDriver());
			break;
		case "firefox": 
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		default:
			System.out.println("Please pass the right browser " + browserName);
			break;
		}
		
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		
		return tlDriver.get();
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * This method is for reading data from the properties file
	 * @return
	 */
	public Properties initProperties()  {
		prop = new Properties();
		FileInputStream ip = null;
//		String envName = System.getProperty("env");
		String envName = "qa";
		System.out.println("The environement selected is: " + envName);
		
		try {
			if(envName==null) {
				System.out.println("Since environemnet is not given, running the automation in QA env");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			}else {
				switch(envName.trim().toLowerCase()) {
				case "qa":
					System.out.println("Running the automation in QA env");
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					System.out.println("Running the automation in DEV env");
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "uat":
					System.out.println("Running the automation in UAT env");
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;
				case "prod":
					System.out.println("Running the automation in PROD env");
					ip = new FileInputStream("./src/test/resources/config/prod.config.properties");
					break;
				default:
					System.out.println("The given environment name is incorrect");
					throw new FrameworkException("Incorrect environment is given");
				}
			}
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	

	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + methodName + "_" + System.currentTimeMillis() + ".png";
		
		File destFile = new File(path);
		try {
			FileHandler.copy(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
