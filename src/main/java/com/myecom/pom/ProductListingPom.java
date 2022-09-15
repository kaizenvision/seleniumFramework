package com.myecom.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myecom.base.BaseClass;

public class ProductListingPom extends BaseClass {
	
	public ProductListingPom() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h1[contains(text(),'Search')]")
	WebElement searchPageHeading;
	
	public String getSearchPageHeading() {
		String pageHeading = driver.getTitle();
		return pageHeading;
	}

}
