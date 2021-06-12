package com.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;
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

public class LeaftapsRegEx {

	ChromeDriver driver ;
	Robot robot;
	
	@Test
	public void createAcct() throws InterruptedException, AWTException, IOException{
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		driver = new ChromeDriver(options);

		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\utils\\config.properties");
		Properties prop = new Properties();
		prop.load(file);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));		
		driver.get("http://leaftaps.com/opentaps/control/main");
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys(prop.getProperty("leaftapsUsername"));
		driver.findElement(By.id("password")).sendKeys(prop.getProperty("leaftapsPassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		driver.findElement(By.xpath("//a[contains(text(),'CRM/SFA')]")).click();
		
		Set<String> windowHandles = driver.getWindowHandles();		
		windowHandles.forEach((handle)->{driver.switchTo().window(handle);	});
		
		driver.findElement(By.xpath("//a[text()='Create Lead']")).click();
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys("Rasmol Enterprise");
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys("Sidharth");
		driver.findElement(By.id("createLeadForm_lastName")).sendKeys("Abhimanyu");
		driver.findElement(By.xpath("//input[@name='submitButton']")).click();
		
		String cName = driver.findElement(By.id("viewLead_companyName_sp")).getText();
		
		String regEx = cName.replaceAll("[\\D", "");	
		
		System.out.println("Extracted lead ID is : "+regEx);
	}

	}

