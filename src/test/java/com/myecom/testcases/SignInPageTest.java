package com.myecom.testcases;


import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.myecom.base.BaseClass;
import com.myecom.pom.IndexPagePom;
import com.myecom.pom.SignInPagePom;
import com.myecom.utility.ExcelSheetHandle;

public class SignInPageTest extends BaseClass {
	
	ExcelSheetHandle data = new ExcelSheetHandle();
	IndexPagePom indexPagePom;
	SignInPagePom signInPagePom;
	
	
	@BeforeMethod
	public void setUp() {
		loadAppication();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@DataProvider(name="logindata")
	public Object[][] getTestData() {
		Sheet sh = null;
		try {
			sh = data.getSheet(data.getExcelFile(), "login");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object[][] logindata = data.getExcelSheetData(sh);
		return logindata;
	}
	
	@Test(dataProvider = "logindata")
	public void login(HashMap<String,String> logindata) throws FileNotFoundException, InterruptedException {
		//data.getSheet(fis,"login")
		indexPagePom = new IndexPagePom();
		signInPagePom = indexPagePom.clickOnSignIn();
		signInPagePom.setLoginText((String)logindata.get("username"), (String)logindata.get("password"));
		Thread.sleep(3000);
	}
	
	@Test
	public void signUp() throws FileNotFoundException, InterruptedException {
		//data.getSheet(fis,"login")
		Sheet sh = data.getSheet(data.getExcelFile(), "signup");
		String signupemail = data.getSingleStringVale(sh,1,0);
		indexPagePom = new IndexPagePom();
		signInPagePom = indexPagePom.clickOnSignIn();
		signInPagePom.setCreateAccountEmail(signupemail);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	

}
