package com.webpacs.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class LocalBrowserManager {
	public static WebDriver localDriver;
	
	/*
	 * intitializing local webdriver with passing argument as browser name.
	 * Passing all driver paths inside set property file.
	 */
	
	public static void initializeDriver(String browser){
		try{
		if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "./src/main/resources/testdriver/chromedriver.exe");
			localDriver = new ChromeDriver();
			localDriver.manage().window().maximize();
			//return localDriver;
		}
		else if(browser.equalsIgnoreCase("ie")){
			System.setProperty("webdriver.ie.driver", "./src/main/resources/testdriver/MicrosoftWebDriver.exe");
			localDriver = new InternetExplorerDriver();
			localDriver.manage().window().maximize();
			//return localDriver;
		}
		else if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", "./src/main/resources/testdriver/geckodriver.exe");
			localDriver = new FirefoxDriver();
			localDriver.manage().window().maximize();
			//return localDriver;
		}}
		catch(WebDriverException e){
			System.out.println("illigal browser type");
			localDriver = startChromeDriver(browser);
			
		}		
	}
	
	/*
	 * implimenting chromedriver and returning driver
	 */
	public static WebDriver startChromeDriver(String browser) {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/testdriver/chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
		
	}
	
	public static WebDriver getLocalDriver(){
		return localDriver;
	}
	
	public static void closeLocalDriver(){
		localDriver.close();
	}

}
