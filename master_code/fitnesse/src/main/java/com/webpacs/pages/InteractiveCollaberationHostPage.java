package com.webpacs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.webpacs.fixtures.DefaultWebDriverSupplier;

public class InteractiveCollaberationHostPage extends DefaultWebDriverSupplier{
	
	
	public static boolean verifyEndCollabaration(String collabarationButtonLocator){
		boolean value=verifyDisplay(collabarationButtonLocator);
		return value;
	}
	
	
	public static boolean verifyChatWindow(String chatwindowLocator){
		boolean value=verifyDisplay(chatwindowLocator);
		return value;
	}
	
	public static boolean getHostStatus(String locator){
		String status = getElement(locator).getText();
		System.out.println("Status:"+status);
		if(status.equalsIgnoreCase("Online")){
			return true;
		}else{
			return false;
		}
	}
	
	public static void drawAnnotation(String drawAnnotationLocator,String strightlineLocator){
		driver.switchTo().frame(0);
		sleep(5);
		getElement(drawAnnotationLocator).click();
		sleep(10);
		WebElement destination=driver.findElement(By.xpath("//*[@id=\"image-1\"]"));
		Actions act = new Actions(driver);
		getElement(strightlineLocator).click();
		//act.dragAndDrop(driver.findElement(By.xpath(".//*[@id='popup-bottomToolbar']/div/div/div[1]/div[2]/input[1]")), destination).moveToElement(destination, 250, 250).build().perform();
		act.moveToElement(destination).build().perform();
		act.clickAndHold().moveByOffset(250, 0).release().build().perform();
		driver.switchTo().defaultContent();
	}
	
	public static boolean getPartcipantStatus(String locator){
		String status = getElement(locator).getText();
		System.out.println("Status:"+status);
		if(status.equalsIgnoreCase("Online")){
			return true;
		}else{
			return false;
		}
	}

	public static void clickOnShowParticipantInformation(String locator){
		waitForAnElement(driver, locator);
		try{
			getElement(locator).click();
		}catch(NoSuchElementException e){
			System.out.println("Show participant information"+locator+" not found");
		}
		sleep(3);
	}
}
