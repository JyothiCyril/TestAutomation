package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPages {
	
	WebDriver driver;
	
	@FindBy(id="txtUsername")
	WebElement UnamTxtField;
	
	public WebElement getUnamTxtField() {
		return UnamTxtField ; 
	}
	
	@FindBy(id="txtPassword")
	WebElement PwdTxtField;
	
	public WebElement getPwdTxtField() {
		return PwdTxtField; 
	}
	
	@FindBy(id="btnLogin")
	WebElement LoginBtn;
	
	public WebElement getLoginBtn() {
		return LoginBtn; 
	}

	public LoginPages(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);


	}

}
