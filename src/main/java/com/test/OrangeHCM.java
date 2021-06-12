package com.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHCM {

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
		driver.get("https://www.orangehrm.com/orangehrm-30-day-trial/");
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);		
		driver.findElement(By.xpath("//input[@id='Form_submitForm_subdomain']")).sendKeys("vishnuprathap");
		driver.findElement(By.xpath("//input[@id='Form_submitForm_FirstName']")).sendKeys("xerox");
		driver.findElement(By.xpath("//input[@id='Form_submitForm_LastName']")).sendKeys("Machine");
		driver.findElement(By.xpath("//input[@id='Form_submitForm_Email']")).sendKeys("vishnu.pratap@stanventure.com");
		driver.findElement(By.xpath("//input[@id='Form_submitForm_JobTitle']")).sendKeys("Freelancing");
		WebElement drpElement = driver.findElement(By.xpath("//select[@id='Form_submitForm_NoOfEmployees']"));		
		Select sel = new Select(drpElement);
		
		List<WebElement> options1 = sel.getOptions();
		System.out.println("Total available options are : "+options1.size());
		options1.forEach((eachElement)->{  if(eachElement.getText().contentEquals("101 - 150")) { eachElement.click();  }  	});
		
		driver.findElement(By.xpath("//input[@id='Form_submitForm_CompanyName']")).sendKeys("AT&T");
		WebElement industry = driver.findElement(By.xpath("//select[@id='Form_submitForm_Industry']"));
		
		if(driver.findElement(By.xpath("//a[@title='Accept Cookies']")).isDisplayed()) {			
			driver.findElement(By.xpath("//a[@title='Accept Cookies']")).click();
		}
		
		Select sel1 = new Select(industry);
		List<WebElement> options2 = sel1.getOptions();
		System.out.println("Total available options are : "+options2.size());
		options2.forEach((eachElement)->{  if(eachElement.getText().contentEquals("Electronics")) { eachElement.click();  }  	});		
		driver.findElement(By.xpath("//input[@id='Form_submitForm_Contact']")).sendKeys("9876325410");
		
		WebElement country = driver.findElement(By.xpath("//select[@id='Form_submitForm_Country']"));
		
		Select sel2 = new Select(country);
		List<WebElement> options3 = sel2.getOptions();
		System.out.println("Total available options are : "+options3.size());
		options3.forEach((eachElement)->{  if(eachElement.getText().contentEquals("Guam")) { eachElement.click();  }  	});
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(100,10)");
		Thread.sleep(3000);
		
		WebElement dummyData = driver.findElement(By.xpath("//input[@id='Form_submitForm_SetDummyData']"));
		dummyData.click();
		//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", dummyData);
		//dummyData.click();
		
		WebElement captchaFrame = driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']"));
		driver.switchTo().frame(captchaFrame);
		
		WebElement captcha =driver.findElement(By.xpath("//span[@id='recaptcha-anchor']/div[1]"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", captcha);
		captcha.click();	
		
		Thread.sleep(5000);
		
		
	/*	for (WebElement ele : options1) {
			if(ele.getText().contentEquals("101 - 150"))
			{
				ele.click();
				break;
			}
			
		}
		*/
		
		
	}

	}

