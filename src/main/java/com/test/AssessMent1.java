package com.test;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AssessMent1 {
	
	
	@Test
	public void assessKnowledge() throws IOException, InterruptedException {
		WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		ChromeDriver driver = new ChromeDriver(options);
		options.addArguments("--disable-notifications");
		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\utils\\config.properties");
		Properties prop = new Properties();
		prop.load(file);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));		
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.id("password")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.id("Login")).click();
		driver.findElement(By.xpath("//div[contains(@class,'slds-icon-waffle')]")).click();
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//p[text()='Service Console']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//a[contains(@class,'slds-context-bar')])[1]")).click();
		driver.findElement(By.xpath("//button[@title='Show Navigation Menu']")).click();
		List<WebElement> findElements = driver.findElements(By.xpath("(//ul[contains(@class,'slds-listbox_vertical')])[1]/li"));
		System.out.println("Total Length  of dropdown list: "+findElements.size());
		Thread.sleep(5000);
		for(int i = 1; i<findElements.size();i++)
		{
			String val = driver.findElement(By.xpath("((//ul[contains(@class,'slds-listbox_vertical')])[1]/li//span[2])["+i+"]/span")).getText();
			if(val.equalsIgnoreCase("Home"))
			{
				driver.findElement(By.xpath("((//ul[contains(@class,'slds-listbox_vertical')])[1]/li//span[2])["+i+"]/span")).click();
				break;
			}
		}
		
		//driver.findElement(By.xpath("(//span[text()='Home'])")).click();
		Thread.sleep(3000);
		String openValue = driver.findElement(By.xpath("//li[@class='metric']/following::span[2]")).getText();
		Thread.sleep(3000);
		String goalValue = driver.findElement(By.xpath("(//li[@class='metric']/following::span[4])[1]")).getText();
		//String openValue = "$10,000";
		
		String openValRemoveChar = openValue.replaceAll("\\D", "");
		System.out.println(openValRemoveChar);
		String goalValRemoveChar = goalValue.replaceAll("\\D", "");
		System.out.println(goalValRemoveChar);
		int AfteropenValConver = Integer.parseInt(openValRemoveChar);
		int AftergoalValConver = Integer.parseInt(goalValRemoveChar);
		int total = AfteropenValConver +AftergoalValConver;
		System.out.println("Open + Goal : "+total);
		if(total<10000)
		{
			driver.findElement(By.xpath("//span[text()='Edit Goal']")).click();
			driver.findElement(By.xpath("//input[contains(@class,'uiInputSmartNumber')]")).sendKeys("10000");
			driver.findElement(By.xpath("(//button[contains(@class,'neutral uiButton')])[3]")).click();
		}		
		driver.findElement(By.xpath("//button[@title='Show Navigation Menu']")).click();
		List<WebElement> elements = driver.findElements(By.xpath("(//ul[contains(@class,'slds-listbox_vertical')])[1]/li"));
		System.out.println("Total Length  of dropdown list: "+findElements.size());
		for(int i = 1; i<elements.size();i++)
		{
			String val = driver.findElement(By.xpath("((//ul[contains(@class,'slds-listbox_vertical')])[1]/li//span[2])["+i+"]/span")).getText();
			if(val.equalsIgnoreCase("Dashboards"))
			{
				driver.findElement(By.xpath("((//ul[contains(@class,'slds-listbox_vertical')])[1]/li//span[2])["+i+"]/span")).click();
				break;
			}
		}
		driver.findElement(By.xpath("//div[text()='New Dashboard']")).click();
		WebElement frameswi1 = driver.findElement(By.xpath("//iframe[@title='dashboard']"));
		driver.switchTo().frame(frameswi1);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='dashboardNameInput']")).sendKeys("Ragu_Assessment_Workout");
		driver.findElement(By.xpath("//button[text()='Create']")).click();
		Thread.sleep(3000);		
		WebElement frameswi2 = driver.findElement(By.xpath("//iframe[@title='dashboard']"));
		driver.switchTo().frame(frameswi2);		
		driver.findElement(By.xpath("//button[text()='Done']")).click();
		driver.findElement(By.xpath("//button[text()='Subscribe']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Daily']")).click();		
		WebElement selEle = driver.findElement(By.xpath("//select[@class=' select']"));		
		Select selOption = new Select(selEle);
		selOption.selectByVisibleText("10:00 AM");
		driver.findElement(By.xpath("//span[text()='Save']")).click();		
		Thread.sleep(4000);		
		String subSuccess = driver.findElement(By.xpath("//div[contains(@class,'toastContent')]//span")).getText();
		if(subSuccess.equals("You started a dashboard subscription."))
		{
			
		}
		else
		{
			System.out.println("Mismatch");
		}
		
			}

}
