package com.webpacs.utils;

import static org.openqa.selenium.remote.CapabilityType.PLATFORM;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteBrowserManager {
	
	//ThreadLocal will keep local copy of the driver
		public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>();
		//public static final String username = "suchi1";
		public static final String username = PageUtils.readProperty("sauce.user");
		//public static final String accesskey = "73d74e9a-b405-46a5-bbc0-5fbb8ed0d7d7";
		public static final String accesskey = PageUtils.readProperty("sauce.accesskey");
		public static final String URL ="https://"+username+":"+accesskey+"@ondemand.saucelabs.com:443/wd/hub";
		
		private static final String REMOTE = "remote";
		private String remote;
		private Map<String, String> capabilities;
		
		public void RemoteWebDriverSupplier(String json) {
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(json);
			} catch (JSONException e) {
				throw new RuntimeException("Unable to interpret browser information", e);
			}

			try {
				remote = jsonObject.getString(REMOTE);
				jsonObject.remove(REMOTE);
				capabilities = jsonObjectToMap(jsonObject);
			} catch (JSONException e) {
				throw new RuntimeException("Unable to fetch required fields from json string", e);
			}
		}
		/*
		 * convert Json Object into key and value pairs
		 */
		private Map<String, String> jsonObjectToMap(JSONObject jsonObject) throws JSONException {
			// Assume you have a Map<String, String> in JSONObject
			@SuppressWarnings("unchecked")
			Iterator<String> nameItr = jsonObject.keys();
			Map<String, String> outMap = new HashMap<String, String>();
			while(nameItr.hasNext()) {
				String name = nameItr.next();
			    outMap.put(name, jsonObject.getString(name));
			}

		    String platform = outMap.get(PLATFORM);
		    if (platform != null) {
		    	outMap.put(PLATFORM, platform.toUpperCase());
		    }

			return  outMap;
		}
		
		public URL getRemote() {
			try {
				return new URL(remote);
			} catch (MalformedURLException e) {
				throw new RuntimeException("URL '" + remote + "' is not a valid URL");
			}
		}

		public Capabilities getCapabilities() {
			return new DesiredCapabilities(capabilities);
		}
		
		public WebDriver get() {
			return new Augmenter().augment(new RemoteWebDriver(getRemote(), getCapabilities()));
		}

		
		/*public static void initializeRemoteDriver(String myBrowser, String saucePlatform) throws MalformedURLException{
			RemoteWebDriver driver = null;
			
			if(myBrowser.equalsIgnoreCase("chrome")){
				DesiredCapabilities capability = new DesiredCapabilities().chrome();
				capability.setCapability("platform", saucePlatform);
				//capability.setCapability("platform", "Windows 7");
				capability.setCapability("version", "53.0");
				driver = new RemoteWebDriver(new URL(URL),capability);
								
			}
			
			else if(myBrowser.equalsIgnoreCase("firefox")){
				DesiredCapabilities capability = new DesiredCapabilities().firefox();
				capability.setCapability("platform", saucePlatform);
				//capability.setCapability("platform", "Windows 7");
				capability.setCapability("version", "50.0");
				driver = new RemoteWebDriver(new URL(URL),capability);
				
			}
			
			else if(myBrowser.equalsIgnoreCase("iexplore")){
				DesiredCapabilities capability =new DesiredCapabilities().internetExplorer();
				capability.setCapability("platform", saucePlatform);
				//capability.setCapability("platform", "Windows 7");
				capability.setCapability("version", "11.0");
				driver = new RemoteWebDriver(new URL(URL),capability);
								
			}
			else if(myBrowser.equalsIgnoreCase("safari")){
				DesiredCapabilities capability =new DesiredCapabilities().safari();
				capability.setCapability("platform", saucePlatform);
				//capability.setCapability("platform", "Windows 7");
				capability.setCapability("version", "5.1");
				driver = new RemoteWebDriver(new URL(URL),capability);
			}

			
			setRemoteDriver(driver);
			
			getRemoteDriver();
		}

		public static WebDriver getRemoteDriver() {
			return dr.get();
			
			
		}

		public static void setRemoteDriver(RemoteWebDriver driver) {
			dr.set(driver);
			
		}*/
		
		/*public static void closeRemoteDriver(){
			getRemoteDriver().quit();
			dr.set(null);
		}*/
			

}
