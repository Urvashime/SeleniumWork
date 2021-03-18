package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;



public class TestBase {
	
    public static FileInputStream fil;
    public static Properties prop;
    public static WebDriver driver;
	
    public TestBase() {
	   
	   try {
	       fil = new FileInputStream("/Users/urvashimehta/eclipse-workspace/GitRepoProject/src/main/java/com/qa/util/config.properties");
	       prop = new Properties();
	       prop.load(fil);
	   }catch(FileNotFoundException e)
	   { e.printStackTrace();
	   }catch(IOException e)
	   { e.printStackTrace();}	
	
	}
    public static void initialization()
    {
    	if(prop.getProperty("browser").equals("chrome"))
    	{
    		System.setProperty("webdriver.chrome.driver", "/Users/urvashimehta/bin/chromedriver");
    		driver= new ChromeDriver();
    	}else if(prop.getProperty("browser").equals("FF"))
    	{
    		System.setProperty("webdriver.gecko.driver", "/Users/urvashimehta/bin/geckodriver");
    		driver= new FirefoxDriver();	
    	}else if(prop.getProperty("browser").equals("Grid"))
    	{
    		/*DesiredCapabilities cap = new DesiredCapabilities();
    		ChromeOptions options = new ChromeOptions();
    		cap.setBrowserName(new ChromeOptions());
    		cap.setPlatform(new Platform(prop.getProperty("platform")));     //(prop.getProperty("platform"));
    		options.merge(cap);
    		driver= new RemoteWebDriver(new URL(prop.getProperty("huburl")),cap);	
    		*/
    	}
    	driver.manage().window().maximize();
    	driver.manage().deleteAllCookies();
    	driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    	driver.get(prop.getProperty("url"));
    }
}	
	

