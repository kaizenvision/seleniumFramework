package com.myecom.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myecom.base.BaseClass;

public class IndexPagePom extends BaseClass {
	
	public IndexPagePom() {
		PageFactory.initElements(driver, this);
	} 
	
	@FindBy(xpath = "//img[@class='logo img-responsive']")
	WebElement logo;
	
	@FindBy(xpath = "//a[@class='login']")
	WebElement signinButton;
	
	@FindBy(id = "search_query_top")
	WebElement searchProductbox;
	
	@FindBy(name = "submit_search")
	WebElement serachButton;
	
	public boolean validateLogo() {
		if(logo.isDisplayed()) {
			System.out.println("logo is available");
			return true;
		}
		else {
			return false;
		}
	}
	
	public String getTitileOfPage() {
		String getTitle = driver.getTitle();
		return getTitle;
	}
	
	public SignInPagePom clickOnSignIn() {
		signinButton.click();
		return new SignInPagePom();
	}
	
	public String setSearchText() {
		searchProductbox.sendKeys("t-shirt");
		String searchText = searchProductbox.getAttribute("value");
		return searchText;
	}
	
	public ProductListingPom clickOnSearch() {
		searchProductbox.sendKeys("t-shirt");
		serachButton.click();
		return new ProductListingPom();
	}

}
