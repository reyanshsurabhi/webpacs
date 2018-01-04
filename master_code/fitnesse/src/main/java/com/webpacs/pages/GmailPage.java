package com.webpacs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.webpacs.fixtures.DefaultWebDriverSupplier;
import com.webpacs.utils.PageUtils;

public class GmailPage extends DefaultWebDriverSupplier {

	public static void navigateToGmail() {
		try {
			driver.navigate().to("http://gmail.com");
		} catch (Throwable t) {
			System.out.println("Not able to load Gmail Url:");
		}

	}

	public static void enterGmailUsername(String gmailusernameLocator, String username) {
		try {
			getElement(gmailusernameLocator).sendKeys(username);
		} catch (NoSuchElementException e) {
			System.out.println("Gmail Username locator:" + gmailusernameLocator + " not found");
		}
	}

	public static void enterGmailPassword(String gmailPasswordLocator, String password) {
		try {
			getElement(gmailPasswordLocator).sendKeys(password);
		} catch (NoSuchElementException e) {
			System.out.println("Gmail Password locator:" + gmailPasswordLocator + " not found");
		}
	}

	public static void clickOnGmailSigninButton(String gmailSigninButtonLocator) {
		try {
			getElement(gmailSigninButtonLocator).click();
		} catch (NoSuchElementException e) {
			System.out.println("Gmail Signin Button locator:" + gmailSigninButtonLocator + " not found");
		}
	}

	public static void clickOnGmailNextButton(String gmailNextButton) {
		try {
			getElement(gmailNextButton).click();
		} catch (NoSuchElementException e) {
			System.out.println("Gmail Next Button locator:" + gmailNextButton + " not found");
		}
	}

	public static void clickOnEmailFunctionalityLabel(String emailfunctionalityLocator) {
		waitForAnElement(driver, emailfunctionalityLocator);
		try {
			driver.navigate().refresh();
			sleep(3);
			getElement(emailfunctionalityLocator).click();
		} catch (NoSuchElementException e) {
			System.out.println("Email Functionality" + emailfunctionalityLocator + " not found");
		}
	}

	public static void clickOnResponseEmail(String responseEmailLocator) throws Exception {
		waitForAnElement(driver, responseEmailLocator);
		try {
			getElement(responseEmailLocator).click();
		} catch (NoSuchElementException e) {
			System.out.println("Email Functionality" + responseEmailLocator + " not found");
		}
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//a[contains(@href,'https://qa.idexximagebank.com')]/img[position()=1]"))).click();
		sleep(3);
		switchTo(1);
		sleep(10);
		PageUtils.getscreenshot("DiagnsticImage");
		switchToParentWithClose();
	}

	public static void clickOnIcResponseEmail(String responseEmailLocator) throws Exception {
		waitForAnElement(driver, responseEmailLocator);
		try {
			getElement(responseEmailLocator).click();
		} catch (NoSuchElementException e) {
			System.out.println("Email Functionality" + responseEmailLocator + " not found");
		}
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//a[contains(@href,'https://qa.idexximagebank.com')]"))).click();
		sleep(3);
		switchTo(1);
		sleep(10);
		PageUtils.getscreenshot("InteractiveCollabaration");
	}
	
	public static void clickOnResponseEmailOnAnnotation(String responseEmailLocator) throws Exception {
		waitForAnElement(driver, responseEmailLocator);
		try {
			getElement(responseEmailLocator).click();
			sleep(5);
		} catch (NoSuchElementException e) {
			System.out.println("Email Functionality" + responseEmailLocator + " not found");
		}
		sleep(5);
		PageUtils.getscreenshot("EmailWithAnnotation");
		switchToParentWithClose();
	}
	
	public static void clickOnResponseEmailOnDicomonly(String responseEmailLocator) throws Exception {
		waitForAnElement(driver, responseEmailLocator);
		try {
			getElement(responseEmailLocator).click();
			sleep(5);
		} catch (NoSuchElementException e) {
			System.out.println("Email Functionality" + responseEmailLocator + " not found");
		}
		sleep(5);
		PageUtils.getscreenshot("EmailWithDicomOnly");
		switchToParentWithClose();
	}
	
	public static void clickOnResponseEmailOnAd(String responseEmailLocator) throws Exception {
		waitForAnElement(driver, responseEmailLocator);
		try {
			getElement(responseEmailLocator).click();
			sleep(5);
		} catch (NoSuchElementException e) {
			System.out.println("Email Functionality" + responseEmailLocator + " not found");
		}
		sleep(5);
		PageUtils.getscreenshot("EmailWithDicomAndAnnotations");
		switchToParentWithClose();
	}


}
