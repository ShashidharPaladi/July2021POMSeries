package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void regPageSetup() {
		registrationpage = loginpage.navigateToRegisterPage();
	}
	
	public String getRandomNumber() {
		Random random = new Random();
		String email = "testautomation98"+random.nextInt(1000)+"@gmail.com";
		return email;
	}
	
	@DataProvider
	public Object[][] getRegTestData() {
		Object data[][] = ExcelUtil.getTestdata(Constants.REGISTER_SHEET_NAME);
		return data;
	}
	
	@Test(dataProvider = "getRegTestData")
	public void registrationTest(String firstname, String lastname, String telephone, String password, String subscribe ) {
		Assert.assertTrue(registrationpage.registration(firstname, lastname, getRandomNumber(), telephone, password, subscribe));
	}
	
	
	
	
	
	
}
