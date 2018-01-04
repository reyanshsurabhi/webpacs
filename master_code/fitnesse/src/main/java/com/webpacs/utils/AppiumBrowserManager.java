package com.webpacs.utils;

import static org.openqa.selenium.remote.CapabilityType.PLATFORM;
import static org.openqa.selenium.remote.CapabilityType.BROWSER_NAME;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AppiumBrowserManager {
	
	public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>();
	public static final String username = "suchi1";
	public static final String accesskey = "73d74e9a-b405-46a5-bbc0-5fbb8ed0d7d7";
	public static final String URL ="https://"+username+":"+accesskey+"@ondemand.saucelabs.com:443/wd/hub";
	private static final String REMOTE = "remote";
	private String remote;
	private Map<String, String> capabilities;
	
	 private static AppiumDriver appiumdriver;
	
	public void AppiumBrowserManager(){}
	
	public WebDriver setRemoteMobileBrowser(String json) throws MalformedURLException {
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
		
		//return appiumdriver = (AppiumDriver) new RemoteWebDriver(getRemote(), getCapabilities());
		return new AndroidDriver(getRemote(), getCapabilities());
	
	//return new Augmenter().augment(new RemoteWebDriver(getRemote(), getCapabilities()));
	
	}
	
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
		
		/*String browserName = outMap.get(BROWSER_NAME);
		if(browserName != null){
			outMap.put(BROWSER_NAME, browserName.toUpperCase());
		}*/
		
		//String devicename = outMap.get(key)

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
	
	/*public static void initializeAppiumDriver(String deviceName, String browserName, String platformName) throws MalformedURLException{
		RemoteWebDriver appium_driver = null;
		
		if(platformName.equalsIgnoreCase("Android")){
			DesiredCapabilities caps = DesiredCapabilities.android();
			caps.setCapability("appiumVersion", "1.5.3");
			caps.setCapability("deviceName",deviceName);
			caps.setCapability("deviceOrientation", "portrait");
			caps.setCapability("browserName", browserName);
			caps.setCapability("platformVersion", "6.0");
			caps.setCapability("platformName",platformName);
			appium_driver = new RemoteWebDriver(new URL(URL),caps);
		}
		
		else if(platformName.equalsIgnoreCase("iOS")){
			DesiredCapabilities caps = DesiredCapabilities.iphone();
			caps.setCapability("appiumVersion", "1.5.3");
			caps.setCapability("deviceName",deviceName);
			caps.setCapability("deviceOrientation", "portrait");
			caps.setCapability("platformVersion","9.3");
			caps.setCapability("platformName", platformName);
			caps.setCapability("browserName", browserName);
			appium_driver = new RemoteWebDriver(new URL(URL),caps);
		}
		
		setAppiumDriver(appium_driver);
		
		getAppiumDriver();
		
	}
	
	public static WebDriver getAppiumDriver() {
		return dr.get();
			
	}

	public static void setAppiumDriver(RemoteWebDriver driver) {
		dr.set(driver);
		
	}*/
	


}
