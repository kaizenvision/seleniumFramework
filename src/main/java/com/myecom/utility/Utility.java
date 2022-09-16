package com.myecom.utility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.myecom.base.BaseClass;

public class Utility extends BaseClass {
	
	public void applyExplicitWait(WebDriver driver, WebElement ele) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(ele));
		
	}
	
	public void applyFluentWait(WebDriver driver, WebElement ele) {
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)							
				.withTimeout(Duration.ofSeconds(30)) 			
				.pollingEvery(Duration.ofSeconds(5)) 			
				.ignoring(NoSuchElementException.class);
		WebElement clickseleniumlink = wait.until(new Function<WebDriver, WebElement>(){
		
			public WebElement apply(WebDriver driver ) {
				return ele;
			}
		});
	}
	
	public static String getScreenShot(WebDriver driver, String name) throws IOException {
		
		File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMDDhhmmss"));
		String des = projectPath + "//screenshot//"+name+currentTime+" .png";
		File destinationFile = new File(des);
		FileUtils.copyFile(scr, destinationFile);
		return des;
	}

}
