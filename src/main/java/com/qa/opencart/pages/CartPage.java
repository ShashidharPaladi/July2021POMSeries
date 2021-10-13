package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class CartPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By header = By.cssSelector("div#content h1");
	private By useCouponCodeLink = By.cssSelector("div#accordion a");
	private By couponCodeLinkText = By.id("input-coupon");
	private By applyCouponButton = By.id("button-coupon");
	
	
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getHeaderText() {
		return elementUtil.doGetText(header);
	}
	
	public void useCouponCode(String couponCode) {
		elementUtil.doClick(useCouponCodeLink);
		elementUtil.doSendKeys(couponCodeLinkText, couponCode);
		elementUtil.doClick(applyCouponButton);
	}
	
	
	
	
}
