package com.webpacs.fixtures;

import com.webpacs.pages.IdexxVetConnectPlusPage;

public class IdexxVetConnectPlusFixture extends DefaultWebDriverSupplier{

	/*
	 * @variables for input values
	 */
	public String userName;
	public String password;
	public String searchWord;
	public String pageTitle;
	public String homePageUrl;

	/*
	 * @variables for locators
	 */
	public String usernameLocator;
	public String passwordLocator;
	public String signInButtonLocator;
	public String imageLinkLocator;
	public String signoutLocator;

	/*
	 * setter methods for input values and locators
	 */
	public void setLoginName(String userName){
		this.userName=userName;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public void setUserNameLocator(String usernameLocator){
		this.usernameLocator = usernameLocator;
	}

	public void setPasswordLocator(String passwordLocator){
		this.passwordLocator = passwordLocator;
	}

	public void setSigninButtonLocator(String signInButtonLocator){
		this.signInButtonLocator = signInButtonLocator;
	}

	public void setImageLinkLocator(String imageLinkLocator){
		this.imageLinkLocator = imageLinkLocator;
	}
	
	public void setPageTitle(String pageTitle){
		this.pageTitle = pageTitle;
	}
	
	public void setSignoutLocator(String signoutLocator){
		this.signoutLocator = signoutLocator;
	}

	/*
	 * Fixure methods
	 */
	public void enterUsername() throws Exception{
		IdexxVetConnectPlusPage.enterUsername(usernameLocator, userName);
	}

	public void enterPassword(){
		IdexxVetConnectPlusPage.enterPassword(passwordLocator, password);
	}

	public void clickOnSigninButton(){
		IdexxVetConnectPlusPage.clickOnSigninButton(signInButtonLocator);
	}

	public void clickOnImageLink(){
		IdexxVetConnectPlusPage.clickOnImageLink(imageLinkLocator);
	}
	
	public void checkPageTitle(){
		IdexxVetConnectPlusPage.verifyTitlepage(pageTitle);
	}
	
	public void homepageLogout() throws Exception{
		IdexxVetConnectPlusPage.homepageLogout(signoutLocator);	
	}

	public void uploadFileInto(String location) throws Exception{
		IdexxVetConnectPlusPage.uploadFileInto(location);
	}
	
}
