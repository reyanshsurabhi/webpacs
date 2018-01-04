package com.webpacs.fixtures;

import java.io.File;
import java.io.PrintWriter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SauceResultsFixture {
	 private final WebDriver webDriver;

	  public SauceResultsFixture(WebDriver webDriver) {
	    this.webDriver = webDriver;
	  } 

	  public boolean printSauceId(String path) {
	  
		  if(path.equals("dummy"))
			  return true;
	String message = String.format("SauceOnDemandSessionID=%1$s job-name=%2$s",(((RemoteWebDriver) webDriver).getSessionId()).toString(), "some job name");
	    //System.out.println(message);
	    
	File theDir = new File("C:\\Xebium\\Xebium-master\\Xebium-master\\FitNesseRoot\\files\\sauceResults\\"+path+"");

	// if the directory does not exist, create it
	if (!theDir.exists()) {
	        try{
	        theDir.mkdir();
	    } 
	    catch(SecurityException se){
	      System.out.println("creating sauce directory failed");
	    }        
	}
		
		
		
		
	try{
	    PrintWriter writer = new PrintWriter("C:\\Xebium\\Xebium-master\\Xebium-master\\FitNesseRoot\\files\\sauceResults\\"+path+"\\saucesession.txt","UTF-8");
	    writer.println(message);
	        writer.close();
			return true;
	} catch (Exception e) {
	   System.out.println("writing sauce result to file failed");
	return false;
	   }


	  }
}


