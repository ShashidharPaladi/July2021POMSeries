package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
//	private By successMsg = By.xpath("//div[@id='product-product']/div[@class='alert alert-success alert-dismissible']/a");
	private By goToCart = By.cssSelector("div#cart");
	//// div[@id='cart']/button[@type='button']
	private By viewCart = By.xpath("(//div[@id='cart']//a)[position()=3]");
	
	private By prodMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By prodPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getProductHeaderText() {
		return elementUtil.doGetText(productHeader);
	}

	public int getProductImageCount() {
		return elementUtil.waitForElementsVisible(productImages, Constants.DEFAULT_TIME_OUT).size();
	}
	
	public Map<String, String> getProductMetaData() {
		Map<String, String> prodMap = new HashMap<String, String>();
		String productName = elementUtil.doGetText(productHeader);
		prodMap.put("productName", productName);
		getProdMetaData(prodMap);
		getProdPriceMetaData(prodMap);
		return prodMap;
	}
	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: Out Of Stock
	private void getProdMetaData(Map<String, String> prodMap) {
		List<WebElement> metaList = elementUtil.getElements(prodMetaData);
		for(WebElement e : metaList) {
			String metaText = e.getText();
			String metaKey = metaText.split(":")[0].trim();
			String metaValue = metaText.split(":")[1].trim();
			prodMap.put(metaKey, metaValue);
		}
	}
	
	private void getProdPriceMetaData(Map<String, String> prodMap) {
		List<WebElement> priceList = elementUtil.getElements(prodPriceData);
		String actPrice = priceList.get(0).getText().trim();
		String exTaxPrice = priceList.get(1).getText().trim();
		prodMap.put("price", actPrice);
		prodMap.put("ExTaxPrice", exTaxPrice.split(":")[1].trim());
	}

	public int getProductQuantitycount() {
		return elementUtil.waitForElementsVisible(quantity, Constants.DEFAULT_TIME_OUT).size();
	}

	public void doEditQuantity(String qty) {
		elementUtil.doSendKeys(quantity, qty);
	}

	public boolean isAddToCartBtnExist() {
		return elementUtil.doIsDiplayed(addToCartBtn);
	}

	public CartPage doAddToCart() {

		elementUtil.doClick(goToCart);
		elementUtil.doClick(viewCart);
		return new CartPage(driver);

	}

}
