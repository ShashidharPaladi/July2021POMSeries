package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//1. By locators - PO (PageObjects) - OR (ObjRef)
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	private By logoutLink = By.linkText("Logout");
	
	//2. Page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	//3. Page actions / methods / features
	
	@Step("getting login page title test.....")
	public String getLoginPageTitle() {
		return driver.getTitle();
	}
	
	@Step("getting login page url test....")
	public String getLoginPageUrl() {
		return driver.getCurrentUrl();
	}
	
	@Step("checking forgot pwd link is exist or not.....")
	public boolean isForgotPwdLinkExist() {
		System.out.println("checking --> isForgotPwdLinkExist");
		return elementUtil.doIsDiplayed(forgotPwdLink);
	}
	
	@Step("checking  register link is exist or not....")
	public boolean isRegisterLinkExist() {
//		elementUtil.doClick(logoutLink);
		return elementUtil.doIsDiplayed(registerLink);
	}
	
	@Step("login with username : {0} and password : {1}")
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("creds: " + un + ":" + pwd);
		elementUtil.doSendKeys(emailId, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		return new AccountsPage(driver);
	}
	
	@Step("navigating to register page....")
	public RegistrationPage navigateToRegisterPage() {
		elementUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
	

}
