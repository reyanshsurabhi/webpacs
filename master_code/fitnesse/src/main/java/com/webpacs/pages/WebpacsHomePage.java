package com.webpacs.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.Sleeper;

import com.webpacs.fixtures.DefaultWebDriverSupplier;

public class WebpacsHomePage extends DefaultWebDriverSupplier {

	/*
	 * click on Help menu button in the home screen
	 */
	public static void clickOnHelpMenuButton(String helpmenuButton) {
		sleep(3);
		clickOnElement(helpmenuButton);
		sleep(2);
	}

	/*
	 * click on About Idexx WebPACS Link
	 */
	public static void clickOnAboutIdexxWebpacsLink(String aboutidexxLink) {
		waitForAnElement(driver, aboutidexxLink);
		clickOnElement(aboutidexxLink);
	}

	/*
	 * verify version of the webpacs
	 */
	public static void checkVersionNumber(String versionlocator, String expectedVersion) {
		waitForAnElement(driver, versionlocator);
		verifyText(versionlocator, expectedVersion);
		clickOnElement(versionlocator);
	}

	/*
	 * Entering data in the Search input Box
	 */
	public static void enterTextInSearchbox(String locator, String input) {
		waitForAnElement(driver, locator);
		enterText(locator, input);
	}

	/*
	 * verify input string
	 */
	public static void checkSearchInput(String elementLocator, String searchString) {
		waitForAnElement(driver, elementLocator);
		verifyText(elementLocator, searchString);
	}

	/*
	 * click on Search Button
	 */
	public static void clickOnSearchButton(String elementLocator) {
		sleep(2);
		clickOnElement(elementLocator);
		sleep(5);
	}

	/*
	 * click on Share Images Link locator in the Search result it is going to
	 * click on first Share Images Link
	 */

	public static void clickOnShareImages(String shareImagesLinkLocator) {
		sleep(2);
		clickOnElement(shareImagesLinkLocator);
		sleep(5);
	}

	/*
	 * click on Share Any Image From This Study Link locator in the Search
	 * result
	 */
	public static void clickOnShareAnyImageFromThisStudy(String shareAnyImageFromThisStudyLocator) {
		sleep(2);
		clickOnElement(shareAnyImageFromThisStudyLocator);
		sleep(5);
	}
	
	
	/*
	 * click on Share Any Image From Patient History locator in the Search
	 * result
	 */
	public static void clickOnShareAnyImageFromPatientHistory(String shareAnyImageFromPatientHistoryLocator) {
		sleep(2);
		clickOnElement(shareAnyImageFromPatientHistoryLocator);
		sleep(5);
	}
	
	/*
	 * click on Pratice Information Button
	 */
	public static void clickOnPracticeInformationButton(String elementLocator){
		System.out.println("inside click on practice information method");
    	clickOnElement(elementLocator);
    	sleep(5);
	}
	
	/*
	 * verify input default reply to email text
	 */
	public static void verifyDefaultEmailText(String elementLocator,String searchString, String attribute){
		System.out.println("Inside of verify default email with multiple params");
		waitForAnElement(driver, elementLocator);
		verifyText(elementLocator, searchString, attribute);
	}
	/*
	 * verify input default reply to email text
	 */
	public static void verifyDefaultEmailText(String elementLocator,String searchString){
		System.out.println("Inside of verify default email with two params");
		waitForAnElement(driver, elementLocator);
		verifyText(elementLocator, searchString);
	}
	
	/*
	 * Select on first image locator in the search results
	 * click on image
	 * navigate to Image viewer screen 
	 */
	public static void navigateToImageviewerScreen(String imagecator){
		clickOnElement(imagecator);
		sleep(10);
	}
}
