package com.myecom.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myecom.base.BaseClass;

public class SignInPagePom extends BaseClass {
	
	public SignInPagePom() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "email")
	WebElement loginEmail;
	
	@FindBy(id = "passwd")
	WebElement loginPassword;
	
	@FindBy(id ="SubmitLogin")
	WebElement signInButton;
	
	@FindBy(id = "email_create")
	WebElement createAccounEmail;
	
	@FindBy(id = "SubmitCreate")
	WebElement createAccountEmail;
	
	@FindBy(xpath = "//h1[text()='Authentication']")
	WebElement signInPageHeading;
	
	
	public String getPageHeading() {
		String pageHeading = signInPageHeading.getText();
		return pageHeading;
	}
	
	public void setLoginText(String username, String password) {
		loginEmail.sendKeys(username);
		loginPassword.sendKeys(password);
	}
	
	public void setCreateAccountEmail(String email) {
		createAccounEmail.sendKeys(email);
	}
}
