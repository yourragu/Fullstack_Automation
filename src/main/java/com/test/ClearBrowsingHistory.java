package com.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ClearBrowsingHistory {

	ChromeDriver driver;

	@Test
	public void shadowRootDOM() throws IOException, InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		driver = new ChromeDriver(options);

		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\utils\\config.properties");
		Properties prop = new Properties();
		prop.load(file);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("chrome://settings/");
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		WebElement settings = (WebElement) jse.executeScript(
				"return document.querySelector(\"settings-ui\").shadowRoot.querySelector(\"#leftMenu\").shadowRoot.querySelector(\"#autofill\")");

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", settings);
		Thread.sleep(3000);
		WebElement safetyCheck = (WebElement) jse.executeScript(
				"return document.querySelector(\"settings-ui\").shadowRoot.querySelector(\"#leftMenu\").shadowRoot.querySelector(\"#safetyCheck\")");

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", safetyCheck);

	}

}
