package org.exoplatform.Selenium;


//import org.jbehave.web.selenium.SeleniumContext;
//import java.io.File;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.exoplatform.Selenium.UserManagementHelpers;

public class SetUpEnvironement  extends UserManagementHelpers{
    protected static boolean runned;
	protected static WebDriver driver;
	public static Actions actions;
	protected static String timeout;
	protected static int timeoutSecInt;
    protected static String host;
    protected static String hostPort;
	protected static String portalPath;
	protected static String browser;
    protected static boolean httpsFlag;
	protected static boolean ieFlag;
	protected static boolean chromeFlag;
	
	
	protected static void setUp() {
		
        if (!runned) {
            timeout="30000";
            timeoutSecInt = 30;
            host = "localhost";
            hostPort = "8080";
            portalPath = "/portal/intranet/";
            driver = getFirefoxDriver();
            actions = new Actions(driver);
            runned = true;
        }
	}
	
	private static WebDriver getFirefoxDriver() {
	        FirefoxDriver firefoxDriver;
	        
	        FirefoxProfile profile = new FirefoxProfile();
	        profile.setEnableNativeEvents(false);
	        
	        System.out.println("Starting default firefox.");
	        firefoxDriver = new FirefoxDriver(profile);
	  
	        return firefoxDriver;
	}
}