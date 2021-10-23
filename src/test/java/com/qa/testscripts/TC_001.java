package com.qa.testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TC_001 extends TestBase{
	
	@Test
	public void validateTitle() throws IOException {
		
		String title = driver.getTitle();
		boolean contains = title.contains("OrangeHRM");
		if(contains) {
			Reporter.log("correct page loaded");
			Assert.assertTrue(true);
		}else {
			Reporter.log("Incorrect page loaded");
			captureScreenshot(driver,"TC_001");
			Assert.assertTrue(false);
		}
		
		
	}
	

}
