package com.testng;

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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateWorktype {
	RemoteWebDriver driver;
	
	@Parameters({"Browser"})
	@Test
	public void createIndiv(String browser) throws InterruptedException, IOException {
		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			//System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");			
			options.addArguments("--disable-extensions");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-popup-blocking");
			//options.addArguments("--headless");
			//options.setExperimentalOption("debuggerAddress", "localhost:62298");
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

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
		
		WebElement workTypeGrp = driver.findElement(By.xpath("//p[text()='Work Type Groups']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", workTypeGrp);
		wait.until(ExpectedConditions.elementToBeClickable(workTypeGrp)).click();
		
		
		WebElement btnNew = driver.findElement(By.xpath("//div[@title='New']"));
		wait.until(ExpectedConditions.elementToBeClickable(btnNew)).click();
		
		//driver.findElement(By.xpath("//input[@class=' input']")).sendKeys("Automation");		
		driver.findElement(By.xpath("//textarea[@class=' textarea']")).sendKeys("Automation");		
		driver.findElement(By.xpath("//a[@class='select']")).click();
		driver.findElement(By.xpath("//a[text()='Capacity']")).click();		
		driver.findElement(By.xpath("//button[@title='Save']//span[1]")).click();				
		String msg = driver.findElement(By.xpath("//li[text()='These required fields must be completed: Work Type Group Name']")).getText();
		if(msg.equalsIgnoreCase("These required fields must be completed: Work Type Group Name"))
		{
			System.out.println("Worktype group name error message is captured");
		}
		else
		{
			System.out.println("Worktype group name is not captured");
		}
		driver.close();
		
	}

}
