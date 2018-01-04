package com.webpacs.fixtures;



import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.tools.ant.util.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import com.webpacs.utils.AppiumBrowserManager;
import com.webpacs.utils.LocalBrowserManager;
import com.webpacs.utils.PageUtils;
import com.webpacs.utils.RemoteBrowserManager;
import org.apache.commons.io.*;

public class DefaultWebDriverSupplier {
	public static String browser;
	public static WebDriver driver ;
	
	RemoteBrowserManager remoteBrowser = new RemoteBrowserManager();
	
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	
	/*
	 * newWebdriver method is which is returning webdriver depends upon
	 * running in Sauce or local environment.
	 */
		
	public WebDriver newWebDriver() throws Exception{
			
		if(browser.contains("Sauce")){
			/*PageUtils.convertJsonString(browser);
			String sauceBrowserNew = PageUtils.readProperty("browserName");
			String saucePlatformNew = PageUtils.readProperty("platform");
			RemoteBrowserManager.initializeRemoteDriver(sauceBrowserNew,saucePlatformNew);
			driver = RemoteBrowserManager.getRemoteDriver();
*/
			remoteBrowser.RemoteWebDriverSupplier(browser);
			driver = remoteBrowser.get();
		}
		else{
		LocalBrowserManager.initializeDriver(browser);
		driver = LocalBrowserManager.getLocalDriver();
		}
		return driver;
		
	}

	public String getProjectPath(){
		String path = System.getProperty("user.dir");
		path = path + "/target/test-classes";
		System.out.println(path+"path");
		return path;
	}
	
	public void closeSession()
	{
		if(driver !=null){
			driver.quit();
		}
	}
	
	
	public static WebElement getElement(String elementLocator) {
		String[] objects = elementLocator.split("::");
		String locator = objects[0];
		String value = objects[1];

		try{
			switch (locator) {
			case "id":
				return driver.findElement(By.id(value));

			case "name":
				return driver.findElement(By.name(value));

			case "xpath":
				return driver.findElement(By.xpath(value));

			case "css":
				return driver.findElement(By.cssSelector(value));

			case "linkText":
				return driver.findElement((By.linkText(value)));

			case "plink":
				return driver.findElement(By.partialLinkText(value));

			case "tagname":
				return driver.findElement((By.tagName(value)));

			case "class":
				return driver.findElement(By.className(value));

			default:
				System.out.println("No Element is matching");
			}
		}catch(NoSuchElementException e){
			System.out.println("No matching element found:"+e.getMessage());
		}
		return null;
	}
	
	public static void waitForAnElement(WebDriver driver,String elementLocator){
		try{
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(getElement(elementLocator)));
		}catch(NoSuchElementException e){
			System.out.println("No Such Element found:"+elementLocator);
		}
	}
	
	public static void verifyTitlepage(String expectedTitle){
		String pageTitle=driver.getTitle();
		System.out.println("Page Title:"+pageTitle);
		try{
			Assert.assertEquals(pageTitle, expectedTitle);
		}catch(Throwable t){
			System.out.println("You are expecting page title is: "+ expectedTitle+",but you are getting: "+pageTitle);
		}
	}
	
	public static void clickOnElement(String elementLocator){
		waitForAnElement(driver, elementLocator);
		try{
			getElement(elementLocator).click();
			Thread.sleep(2000);
		}catch(Throwable t){
			System.out.println("No Such Element found:"+elementLocator);
		}
	}
	
	public static void verifyText(String elementLocator,String message){
		waitForAnElement(driver, elementLocator);
		String actualText=getElement(elementLocator).getText();
		System.out.println("Expected Text:"+message);
		System.out.println("Actual Text:"+actualText);
		try{
			Assert.assertTrue(actualText.equalsIgnoreCase(message));
		}catch(Throwable t){
			System.out.println("Expcted message:"+message+"  "+"Actual Text:"+actualText);
		}
	}
	
	public static void enterText(String elementLocator,String message){
		waitForAnElement(driver, elementLocator);
		try{
			getElement(elementLocator).sendKeys(message);
		}catch(Throwable t){
			System.out.println("Unable to write in the textbox:"+elementLocator);
		}
	}
	
	public static void sleep(int durationInSeconds) {
	    try {
	        Sleeper.SYSTEM_SLEEPER.sleep(new Duration(durationInSeconds, TimeUnit.SECONDS));
	    } catch (InterruptedException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public static boolean verifyDisplay(String elementLocator){
		waitForAnElement(driver, elementLocator);
		try{
			getElement(elementLocator).isDisplayed();
			return true;
		}catch(NoSuchElementException t){
			System.out.println("Unable to find element:"+elementLocator);
			return false;
		}
	}
	
	
	public static void switchTo(int index) {
		try {
			List<String> win = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(win.get(index));
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("Invalid Window Index : " + index);
		}
		
	}
	
	public static void switchToParentWithClose() {
		List<String> win = new ArrayList<String>(driver.getWindowHandles());
		
		for(int i = 1; i < win.size(); i++){
			driver.switchTo().window(win.get(i));
			driver.close();
		}
		driver.switchTo().window(win.get(0));
		
	}
	public static void verifyText(String elementLocator,String message, String property){
		waitForAnElement(driver, elementLocator);
		String actualText=getElement(elementLocator).getAttribute(property);
		System.out.println("Expected Text:"+message);
		System.out.println("Actual Text:"+actualText);
		try{
			Assert.assertTrue(actualText.equalsIgnoreCase(message));
		}catch(Throwable t){
			System.out.println("Expcted message:"+message+"  "+"Actual Text:"+actualText);
		}
	}
	
	public static void verifyElementPresent(String locator){
		Assert.assertTrue(getElement(locator).isDisplayed());
	}
	
	public static void scrollToElement(String locator) throws Exception{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", getElement(locator));
	}
}

