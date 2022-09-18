package com.myecom.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;

public class BaseClass {
	
	public static WebDriver driver;
	public static Properties prop;
	public static String projectPath = System.getProperty("user.dir");
	
	
	public void loadAppication() {
		
		String browser = (String) prop.get("browser");
		
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath+"//drivers//chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath+"//drivers//geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else {
			System.setProperty("webdriver.edge.driver", projectPath+"//drivers//msedgedriver.exe");
			driver = new EdgeDriver();
		}
		driver.get(prop.getProperty("baseurl"));
	}
	
	@BeforeTest
	public void readProp() throws IOException {
		
		FileInputStream fis = new FileInputStream(projectPath+"//config/config.properties");
		prop = new Properties();
		prop.load(fis);	
	}

}
