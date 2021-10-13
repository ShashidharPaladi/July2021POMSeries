package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class DemoCartPage {
	
	private By cartButton = By.id("cart");
	
	public DemoCartPage() {
		System.out.println("demo cart page...");
	}
	
	public void addToCart() {
		System.out.println("add to cart");
		System.out.println("add feature is done...");
	}
}
