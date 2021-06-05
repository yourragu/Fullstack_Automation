package com.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateParentTer {

	@Test

	public void CreateParentTer() throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		//System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		//options.setExperimentalOption("debuggerAddress", "localhost:62298");
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
		WebElement appLauncher = driver.findElement(By.xpath("//div[@role='navigation']//button[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(appLauncher)).click();
		
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		Thread.sleep(3000); 
		WebElement st = driver.findElement(By.xpath("//p[text()='Service Territories']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", st);
		wait.until(ExpectedConditions.elementToBeClickable(st)).click();
		Thread.sleep(3000);
		
		WebElement stSearch = driver.findElement(By.xpath("//input[contains(@name,'ServiceTerritory-search')]"));
		wait.until(ExpectedConditions.elementToBeClickable(stSearch)).sendKeys("Brinda");
		Thread.sleep(3000);
		stSearch.sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//table[tbody]/tbody/tr[1]/td[5]")).click();
		driver.findElement(By.xpath("//table[tbody]/tbody/tr[1]/td[5]//button")).click();		
		driver.findElement(By.xpath("//span[text()='New Service Territory']")).click();
		String x = "Gowtham Adani";
		 driver.findElement(By.xpath("(//label[contains(@class,'uiLabel')])[3]/following-sibling::input[1]")).sendKeys(x);
		
			
		driver.findElement(By.xpath("//input[@placeholder='Search Operating Hours...']")).click();
		
		List<WebElement> operatingHrs = driver.findElements(By.xpath("(//ul[contains(@class,'lookup__list')])[2]/li"));
			for(int i=1;i<operatingHrs.size();i++)
			{
				if(i==3) {
					driver.findElement(By.xpath("(//ul[contains(@class,'lookup__list')])[2]/li["+i+"]")).click();
					break;
				}
			}
	
	WebElement btnSave = driver.findElement(By.xpath("(//span[text()='Save'])[2]"));
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].click();", btnSave);
	Thread.sleep(3000);
	
	String getParentTerName = driver.findElement(By.xpath("//table[tbody]/tbody/tr[1]/td[5]//a")).getAttribute("innerHTML");
	System.out.println("2nd Parent Ter is "+getParentTerName);
	
	if(getParentTerName.contains(x))
	{
		System.out.println("Parent Territory Name created and displayed");
	}else
	{
		System.out.println("Parent Territory Name is not created and displayed");
	}
	driver.close();
	}

	
}
