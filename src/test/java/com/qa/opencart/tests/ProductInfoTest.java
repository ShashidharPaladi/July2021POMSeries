package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoTest extends BaseTest {
	
	@BeforeClass
	public void productInfoPageSetup() {
		accPage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		
	}
	
//	@DataProvider
//	public Object[][] getHeaderData() {
//		return new Object[][] {
//			{"macbook", "MacBook Pro"},
//			{"iMac", "iMac"},
//			{"Apple", "Apple Cinema 30\""}
//		};
//	}
	
	@DataProvider
	public Object[][] getHeaderData() {
		Object data[][] = ExcelUtil.getTestdata(Constants.PRODUCTINFODATA_SHEET_NAME);
		return data;
	}
	
	@Test(dataProvider = "getHeaderData")
	public void productHeaderTest(String productName, String mainProductName) {
		resultPage = accPage.doSearch(productName);
		productInfoPage = resultPage.selectProduct(mainProductName);
		String actHeader = productInfoPage.getProductHeaderText();
		Assert.assertEquals(actHeader, mainProductName);
	}
	
//	@DataProvider
//	public Object[][] getImageData() {
//		return new Object[][] {
//			{"macbook", "MacBook Pro", 4},
//			{"iMac", "iMac", 3},
//			{"Apple", "Apple Cinema 30\"", 6}
//		};
//	}
	
	@DataProvider
	public Object[][] getImageData() {
		Object data[][] = ExcelUtil.getTestdata(Constants.PRODUCTINFOIMAGEDATA_SHEET_NAME);
		return data;
	}
	@Test(dataProvider = "getImageData")
	public void productImageCountTest(String productName, String maninProductName, int imageCount) {
		resultPage = accPage.doSearch(productName);
		productInfoPage = resultPage.selectProduct(maninProductName);
		Assert.assertEquals(productInfoPage.getProductImageCount(), imageCount);
	}
	
	@DataProvider
	public Object[][] getQuantityCountData() {
		return new Object[][] {
			{"macbook","MacBook Pro", 1}
		};
	}
	
	@Test(dataProvider = "getQuantityCountData")
	public void getQuantityCountTest(String productName, String mainProductName, int quantityCount) {
		resultPage = accPage.doSearch(productName);
		productInfoPage = resultPage.selectProduct(mainProductName);
		int count = productInfoPage.getProductQuantitycount();
		System.out.println(count);
		Assert.assertEquals(count, quantityCount);
		
	}
	
	
	@DataProvider
	public Object[][] getEditQtyData() {
		return new Object[][] {
			{"macbook", "MacBook Pro", "2"}
		};
	}
	
	@Test(dataProvider = "getEditQtyData")
	public void doEditQtyTest(String productName, String mainProductName, String updateQty) {
		resultPage = accPage.doSearch(productName);
		productInfoPage = resultPage.selectProduct(mainProductName);
		productInfoPage.doEditQuantity(updateQty);
		
	
	}
	
	@Test(dataProvider = "getHeaderData")
	public void addToCartBtnExistTest(String productName, String mainProductName ) {
		resultPage = accPage.doSearch(productName);
		productInfoPage = resultPage.selectProduct(mainProductName);
		Assert.assertTrue(productInfoPage.isAddToCartBtnExist());
	}
	
//	@DataProvider
//	public Object[][] getAddToCartData() {
//		return new Object[][] {
//			{"macbook", "MacBook Pro"},
//		};
//	}
	
	@Test(dataProvider = "getAddToCartData")
	public void doAddTocartTest(String productName, String mainProductName) {
		resultPage = accPage.doSearch(productName);
		productInfoPage = resultPage.selectProduct(mainProductName);
		productInfoPage.doAddToCart();
		
	}
	
	@DataProvider
	public Object[][] getAddToCartData() {
		return new Object[][] {
			{"macbook", "MacBook Pro"},
		};
	}
	
	@Test(dataProvider = "getAddToCartData")
	public void productMetaDataTest(String productName, String mainProductName) {
		resultPage = accPage.doSearch(productName);
		productInfoPage = resultPage.selectProduct(mainProductName);
		Map<String, String> actProdMap = productInfoPage.getProductMetaData();
		actProdMap.forEach((k,v) -> System.out.println(k + ":" + v));
		softAssert.assertEquals(actProdMap.get("productName"), "MacBook Pro");
		softAssert.assertEquals(actProdMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProdMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProdMap.get("price"), "$2,000.00");
		softAssert.assertAll();
	}
	

}
