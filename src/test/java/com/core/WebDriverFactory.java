package com.core;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverFactory {
	
	private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);

	public static WebDriver driver;
	public static WebDriver BrowserSelection(String browser) throws Exception{
		
		switch(browser.toLowerCase())
		{
		    case "chrome":   driver=new ChromeDriver();
		                     logger.info("Chrome browser has been invoked");
		                     break;

		    case "firefox":  driver=new FirefoxDriver();
                             logger.info("Firefox browser has been invoked");
                             break;
                             
		    default:
                             logger.fatal("No such browser is implemented.Browser name sent: " + browser);
                             throw new Exception("No such browser is implemented.Browser name sent: " + browser);
		    	   
		}
		
		driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        logger.info("Driver maximized and implicit time out set to 20 seconds");
		return driver;
		
	}
	
	 public static void navigateToTheUrl(String url){
	        driver.get(url);
	        logger.info("Browser navigated to the url: " + url);
	    }
	

	    public static void quitDriver(){
	        driver.quit();
	        logger.info("Driver closed");
	    }
	 
	 public static void SwitchToNewTab() {
		 WebDriverWait wait = new WebDriverWait(driver,20);
	        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
	        
	        Set<String> handles = driver.getWindowHandles(); 
	        logger.info("List of windows found: "+handles.size());
	        logger.info("Windows handles: " + handles.toString());
	        Iterator<String> it = handles.iterator(); 
	        String original = it.next();
	        String nextTab = it.next();
	        driver.switchTo().window(nextTab); 
	        logger.info("Switched to the new window/tab");
	 }
	 
	 public static void switchToOriginalTab(){
	        Set<String> handles = driver.getWindowHandles(); 
	        logger.info("List of windows found: "+handles.size());
	        logger.info("Windows handles: " + handles.toString());
	        Iterator<String> it = handles.iterator(); 
	        String original = it.next();
	        driver.switchTo().window(original);
	        logger.info("Switched to the original window/tab");

	    }
	 
	 public static String getBrowserName(){
	        String browserDefault = "chrome"; 
	        String browserSentFromCmd = System.getProperty("browser");

	        if (browserSentFromCmd==null){
	            return browserDefault;
	        }else{
	            return browserSentFromCmd;
	        }
	    }
}



