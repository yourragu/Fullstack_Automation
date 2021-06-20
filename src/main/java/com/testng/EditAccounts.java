package com.testng;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditAccounts {

	ChromeDriver driver ;
	JavascriptExecutor js;
	Robot robot;
	
	@Test
	public void editAcct() throws InterruptedException, AWTException, IOException{
		
		js = (JavascriptExecutor) driver;
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
		
		WebElement acctName = driver.findElement(By.xpath("//input[@name='Account-search-input']"));
		wait.until(ExpectedConditions.elementToBeClickable(acctName)).sendKeys("Ragu");
		Thread.sleep(3000);
		acctName.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		
		/*robot = new Robot();
		for (int i = 0; i < 1; i++) {
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}*/
		

		Thread.sleep(3000);	
		driver.findElement(By.xpath("(//label[@class='slds-checkbox']/span[1])[2]")).click();
		
	List<WebElement> totSize = driver.findElements(By.xpath("//table/tbody/tr[1]/td"));
	System.out.println("Total Size :"+ totSize.size());
	
	//for(int i=1;i<=totSize.size();i++)
	//{
		
		driver.findElement(By.xpath("//table/tbody/tr[1]/td[5]")).click();
		driver.findElement(By.xpath("//table/tbody/tr[1]/td[5]")).sendKeys(Keys.TAB);
	//}
		
		/*for(int i=1;i<=7;i++)
		{
			driver.findElement(By.xpath("//table//a[@title]//*[@data-key='down']")).sendKeys(Keys.TAB);
		}
		
		driver.findElement(By.xpath("//table//a[@title]//*[@data-key='down']")).sendKeys(Keys.ENTER);*/
		
		WebElement drpDown = driver.findElement(By.xpath("(//table//a[@title]//*[@data-key='down'])[1]"));
		js.executeScript("arguments[0].click();", drpDown);
		//drpDown.click();	
		
		Thread.sleep(10000);			
		driver.findElement(By.xpath("(//div[text()='Edit'])[1]")).click();
		
		/*robot = new Robot();
		for (int i = 0; i < 1; i++) {
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ADD);
			robot.keyRelease(KeyEvent.VK_ADD);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}*/
		
		
		
	}

	}

