package com.webpacs.pages;

import org.openqa.selenium.NoSuchElementException;

import com.webpacs.fixtures.DefaultWebDriverSupplier;
import com.webpacs.utils.PageUtils;

public class ShareDilaogPage extends DefaultWebDriverSupplier {
	
	public static boolean checkShareDialogDisplay(String locator){
		boolean display=verifyDisplay(locator);
		return display;
	}
	
	
	public static void enterEmailAddress(String emailToLocator, String value, String emailValue){
		try{
			getElement(emailToLocator).sendKeys(value);
			sleep(2);
			getElement(emailValue).click();
		}catch(NoSuchElementException e){
			System.out.println("Username locator:"+emailToLocator+" not found");
		}	
	}
	
	public static void clickOnSendButton(String emailSendLocator){
		try{
			getElement(emailSendLocator).click();
		}catch(NoSuchElementException e){
			System.out.println("Send Button locator:"+emailSendLocator+" not found");
		}	
	}
	
	public static void clickOnInteractive(String interactiveLocator){
		try{
			getElement(interactiveLocator).click();
		}catch(NoSuchElementException e){
			System.out.println("Interactive Button locator:"+interactiveLocator+" not found");
		}	
	}
	
	public static void selectJpegAttchmentsRadiobuttonWithDicomtags(String jpegAttachmentRadioButton){
		try{
			getElement(jpegAttachmentRadioButton).click();
		}catch(NoSuchElementException e){
			System.out.println("JpegAttachment Radio Button locator:"+jpegAttachmentRadioButton+" not found");
		}	
	}
	
	public static void selectJpegAttchmentsRadiobuttonWithoutDicomTags(String jpegAttachmentRadioButton,String docomTagsLocator){
		try{
			getElement(jpegAttachmentRadioButton).click();
			sleep(3);
			getElement(docomTagsLocator).click();
		}catch(NoSuchElementException e){
			System.out.println("JpegAttachment Radio Button locator:"+jpegAttachmentRadioButton+" not found");
		}	
	}
	
	public static void selectJpegAttchmentsRadiobuttonWithAddAnnotationsOnly(String jpegAttachmentRadioButton,String docomTagsLocator,String addAnnotationsLocator){
		try{
			getElement(jpegAttachmentRadioButton).click();
			sleep(3);
			getElement(docomTagsLocator).click();
			sleep(3);
			getElement(addAnnotationsLocator).click();
		}catch(NoSuchElementException e){
			System.out.println("JpegAttachment Radio Button locator:"+jpegAttachmentRadioButton+" not found");
		}	
	}
	
	public static void selectJpegAttchmentsRadiobuttonWithAddAnnotationsAndDicom(String jpegAttachmentRadioButton,String addAnnotationsLocator){
		try{
			getElement(jpegAttachmentRadioButton).click();
			sleep(3);
			getElement(addAnnotationsLocator).click();
		}catch(NoSuchElementException e){
			System.out.println("JpegAttachment Radio Button locator:"+jpegAttachmentRadioButton+" not found");
		}	
	}
	
	
	
	

}
