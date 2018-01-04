package com.webpacs.pages;

import org.apache.tools.ant.filters.TokenFilter.ContainsString;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import java.io.File;
import com.webpacs.fixtures.DefaultWebDriverSupplier;
import com.webpacs.utils.PageUtils;

public class IdexxVetConnectPlusPage extends DefaultWebDriverSupplier{
	public static String title="IDEXX Web PACS";
	
	public static void enterUsername(String usernameLocator,String username) throws Exception{
		try{
			getElement(usernameLocator).sendKeys(username);
			PageUtils.getscreenshot("username");
		}catch(NoSuchElementException e){
			System.out.println("Username locator:"+usernameLocator+" not found");
		}
	}
	
	public static void enterPassword(String passwordLocator,String password){
		try{
			getElement(passwordLocator).sendKeys(password);
		}catch(NoSuchElementException e){
			System.out.println("Username locator:"+passwordLocator+" not found");
		}
	}
	
	public static void clickOnSigninButton(String signinButtonLocator){
		try{
			getElement(signinButtonLocator).click();
		}catch(NoSuchElementException e){
			System.out.println("Username locator:"+signinButtonLocator+" not found");
		}
	}
	
	 public static void clickOnImageLink(String imageLinkLocator){
		 waitForAnElement(driver, imageLinkLocator);
	     getElement(imageLinkLocator).click();
	}
	
	 public static void checkPageTitle(){
		 sleep(5);
		 verifyTitlepage(title);
	 }
	 
	 public static void homepageLogout(String locator) throws Exception{
		 sleep(5);
		 getElement(locator).click();
		 PageUtils.getscreenshot("homepageLogout");
		 sapidLogout();
	 }
	 
	 public static void sapidLogout(){
		 try{
			 waitForAnElement(driver, "xpath:://span[text()='digital10']");
			 getElement("xpath:://span[text()='digital10']").click();
			 waitForAnElement(driver, "xpath:://a[normalize-space(text())='Sign Out']");
			 getElement("xpath:://a[normalize-space(text())='Sign Out']").click();
		 }catch(Throwable t){
			 t.printStackTrace();
		 }
	 }
	 
	 public static void openUrl(String url) throws Exception{
		 sleep(2);
		 driver.get(url);
		 sleep(3);
	 }

	public static void uploadFileInto(String fileLocation) throws Exception{
			Screen screen = new Screen();
			Pattern file = new Pattern(
					new File("./src/main/resources/Fileupload.PNG").getAbsolutePath());
			Pattern open = new Pattern(
					new File("./src/main/resources/open.PNG").getAbsolutePath());
			Thread.sleep(2000);
			File f = new File(fileLocation);			
			screen.type(file, f.getAbsolutePath());
			Thread.sleep(2000);
			screen.click(open);
			Thread.sleep(5000);
	}
	
}
