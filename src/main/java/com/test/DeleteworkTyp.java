package com.test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteworkTyp {

	@Test

	public void deleteworkty() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		//System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		ChromeDriver driver = new ChromeDriver(options);
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-extensions");
		options.addArguments("disable-infobars");
		options.addArguments("--disable-popup-blocking");
		
		Map<String, Object> prefs = new HashMap<String, Object>();

		//add key and value to map as follow to switch off browser notification
		//Pass the argument 1 to allow and 2 to block
		prefs.put("profile.default_content_setting_values.notifications", 2);

	// set ExperimentalOption - prefs 
		options.setExperimentalOption("prefs", prefs);
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));	
		driver.manage().window().maximize();
		driver.get("https://login.salesforce.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys("mercury.bootcamp@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Bootcamp$123");
		driver.findElement(By.id("Login")).click();
		WebElement appLauncher = driver.findElement(By.xpath("//div[@role='navigation']//button[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(appLauncher)).click();
		
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		Thread.sleep(3000); 
		WebElement wt = driver.findElement(By.xpath("//p[text()='Work Types']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", wt);
		wait.until(ExpectedConditions.elementToBeClickable(wt)).click();
		Thread.sleep(3000);
		
		WebElement drp = driver.findElement(By.xpath("(//table[tbody])[1]/tbody/tr[1]/td[5]//a"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", drp);
		Thread.sleep(3000);	
		driver.findElement(By.xpath("(//table[tbody])[1]/tbody/tr[1]/td[5]//a")).click();
		Thread.sleep(5000);		
		driver.findElement(By.xpath("(//ul[@class='scrollable'])[3]/li[2]/a")).click();
		driver.findElement(By.xpath("//span[text()='Delete']")).click();
		String text = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();
		System.out.println(text);
		driver.close();
		
		
	}

}
