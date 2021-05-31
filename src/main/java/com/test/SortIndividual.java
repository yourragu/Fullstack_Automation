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

public class SortIndividual {

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
		Thread.sleep(2000);
		String beforeSort = driver.findElement(By.xpath("(//table[tbody])[1]/tbody/tr[1]/th/span/a[1]")).getText();
		System.out.println(beforeSort);
		Thread.sleep(4000);
		WebElement lblName = driver.findElement(By.xpath("//span[text()='Name']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", lblName);		
		//driver.findElement(By.xpath("//span[text()='Name']")).click();
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();", lblName);
		Thread.sleep(5000);		
		String afterSort = driver.findElement(By.xpath("(//table[tbody])[1]/tbody/tr[1]/th/span/a[1]")).getText();
		System.out.println(afterSort);
		
		if(!beforeSort.equals(afterSort))
		{
			System.out.println("Sort successfully completed");
		}
		else
		{
			System.out.println("Unable to sort");
		}
		driver.close();
		
	}

}
