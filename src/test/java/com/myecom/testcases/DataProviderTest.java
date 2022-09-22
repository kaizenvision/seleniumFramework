package com.myecom.testcases;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.myecom.base.BaseClass;
import com.myecom.pom.IndexPagePom;
import com.myecom.pom.SignInPagePom;
import com.myecom.utility.ExcelSheetHandle;

public class DataProviderTest extends BaseClass {
	
	SignInPagePom signInPagePom;
	ExcelSheetHandle data = new ExcelSheetHandle();
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", projectPath+"//drivers//chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		driver.manage().window().maximize();
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@DataProvider
	public Object[][] getData() {
		Sheet sh = null;
		try {
			sh = data.getSheet(data.getExcelFile(), "login");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data.getTestData(sh);
	}
	
	@Test(dataProvider = "getData")
	public void login(String username, String password) throws InterruptedException {
		IndexPagePom indexPagePom = new IndexPagePom();
		signInPagePom = indexPagePom.clickOnSignIn();
		signInPagePom.setLoginText(username, password);
		Thread.sleep(3000);
	}

}
