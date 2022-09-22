package com.myecom.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.listner.MyListner;
import com.myecom.base.BaseClass;

@Listeners(MyListner.class)
public class DemoTest extends BaseClass {
	
	
	@BeforeMethod
	public void setUp() {
		loadAppication();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority = 3, groups = {"smoke"})
	public void test1() {
		System.out.println("in test 1");
	}
	
	@Test(priority = 1, groups = {"sanity", "regression"})
	public void abc() {
		System.out.println("in test 2");
		Assert.fail();
	}
	
	@Test(priority = 1, groups = {"sanity"})
	public void xyz() {
		System.out.println("in test 3");
	}
	

}
