package com.webpacs.fixtures;

import com.webpacs.pages.GmailPage;
import com.webpacs.pages.IdexxVetConnectPlusPage;

public class GmailFixture extends DefaultWebDriverSupplier {
	//data
	public String gmailUsername;
	public String gmailPassword;

	
	//locators
	public String gmailUsernameTextbox;
	public String gmailPasswordTextBox;
	public String gmailNextButton;
	public String gmailSignInButton;
	public String emailFunctionalityLink;
	public String responseMailLocator;
	
	//setters methods
	public void setGmailUsernameLocator(String gmailUsernameTextbox){
		this.gmailUsernameTextbox = gmailUsernameTextbox;
	}
	
	public void setGmailUsername(String gmailUsername){
		this.gmailUsername = gmailUsername;
	}
	
	public void setGmailPasswordLocator(String gmailPasswordTextBox){
		this.gmailPasswordTextBox = gmailPasswordTextBox;
	}
	
	public void setGmailPassword(String gmailPassword){
		this.gmailPassword = gmailPassword;
	}
	
	
	public void setGmailNextButton(String gmailNextButton){
		this.gmailNextButton = gmailNextButton;
	}
	
	public void setGmailSignInButton(String gmailSignInButton){
		this.gmailSignInButton = gmailSignInButton;
	}
	
	public void setEmailFunctionalityLink(String emailFunctionalityLink){
		this.emailFunctionalityLink = emailFunctionalityLink;
	}
	
	public void setResponseMailLocator(String responseMailLocator){
		this.emailFunctionalityLink = responseMailLocator;
	}
	
	/*
	 * Fixure methods
	 */
	public void navigateToGmail(){
		GmailPage.navigateToGmail();
	}
	
	public void enterGmailUsername() throws Exception{
		GmailPage.enterGmailUsername(gmailUsernameTextbox,gmailUsername);
	}
	
	public void enterGmailPassword() throws Exception{
		GmailPage.enterGmailPassword(gmailPasswordTextBox,gmailPassword);
	}
	
	public void clickOnGmailNextButton(){
		GmailPage.clickOnGmailNextButton(gmailNextButton);
	}
	
	public void clickOnGmailSigninButton(){
		GmailPage.clickOnGmailSigninButton(gmailSignInButton);
	}
	
	public void clickOnEmailFunctionalityLabel(){
		GmailPage.clickOnEmailFunctionalityLabel(emailFunctionalityLink);
	}
	
	public void clickOnResponseEmail() throws Exception{
		GmailPage.clickOnResponseEmail(responseMailLocator);
	}
	
	public void clickOnResponseEmailOnAnnotation() throws Exception{
		GmailPage.clickOnResponseEmailOnAnnotation(responseMailLocator);
	}
	
	public void clickOnIcResponseEmail() throws Exception{
		GmailPage.clickOnIcResponseEmail(responseMailLocator);
	}
	
	public void clickOnResponseEmailOnDicomonly() throws Exception{
		GmailPage.clickOnResponseEmailOnDicomonly(responseMailLocator);
	}
	
	public void clickOnResponseEmailOnAd() throws Exception{
		GmailPage.clickOnResponseEmailOnAd(responseMailLocator);
	}
	
	
	

}
