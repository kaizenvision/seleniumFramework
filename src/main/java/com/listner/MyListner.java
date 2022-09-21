package com.listner;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.myecom.base.BaseClass;
import com.myecom.utility.Utility;

public class MyListner extends BaseClass implements ITestListener {
	
	@Override
	public void onTestSuccess(ITestResult result) {
		try {
			Utility.getScreenShot(driver, result.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		try {
			Utility.getScreenShot(driver, result.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
