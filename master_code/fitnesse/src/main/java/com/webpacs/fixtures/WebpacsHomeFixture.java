package com.webpacs.fixtures;

import java.util.List;

//import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.webpacs.pages.WebpacsHomePage;
import com.webpacs.utils.PageUtils;

public class WebpacsHomeFixture{

	//input values
	public String expectedversion;
	public String searchInput;
	public String expctedSearchResulttxt;

	//locators
	public String helpmenuLocator;
	public String about_idexx_webpacsLink;
	public String versionlocator;
	public String searchInputTextBox;
	public String searchResultmessage;
	public String searchButton;
	public String shareImagesLink;
	public String shareAnyImageFromThisStudyLink;
	public String shareAnyImageFromPatientHistoryLink;

	//set methods
	public void setHelpmenuLocator(String helpmenuLocator){
		this.helpmenuLocator = helpmenuLocator;
	}

	public void setAboutidexxwebpacsLink(String about_idexx_webpacsLink){
		this.about_idexx_webpacsLink = about_idexx_webpacsLink;
	}

	public void setVersionLocator(String versionlocator){
		this.versionlocator = versionlocator;	
	}

	public void setExpectedVersion(String expectedversion){
		this.expectedversion = expectedversion;
	}
	
	public void setSearchInputTextBox(String searchInputTextBox){
		this.searchInputTextBox = searchInputTextBox;
	}
	
	public void setSearchInput(String searchInput){
		this.searchInput = searchInput;
	}
	
	public void setSearchResultmessage(String searchResultmessage){
		this.searchResultmessage = searchResultmessage;
	}
	
	public void setExpctedSearchResulttxt(String expctedSearchResulttxt){
		this.expctedSearchResulttxt = expctedSearchResulttxt;
	}
	
	public void setSearchButton(String searchButton){
		this.searchButton = searchButton;
	}
	
	public void setShareImagesLink(String shareImagesLink){
		this.shareImagesLink = shareImagesLink;
	}
	
	public void setShareAnyImageFromThisStudyLink(String shareAnyImageFromThisStudyLink){
		this.shareAnyImageFromThisStudyLink = shareAnyImageFromThisStudyLink;
	}
	
	public void setShareAnyImageFromPatientHistoryLink(String shareAnyImageFromPatientHistoryLink){
		this.shareAnyImageFromPatientHistoryLink = shareAnyImageFromPatientHistoryLink;
	}
	
	//Fixure Methods
	public void clickOnHelpMenuButton(){
		WebpacsHomePage.clickOnHelpMenuButton(helpmenuLocator);
	}

	public void clickOnAboutIdexxWebpacsLink(){
		WebpacsHomePage.clickOnAboutIdexxWebpacsLink(about_idexx_webpacsLink);
	}

	public void checkVersionNumber(){
		WebpacsHomePage.checkVersionNumber(versionlocator,expectedversion);
	}
	
	public void enterTextInSearchbox(){
		WebpacsHomePage.enterTextInSearchbox(searchInputTextBox,searchInput);
	}
	
	public void checkSearchInput(){
		WebpacsHomePage.checkSearchInput(searchResultmessage,expctedSearchResulttxt);
	}
	
	public void clickOnSearchButton(){
		WebpacsHomePage.clickOnSearchButton(searchButton);
	}
	
	public void clickOnShareImages(){
		WebpacsHomePage.clickOnShareImages(shareImagesLink);
	}
	
	public void clickOnShareAnyImageFromThisStudy(){
		WebpacsHomePage.clickOnShareAnyImageFromThisStudy(shareAnyImageFromThisStudyLink);
	}
	
	public void clickOnShareAnyImageFromPatientHistory(){
		WebpacsHomePage.clickOnShareAnyImageFromPatientHistory(shareAnyImageFromPatientHistoryLink);
	}

}
