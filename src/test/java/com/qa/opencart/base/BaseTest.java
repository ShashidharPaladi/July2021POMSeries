package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.CartPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.ResultsPage;

public class BaseTest {
	
	public WebDriver driver;
	public Properties prop;
	public DriverFactory df;
	public LoginPage loginpage;
	public AccountsPage accPage;
	public ResultsPage resultPage;
	public ProductInfoPage productInfoPage;
	public CartPage cartPage;
	public RegistrationPage registrationpage;
	
	public SoftAssert softAssert;
	
	@BeforeTest
	public void setUp() {
		
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		
		loginpage = new LoginPage(driver);
		softAssert = new SoftAssert();
		
	}
	
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	
	

}
