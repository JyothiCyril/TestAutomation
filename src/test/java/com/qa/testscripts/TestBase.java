package com.qa.testscripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.qa.pages.LoginPages;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	WebDriver driver;	
	Actions act;
	FileInputStream fileLoc;
	Properties prop;
	LoginPages LoginOR;
	
	@Parameters({"Browser","Url"})
	@BeforeClass
	public void setUp(String Browser,String Url) throws IOException {
		// top of every class
//		1. Open the browser
//		2. Launch the URL
//		3. Initialize the page object model
//		4. Maximize the screen
//		5. Manage the timeout	
		
		fileLoc = new FileInputStream("D:\\Devlabs\\Batch 4\\SDETTraining\\TestAutomation\\src\\test\\java\\com\\qa\\testdata\\Credentials.properties");
		prop = new Properties();
		prop.load(fileLoc);
		
		
		if(Browser.equalsIgnoreCase("Chrome")) {
			//System.setProperty("webdriver.chrome.driver", "D:\\Selenium Software\\Drivers\\chromedriver.exe");
			WebDriverManager.chromedriver().setup(); 
			driver = new ChromeDriver();	
		}else if(Browser.equalsIgnoreCase("firefox")) {
			//System.setProperty("webdriver.gecko.driver", "");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();	
		}else if(Browser.equalsIgnoreCase("ie")) {
		//	System.setProperty("webdriver.ie.driver", "");
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();	
		}else if(Browser.equalsIgnoreCase("edge")) {
			//System.setProperty("webdriver.edge.driver", "");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();	
		}
		LoginOR = new LoginPages(driver);		
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);		
		
		act = new Actions(driver);
		driver.get(Url);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10000, TimeUnit.SECONDS);
		
		LoginOR.getUnamTxtField().sendKeys(prop.getProperty("Uname"));
		LoginOR.getPwdTxtField().sendKeys(prop.getProperty("Pwd"));
		LoginOR.getLoginBtn().click();
		
	}
	
	@AfterClass
	public void tearDown() {
		// after completion of every class
		// close browser
		driver.close();
	}
	
	
	
	public void captureScreenshot(WebDriver driver, String tName) throws IOException {		
		TakesScreenshot ts = (TakesScreenshot)driver;		
		File Source = ts.getScreenshotAs(OutputType.FILE);
		File Target = new File(System.getProperty("user.dir")+"/Screenshots/"+tName+".png");
		FileUtils.copyFile(Source, Target);		
		System.out.println("Screenshot capture successfully");
		
	}

}
