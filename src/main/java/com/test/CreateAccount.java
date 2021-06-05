package com.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
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

public class CreateAccount {

	ChromeDriver driver ;
	Robot robot;
	
	@Test
	public void createAcct() throws InterruptedException, AWTException, IOException{
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-extensions");
		options.addArguments("disable-infobars");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("start-maximized");
		driver = new ChromeDriver(options);

		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\utils\\config.properties");
		Properties prop = new Properties();
		prop.load(file);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		driver.get("https://login.salesforce.com/");
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.id("password")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.id("Login")).click();
		
		WebElement appLauncher = driver.findElement(By.xpath("//div[@role='navigation']//button[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(appLauncher)).click();
		
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		Thread.sleep(5000); 
		WebElement accounts = driver.findElement(By.xpath("//p[text()='Accounts']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", accounts);
		wait.until(ExpectedConditions.elementToBeClickable(accounts)).click();
		
		driver.findElement(By.xpath("//div[@title='New']")).click();
		driver.findElement(By.xpath("//input[@name='Name']")).sendKeys("Ragu_Accounts_TC");
		
		/*Thread.sleep(15000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(100,250)");
		
		driver.findElement(By.xpath("//label[text()='Ownership']/following::input")).click();
		
		List<WebElement> ownership = driver.findElements(By.xpath("//label[text()='Ownership']/following::span"));
		for(int i=0;i<ownership.size();i = i+3)
		{
			String ownerVal = driver.findElement(By.xpath("//label[text()='Ownership']/following::span["+i+"]")).getText();
			if(ownerVal.equalsIgnoreCase("Public"))
			{
				driver.findElement(By.xpath("//label[text()='Ownership']/following::span["+i+"]")).click();
				break;
			}
		}*/
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		WebElement sucessMsg = driver.findElement(By.xpath("//span[text()='Account']//div"));
		wait.until(ExpectedConditions.visibilityOf(sucessMsg));
		System.out.println("Message is "+sucessMsg.getText());		

		if (sucessMsg.getText().contains("Ragu_Accounts_TC")) {
			System.out.println("New account  is created");

		} else {
			System.out.println("New account creation failed");
		}
		
		
	}

	}

