package com.myecom.testcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.myecom.base.BaseClass;
import com.myecom.pom.IndexPagePom;
import com.myecom.pom.ProductListingPom;
import com.myecom.pom.SignInPagePom;
import com.myecom.utility.Utility;

public class IndexPageTest extends BaseClass {
	
	IndexPagePom indexPagePom;
	SignInPagePom signInPagePom;
	ProductListingPom productListingPom;
	SoftAssert softAssert = new SoftAssert();
	
	ExtentReports extentReports;
	ExtentTest logger;
	
	@BeforeTest
	public void generateExtentReport() {
		ExtentSparkReporter generateReport = new ExtentSparkReporter(projectPath+"//extentreport/ExtentReport.html");
		extentReports = new ExtentReports();
		extentReports.attachReporter(generateReport);
	}
	
	@AfterTest
	public void flushReport() {
		extentReports.flush();
	}
	
	
	@BeforeMethod
	public void setUp() {
		loadAppication();
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if(result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, result.getName()+" Test case FAIL ");
			logger.log(Status.FAIL, result.getThrowable());
			logger.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(Utility.getScreenShot(driver, result.getName())).build());
			//logger.log(Status.FAIL, logger.addScreenCaptureFromPath(Utility.getScreenShot(driver, result.getName())));
		}
		else if(result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.SKIP, result.getName()+" Test case SKIPPED ");
		}
		else if(result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS, result.getName()+" Test case PASS ");
		}
		//extentReports.removeTest(logger);
		driver.quit();
	}
	
	@Test
	public void validateLogo() {
		logger = extentReports.createTest("validateLogo");
		indexPagePom = new IndexPagePom();
		boolean res = indexPagePom.validateLogo();
		logger.log(Status.INFO, "get validate logo res");
		Assert.assertTrue(res);
	}
	
	@Test
	public void ValdateTiltle() {
		logger = extentReports.createTest("ValdateTiltle");
		indexPagePom = new IndexPagePom();
		String title = indexPagePom.getTitileOfPage();
		logger.log(Status.INFO, title);
		Assert.assertEquals(title, "My Store");
	}
	
	@Test
	public void ClickOnSignIn() {
		logger = extentReports.createTest("ClickOnSignIn");
		indexPagePom = new IndexPagePom();
		signInPagePom = indexPagePom.clickOnSignIn();
		String pageHeading = signInPagePom.getPageHeading();
		Assert.assertEquals(pageHeading, "AUTHENTICATION");
	}
	
	@Test
	public void validateSearchBoxText() {
		logger = extentReports.createTest("validateSearchBoxText");
		indexPagePom = new IndexPagePom();
		String searchText = indexPagePom.setSearchText();
		Assert.assertEquals(searchText, "t-shi");
	}
	@Test
	public void validateSearchBoxClick() {
		logger = extentReports.createTest("validateSearchBoxClick");
		indexPagePom = new IndexPagePom();
		productListingPom = indexPagePom.clickOnSearch();
		String searchPageHeading = productListingPom.getSearchPageHeading();
		Assert.assertEquals(searchPageHeading, "Search - My Store");
	}
}
