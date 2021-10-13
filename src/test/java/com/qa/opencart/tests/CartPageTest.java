package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class CartPageTest extends BaseTest {
	
	@BeforeClass
	public void productInfoPageSetup() {
		accPage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		
	}
	
	@DataProvider
	public Object[][] getHeaderTextData() {
		return new Object[][] {
			{"macbook", "MacBook Pro"},
		};
	}
	
	@Test(dataProvider = "getHeaderTextData")
	public void getHeaderTextTest(String productName, String mainProductName) {
		resultPage = accPage.doSearch(productName);
		productInfoPage = resultPage.selectProduct(mainProductName);
		cartPage = productInfoPage.doAddToCart();
		String text = cartPage.getHeaderText();
		Assert.assertEquals(text, Constants.HEADER_TEXT.trim());
	}
	
	
	
	
	
	

}
