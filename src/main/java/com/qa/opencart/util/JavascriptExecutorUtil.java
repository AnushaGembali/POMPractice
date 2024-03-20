package com.qa.opencart.util;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptExecutorUtil {
	private WebDriver driver;
	private JavascriptExecutor js;
	
	public JavascriptExecutorUtil(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor)driver;
	}
	
	public void scrollPageDown() {
//		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public void scrollPageUp() {
//		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, -(document.body.scrollHeight))");
	}
	
	public void scrollToElement(WebElement element) {
//		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)",element);
	}
	
	public void scrollToHeight(int height) {
//		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, '"+height+"')");
	}
	
	public void drawBorder(WebElement element) {
//		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.border='3px solid red'",element);
	}
	
	public void changeBackgroundColor(WebElement element, String color) {
//		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.background = '"+color+"'",element);
		try {
			Thread.sleep(Duration.ofSeconds((long) 0.5));
		}catch(InterruptedException e) {
			
		}
	}
	
	public void flash(WebElement element) {
		String orgColor = element.getCssValue("backgroundColor");
		for(int i =0; i < 10; i++) {
			changeBackgroundColor(element, "lightgreen");
			changeBackgroundColor(element,orgColor );
		}	
	}
	
	public String getPageTitleByJS() {
//		JavascriptExecutor js = (JavascriptExecutor)driver;
		return js.executeScript("return document.title;").toString();		 
	}
	
	public void goBackByJS() {
//		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.history.go(-1);");
	}
	
	public void goForwardByJS() {
//		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.history.go(1);");
	}
	
	public void refreshBrowserByJS() {
//		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.history.go(0);");
	}
	
	public String getPageInnerTextByJs() {
		return js.executeScript("return document.documentElement.innerText").toString();
	}
	
	public void generateAlert(String message) {
		js.executeScript("alert('"+message+"')");
	}
	
	public void generateConfirmation(String message) {
		js.executeScript("confirm('"+message+"')");
	}
	
	public void generatePrompt(String message) {
		js.executeScript("prompt('"+message+"')");
	}
	
	public void sendKeysUsingIDByJS(String id, String value) {
		js.executeScript("document.getElementById('" + id + "').value='" + value + "';");
	}
	
	public void clickElementByJS(WebElement element) {
		js.executeScript("arguments[0].click();",element);
	}
	

}
