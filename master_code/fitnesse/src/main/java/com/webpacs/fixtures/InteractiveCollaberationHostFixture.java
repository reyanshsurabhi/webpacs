package com.webpacs.fixtures;

import com.webpacs.pages.InteractiveCollaberationHostPage;

public class InteractiveCollaberationHostFixture extends DefaultWebDriverSupplier {
	
	//locators
	public String collabarationButtonLocator;
	public String chatwindowLocator;
	public String showParticipantLocator;
	public String hostStatusLocator;
	public String drawAnnotationLocator;
	public String strightlineLocator;
	
	//setters methods
	public void setCollabarationButtonLocator(String collabarationButtonLocator){
		this.collabarationButtonLocator = collabarationButtonLocator;
	}

	public void setChatwindowLocator(String chatwindowLocator){
		this.chatwindowLocator = chatwindowLocator;
	}

	public void setShowParticipantLocator(String showParticipantLocator){
		this.showParticipantLocator = showParticipantLocator;
	}
	
	public void setHostStatusLocator(String hostStatusLocator){
		this.hostStatusLocator = hostStatusLocator;
	}
	
	public void setDrawAnnotationLocator(String drawAnnotationLocator){
		this.drawAnnotationLocator = drawAnnotationLocator;
	}
	
	public void setStrightlineLocator(String strightlineLocator){
		this.strightlineLocator = strightlineLocator;
	}
	
	
    //Fixture Methods
	
	public boolean verifyEndCollabaration(){
		return InteractiveCollaberationHostPage.verifyEndCollabaration(collabarationButtonLocator);
	}
	
	public  boolean verifyChatWindow(){
		return InteractiveCollaberationHostPage.verifyChatWindow(chatwindowLocator);
	}
	
	public boolean  getHostStatus(){
		 return InteractiveCollaberationHostPage.getHostStatus(hostStatusLocator);
	}
	
	public void clickOnShowParticipantInformation(){
		InteractiveCollaberationHostPage.clickOnShowParticipantInformation(showParticipantLocator);
	}
	
	public void drawAnnotation(){
		InteractiveCollaberationHostPage.drawAnnotation(drawAnnotationLocator, strightlineLocator);
	}

}
