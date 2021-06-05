package com.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CustomerServiceOption {

	ChromeDriver driver;
	Robot robot;
	static boolean flag = false;
	@Test
	public void nonProfit() throws InterruptedException, AWTException, IOException {

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
		int j = 1;
		int x = 0;

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		driver.get("https://login.salesforce.com/");
		// driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.id("password")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.id("Login")).click();
		Thread.sleep(4000);
		List<WebElement> totalSlider = driver.findElements(By.xpath("//div[@class='carousel']/div"));

		for (int i = 1; i <= totalSlider.size(); i++) {

			if (i > 1) {
				driver.findElement(By.xpath("//button[contains(@class,'rightArrowButton')]")).click();

			}

			Thread.sleep(3000);
			List<WebElement> totalLinks = driver.findElements(By.xpath("(//div[@class='tileTitle'])"));

			for (j = 1; j < totalLinks.size(); j++) {
				System.out.println("Value of x is :" + x);
				x = x + 1;
				String tileText = driver.findElement(By.xpath("(//div[@class='tileTitle'])[" + x + "]//span"))
						.getText();
				System.out.println(tileText);
				if (tileText.equalsIgnoreCase("Mobile Publisher")) {
					flag= true;
					driver.findElement(By.xpath("(//div[@class='tileTitle'])[" + x + "]/following::button/span[1]"))
							.click();
					break;
				}
				if (j == 3) {
					break;
				}
			}
			if(flag)
			{
				break;
			}
		}

		Set<String> windowHandles = driver.getWindowHandles();
		for (String window : windowHandles) {
			driver.switchTo().window(window);

		}
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//li[@id='products_menu_item']"))).moveToElement(driver.findElement(By.xpath("//*[contains(@class,'cloud-icon-service')]"))).click().build().perform();
		
		List<WebElement> ele = driver.findElements(By.xpath("//ul[contains(@class,'searchEnabled')]/li"));
		for(int i=1;i<ele.size();i++)
		{
			System.out.println(driver.findElement(By.xpath("//ul[contains(@class,'searchEnabled')]/li["+i+"]/button/span[1]")).getText());
		}

		

	}

}
