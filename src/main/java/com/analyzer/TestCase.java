package com.analyzer;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class TestCase {
	

	//@Test(retryAnalyzer = com.analyzer.RetryFailedTestcase.class)
	@Test
	public void x() {
		//WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.orangehrm.com/orangehrm-30-day-trial/");
		driver.close();
		
	}

	@Test()
	public void y() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println("This is the second test case");
		driver.get("https://www.toolsqa.com/");
		WebElement ele = driver.findElement(By.xpath("(//a[@href='#go'])[1]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",ele);		
		driver.findElement(By.xpath("(//input[@title='Search form'])[1]")).sendKeys("Testing");
		driver.findElement(By.xpath("//a[@class='search-icon']/i")).click();
		driver.close();


	}

	@Test()
	public void z() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("This is the third test case");
		driver.get("https://www.servicenow.com/#");
		
		
		driver.findElement(By.xpath("//a[text()='Solutions']")).click();
		driver.findElement(By.id("nav-toggler")).click();
		driver.close();

	}


}
