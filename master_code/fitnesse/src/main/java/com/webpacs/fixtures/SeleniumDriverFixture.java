package com.webpacs.fixtures;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.webpacs.utils.LocalBrowserManager;

public class SeleniumDriverFixture {
	DefaultWebDriverSupplier defaultWebDriverSupplier = new DefaultWebDriverSupplier();
	public static String url;
	
	public void setBrowser(String browser) {
		
		defaultWebDriverSupplier.setBrowser(browser);
	}
		
	public void setUrl(String url){
		this.url = url;
	}
	
	public void startDriverOnUrl(WebDriver driver, String url) throws Exception{
		driver.get("https://qa.vetconnectplus.com/login");
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}
	
	public void startMobiledriverOnUrl(WebDriver driver, String url) throws Exception{
		driver.get("https://qa.vetconnectplus.com/login");
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}
	
	public void stopBrowser(){
		defaultWebDriverSupplier.closeSession();
	}
	
	
}
