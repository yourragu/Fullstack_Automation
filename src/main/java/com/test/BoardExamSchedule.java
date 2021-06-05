package com.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BoardExamSchedule {

	@Test

	public void boardExamSch() throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		//System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		//options.setExperimentalOption("debuggerAddress", "localhost:62433");
		options.addArguments("--disable-notifications");
		//options.addArguments("--headless");
		options.addArguments("--disable-extensions");
		options.addArguments("disable-infobars");
		options.addArguments("--disable-popup-blocking");
		
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		Capabilities capabilities = driver.getCapabilities();
		Map<String, Object> asMap = capabilities.asMap();
		asMap.forEach((key, value)->{
			System.out.println("Key :"+key + "" + "Value :" +value);
			
		});
		
		//String version = driver.getCapabilities().getVersion();
		//System.out.println(browserName +":"+version);
		
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
		
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();
		
		Set<String> windowHandles = driver.getWindowHandles();
		
		for (String window : windowHandles) {
			driver.switchTo().window(window);
			String title = driver.getTitle();
			if(title.equalsIgnoreCase("Certification - Architect Overview"))
					{
						System.out.println("Title of the page:"+title);
					}
			
		}
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		WebElement resource = driver.findElement(By.xpath("//span[text()='Resources']"));
		WebElement certification = driver.findElement(By.xpath("//span[text()='Salesforce Certification ']/parent::a"));
		action.moveToElement(resource).moveToElement(certification).click().build().perform();
		Set<String> windowHandles2 = driver.getWindowHandles();
		
		for (String window2 : windowHandles2) {
			driver.switchTo().window(window2);
			String title = driver.getTitle();
			if(title.equalsIgnoreCase("Certification - Architect Overview"))
					{
						System.out.println("Title of the page:"+title);
					}
			
		}
		driver.findElement(By.xpath("//div[text()='Salesforce Architect']")).click();
		WebElement ta = driver.findElement(By.xpath("//a[text()='Technical Architect']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", ta);
		
		driver.findElement(By.xpath("//a[text()='More Details']")).click();
		
	}

	
}
