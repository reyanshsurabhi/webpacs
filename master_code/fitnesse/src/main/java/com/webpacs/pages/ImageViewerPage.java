package com.webpacs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.webpacs.fixtures.DefaultWebDriverSupplier;

public class ImageViewerPage extends DefaultWebDriverSupplier {
	
	/*
	 * Select Annotation tool in the bottom
	 * click on Advanced measurement tool
	 * click on VHS tool
	 */
	public static void drawAnnotationOnVhs(String advancedMesurementTool,String vhsLocator){
		driver.switchTo().frame(0);
		revertImage();
		getElement(advancedMesurementTool).click();
		sleep(10);
		WebElement destination=driver.findElement(By.xpath("//*[@id=\"image-1\"]"));
		Actions act = new Actions(driver);
		getElement(vhsLocator).click();
		act.moveToElement(destination).build().perform();
		act.clickAndHold().moveByOffset(250, 0).release().build().perform();
		driver.switchTo().defaultContent();
	}
	
	public static void clickOnHomeButton(String homeIcon){
		sleep(5);
		clickOnElement(homeIcon);
	}
	
	public static void revertImage(){
		driver.findElement(By.xpath(".//*[@id='bottomButton-revert']")).click();
		sleep(2);
		driver.findElement(By.xpath(".//*[@id='popup-annotate-modal']/div/div/div[3]/a[1]")).click();
	}
	
	/*
	 * clearing annotations if anything is present
	 */
	public static void revertImageForDicomtags(){
		driver.switchTo().frame(0);
		driver.findElement(By.xpath(".//*[@id='bottomButton-revert']")).click();
		sleep(2);
		driver.findElement(By.xpath(".//*[@id='popup-annotate-modal']/div/div/div[3]/a[1]")).click();
		driver.switchTo().defaultContent();
	}
}
