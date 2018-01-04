package com.webpacs.fixtures;

import com.webpacs.pages.ImageViewerPage;

public class ImageViewerFixture {
	
	//locators
	public String advancedMeasurementTool;
	public String vertabralHeartScoreLocator;
	
	
	//setter methods
	public void setAdvancedMeasurementTool(String advancedMeasurementTool){
		this.advancedMeasurementTool = advancedMeasurementTool;
	}
	
	public void setVertabralHeartScoreLocator(String vertabralHeartScoreLocator){
		this.vertabralHeartScoreLocator = vertabralHeartScoreLocator;
	}

	public void drawAnnotationOWithVhs(){
		ImageViewerPage.drawAnnotationOnVhs(advancedMeasurementTool,vertabralHeartScoreLocator);
	}

}
