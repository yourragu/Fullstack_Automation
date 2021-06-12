package com.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DownloadPDF {

	ChromeDriver driver;
	Robot robot;

	@Test
	public void nonProfit() throws InterruptedException, AWTException, IOException {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-extensions");
		options.addArguments("disable-infobars");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("start-maximized");
		
		HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
		chromeOptionsMap.put("plugins.always_open_pdf_externally", true);
		chromeOptionsMap.put("download.prompt_for_download", false);
		chromeOptionsMap.put("download.default_directory", "D:\\FullStackAutomation_Exercise\\Exercise1\\src\\main\\resources\\downloadedFile");
		options.setExperimentalOption("prefs", chromeOptionsMap);
		
		driver = new ChromeDriver(options);
		
		long sizeOfDirectory = FileUtils.sizeOfDirectory(new File("D:\\FullStackAutomation_Exercise\\Exercise1\\src\\main\\resources\\downloadedFile"));
		System.out.println(sizeOfDirectory);
		FileUtils.cleanDirectory(new File("D:\\FullStackAutomation_Exercise\\Exercise1\\src\\main\\resources\\downloadedFile"));
		
		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\utils\\config.properties");
		Properties prop = new Properties();
		prop.load(file);
		int j = 1;
		int x = 0;
		boolean flag = false;
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
				String tileText = driver.findElement(By.xpath("(//div[@class='tileTitle'])[" + x + "]//span")).getText();
				System.out.println(tileText);
				if (tileText.equalsIgnoreCase("View Release Notes")) {
					driver.findElement(By.xpath("(//div[@class='tileTitle'])[" + x + "]/following::button/span[1]")).click();
					flag = true;
					break;
				}
				if (j == 3) {
					break;
				}
			}
			if(flag==true) {
				break;
			}
		}
		
		Set<String> windowHandles = driver.getWindowHandles();
		for (String window : windowHandles) {
			driver.switchTo().window(window);

		}
		Thread.sleep(10000);
		
		/*WebElement st = driver.findElement(By.xpath("//*[@id='dropdown-icon']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", st);
		Thread.sleep(2000);		
		driver.findElement(By.xpath("//ul[contains(@class,'slds-listbox')]/li[4]")).click();
		Thread.sleep(2000);
		*/
		driver.findElement(By.xpath("//div[@id='toc-download-pdf-button']/a")).click();

		

	}

}
