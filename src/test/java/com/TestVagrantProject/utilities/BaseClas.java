package com.TestVagrantProject.utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClas {
	ReadConfig readconfig=new ReadConfig();
	protected String url=readconfig.getBaseUrl();
	String browser=readconfig.getBrowser();
	public static WebDriver driver;
	public static Logger Log=LogManager.getLogger("TestVagrantProject");

	//Cross Browser Testing
	@BeforeClass
	public void setup() {
		switch(browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;	
		}
		//maximize
		driver.manage().window().maximize();
		//wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	//@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	//ScreenShot Method
	public void CaptureScreenshot(WebDriver driver,String testName) throws IOException {
		
			//step1: convert webdriver object to TakesScreenshot interface
		File src =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + "//Screenshot//" + testName + ".png");
		FileUtils.copyFile(src, dest);
		
	}
}
