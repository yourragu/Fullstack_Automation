package com.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifySorting {

	@Test

	public void verifySort() throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		// System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();

		options.addArguments("--disable-notifications");
		options.addArguments("--disable-extensions");
		options.addArguments("disable-infobars");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("start-maximized");
		ChromeDriver driver = new ChromeDriver(options);

		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\utils\\config.properties");
		Properties prop = new Properties();
		prop.load(file);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		driver.get("https://login.salesforce.com/");
		// driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.id("password")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.id("Login")).click();
		
		// Navigate to Sales
		driver.findElementByClassName("slds-icon-waffle").click();
		driver.findElementByXPath("//button[text()='View All']").click();
		driver.findElementByXPath("//p[text()='Sales']").click();

		// Navigate to Accounts tab
		WebElement element = driver.findElementByXPath("//span[text()='Accounts']");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(3000);
		
		// Get all account names from table
		List<String> accountNames = new ArrayList<>();
		String count = driver.findElementByXPath("//span[@class='countSortedByFilteredBy']").getText().replaceAll("\\D",
				"");
		int recordsCount = Integer.parseInt(count);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i = 1; i <= recordsCount; i++) {
			WebElement row = driver.findElementByXPath("(//a[@data-refid='recordId'])[" + i + "]");
			js.executeScript("arguments[0].scrollIntoView();", row);
			accountNames.add(row.getText());
			if (i == recordsCount) {
				count = driver.findElementByXPath("//span[@class='countSortedByFilteredBy']").getText()
						.replaceAll("\\D", "");
				recordsCount = Integer.parseInt(count);
			}
		}
		System.out.println(accountNames);
		
		// Close Browser
		driver.close();
	}
		
	}

