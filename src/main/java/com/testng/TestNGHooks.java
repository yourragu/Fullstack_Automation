package com.testng;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNGHooks {
	public RemoteWebDriver driver;
	public WebDriverWait wait;
	public Properties prop;
	public ChromeOptions options;
	public FileInputStream file;
	
	@Parameters({"Browser"})
	@BeforeMethod
	public void beforeMethod() throws IOException {
		file = new FileInputStream(".\\src\\main\\resources\\utils\\config.properties");
		prop = new Properties();
		prop.load(file);
		if (prop.getProperty("browser").equalsIgnoreCase((prop.getProperty("browser")))) {
			WebDriverManager.chromedriver().setup();
			// System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			options = new ChromeOptions();
			driver = new ChromeDriver(options);
			options.addArguments("--disable-notifications");
			//options.addArguments("start-maximized");
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		} else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		} else {
			System.out.println("Specified browser not found");
		}

	}

	@AfterMethod
	public void afterMethod() {
		driver.close();

	}

}
