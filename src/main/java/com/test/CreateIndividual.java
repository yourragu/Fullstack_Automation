package com.test;

import java.time.Duration;
import java.util.List;
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

public class CreateIndividual {

	@Test

	public void createIndiv() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		//System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		ChromeDriver driver = new ChromeDriver(options);
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-extensions");
		options.addArguments("disable-infobars");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("start-maximized");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));


		driver.get("https://login.salesforce.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys("mercury.bootcamp@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Bootcamp$123");
		driver.findElement(By.id("Login")).click();
		WebElement appLauncher = driver.findElement(By.xpath("//div[@role='navigation']//button[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(appLauncher)).click();
		
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		Thread.sleep(5000); 
		WebElement individual = driver.findElement(By.xpath("//p[text()='Individuals']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", individual);
		wait.until(ExpectedConditions.elementToBeClickable(individual)).click();
		
		
		WebElement btnNew = driver.findElement(By.xpath("//div[@title='New']"));
		wait.until(ExpectedConditions.elementToBeClickable(btnNew)).click();
		
		driver.findElement(By.xpath("(//a[@class='select'])")).click();
		driver.findElement(By.xpath("//a[@title='Mr.']")).click();
		
		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Ragu");
		
		driver.findElement(By.xpath("//button[@title='Save']//span[1]")).click();
		
		String text = driver.findElement(By.xpath("//li[text()='These required fields must be completed: Last Name']")).getText();
		
		if(text.equalsIgnoreCase("These required fields must be completed: Last Name")) {
			System.out.println("Alert message is displayed");
		}
			else
			{
				System.out.println("Alert message is not displayed");
			}
		
		driver.close();
		
	}

}
