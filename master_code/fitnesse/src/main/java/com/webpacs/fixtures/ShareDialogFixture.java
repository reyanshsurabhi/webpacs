package com.webpacs.fixtures;

import com.webpacs.pages.ShareDilaogPage;
import com.webpacs.pages.WebpacsHomePage;

public class ShareDialogFixture {
	
	//inputdata
	public String emailToValue;

	//locators
	public String shareDialogLocator;
	public String EmailToLocator;
	public String linkToViewOnlineRadioButton;
	public String jpegAttchmentsRadiobutton;
	public String populateEmailId;
	public String emailSendLocator;
	public String interactiveLocator;
	public String dicomTagCheckBoxLocator;
	public String annotationsCheckBoxLocator;


	//set methods
	public void setShareDialogLocator(String shareDialogLocator){
		this.shareDialogLocator = shareDialogLocator;
	}
	
	public void setEmailToLocator(String EmailToLocator){
		this.EmailToLocator = EmailToLocator;
	}
	
	public void setEmailToValue(String emailToValue){
		this.emailToValue = emailToValue;
	} 
	
	public void setLinkToViewOnlineRadioButton(String linkToViewOnlineRadioButton){
		this.linkToViewOnlineRadioButton = linkToViewOnlineRadioButton;
	}
	
	public void setJpegAttchments(String jpegAttchmentsRadiobutton){
		this.jpegAttchmentsRadiobutton = jpegAttchmentsRadiobutton;
	}
	
	public void setPopulateEmailId(String populateEmailId){
		this.populateEmailId = populateEmailId;
	}
	
	public void setEmailSendLocator(String emailSendLocator){
		this.emailSendLocator = emailSendLocator;
	}
	
	public void setInteractiveLocator(String interactiveLocator){
		this.interactiveLocator = interactiveLocator;
	}
	
	public void setDicomTagCheckBoxLocator(String dicomTagCheckBoxLocator){
		this.dicomTagCheckBoxLocator = dicomTagCheckBoxLocator;
	}
	
	public void setAnnotationsCheckBoxLocator(String annotationsCheckBoxLocator){
		this.annotationsCheckBoxLocator = annotationsCheckBoxLocator;
	}
	
	
	
	//Fixture Methods
	public boolean  checkShareDialogDisplay(){
		return ShareDilaogPage.checkShareDialogDisplay(shareDialogLocator);
	}
	
	public void selectJpegAttchmentsRadiobuttonWithDicomtags(){
		ShareDilaogPage.selectJpegAttchmentsRadiobuttonWithDicomtags(jpegAttchmentsRadiobutton);
	}
	
	public void selectJpegAttchmentsRadiobuttonWithAddAnnotationsOnly(){
		ShareDilaogPage.selectJpegAttchmentsRadiobuttonWithAddAnnotationsOnly(jpegAttchmentsRadiobutton,dicomTagCheckBoxLocator,annotationsCheckBoxLocator);
	}
	
	public void selectJpegAttchmentsRadiobuttonWithAddAnnotationsAndDicom(){
		ShareDilaogPage.selectJpegAttchmentsRadiobuttonWithAddAnnotationsAndDicom(jpegAttchmentsRadiobutton,annotationsCheckBoxLocator);
	}
	
	public void enterEmailAddress(){
		ShareDilaogPage.enterEmailAddress(EmailToLocator, emailToValue,populateEmailId);
	}
	
	public void clickOnSendButton(){
		ShareDilaogPage.clickOnSendButton(emailSendLocator);
	}
	
	public void clickOnInteractive(){
		ShareDilaogPage.clickOnInteractive(interactiveLocator);
	}
	
	
}
