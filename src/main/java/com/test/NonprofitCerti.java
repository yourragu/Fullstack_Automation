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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NonprofitCerti {

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
				if (tileText.equalsIgnoreCase("See System Status")) {
					driver.findElement(By.xpath("(//div[@class='tileTitle'])[" + x + "]/following::button/span[1]"))
							.click();
					break;
				}
				if (j == 3) {
					break;
				}
			}
		}
		
		Set<String> windowHandles = driver.getWindowHandles();
		for (String window : windowHandles) {
			driver.switchTo().window(window);

		}

		driver.findElement(By.xpath("//*[contains(@class,'dropdown-svg-icon')]")).click();
		List<WebElement> listElement = driver.findElements(By.xpath("(//span[contains(@class,'slds-truncate')])"));
		for (int i = 1; i < listElement.size(); i++) {
			WebElement tru = driver.findElement(By.xpath("(//span[contains(@class,'slds-truncate')])[" + i + "]/p[1]"));
			WebElement mid = driver.findElement(By.xpath("(//span[contains(@class,'slds-truncate')])[" + i + "]/p[2]"));
			WebElement las = driver.findElement(By.xpath("(//span[contains(@class,'slds-truncate')])[" + i + "]/p[3]"));
			String ele = tru.getText() + " ".concat(mid.getText() + " ".concat(las.getText()));
			System.out.println(tru.getText() + " ".concat(mid.getText() + " ".concat(las.getText())));
			if (ele.equals("Trust | Compliance")) {
				driver.findElement(By.xpath("(//span[contains(@class,'slds-truncate')])[" + i + "]")).click();
				break;
			}
		}
		driver.findElement(By.xpath("(//div[@role='group']//button[contains(@class,'slds-button')])[1]")).click();
		driver.findElement(By.xpath("//label[@for='Nonprofit']")).click();
		List<WebElement> totalCert = driver.findElements(By.xpath("(//div[contains(@class,'slds-grid')])[4]/div"));
		for (int i = 1; i < totalCert.size(); i++) {
			String certificateText = driver
					.findElement(
							By.xpath("(//div[contains(@class,'slds-grid')])[4]/div[" + i + "]//div[2]//span[1]/span"))
					.getText();
			System.out.println("Cerificate is" + i + ": " + certificateText);
		}

		if (driver.findElement(By.xpath("//div[contains(@class,'button-group')]/button[2]")).isDisplayed()) {
			System.out
					.println(driver.findElement(By.xpath("//div[contains(@class,'button-group')]/button[2]")).getText()
							+ " is displayed");
			driver.findElement(By.xpath("//div[contains(@class,'button-group')]/button[2]")).click();
			Thread.sleep(3000);
			driver.quit();

		}

	}

}
