package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;



@Epic("Epic 200: Open cart app...Design Accounts page")
@Story("US 201: Accounts page features with some basic modules and functionalities")
public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accPage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	
	@Test
	public void accPageTitleTest() {
		String title = accPage.getAccPageTitle();
		System.out.println("Acc page title is: " + title);
		Assert.assertEquals(title, Constants.ACC_PAGE_TITLE);
	}
	
	@Test
	public void accPageLogoutLinkTest() {
		Assert.assertTrue(accPage.islogoutLinkExist());
	}
	
	@Test
	public void accPageSearchTest() {
		Assert.assertTrue(accPage.issearchFieldExist());
	}
	
	@Test
	public void accPageSecHeaderTest() {
		List<String> actSecList = accPage.getAccountSecList();
		System.out.println(actSecList);
		Assert.assertEquals(actSecList, Constants.EXP_ACCOUNTS_SECTIONS_LIST);
	}
	
	
	@DataProvider
	public Object[][] productData() {
		return new Object[][] {
			{"macbook"},
			{"iMac"},
			{"Apple"}
		};
	}
	
	@Test(dataProvider = "productData")
	public void searchTest(String productName) {
		resultPage = accPage.doSearch(productName);
		Assert.assertTrue(resultPage.getProductsListCount() > 0);
	}
	
//	@DataProvider
//	public Object[][] productSelectData() {
//		return new Object[][] {
//			{"macbook", "MacBook Pro"},
//			{"iMac", "iMac"},
//			{"Apple", "Apple Cinema 30\""}
//		};
//	}
	@DataProvider
	public Object[][] productSelectData() {
		Object data[][] = ExcelUtil.getTestdata(Constants.PRODUCTINFODATA_SHEET_NAME);
		return data;
	}
	
	@Test(dataProvider = "productSelectData")
	public void selectProductTest(String productName, String mainProductname) {
		resultPage = accPage.doSearch(productName);
		resultPage.selectProduct(mainProductname);
	}
	
	
	
	
	
	
}
