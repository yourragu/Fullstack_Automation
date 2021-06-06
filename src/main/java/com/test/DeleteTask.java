package com.test;

import java.io.FileInputStream;
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

public class DeleteTask {

	@Test

	public void deleteTask() throws InterruptedException, IOException {
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
		driver.findElement(By.id("Login")).click();
		WebElement appLauncher = driver.findElement(By.xpath("//div[@role='navigation']//button[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(appLauncher)).click();
		
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElementByXPath("//p[text()='Sales']").click();
		Thread.sleep(3000); 
		
		WebElement element = driver.findElementByXPath("//span[text()='Tasks']");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(3000);		
		driver.findElement(By.xpath("//a[@title='Select List View']")).click();
		driver.findElement(By.xpath("(//span[text()='Recently Viewed'])[5]")).click();		
		WebElement x = driver.findElement(By.xpath("((//ul[@role='listbox'])[1]//li[1])[1]/a/div[1]"));
		executor.executeScript("arguments[0].click();", x);		
		Thread.sleep(3000);
		
		//List<WebElement> totalTabs = driver.findElements(By.xpath("//ul[contains(@class,'branding-actions')]/li"));
		
		driver.findElement(By.xpath("//ul[contains(@class,'branding-actions')]/li[4]/div")).click();
		driver.findElement(By.xpath("//a[@title='Delete']")).click();
		driver.findElement(By.xpath("//button[@title='Delete']")).click();
		String text = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();
		System.out.println(text);

		
		
	}
	
}
