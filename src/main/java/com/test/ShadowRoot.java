package com.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ShadowRoot {

	ChromeDriver driver;

	@Test
	public void shadowRootDOM() throws IOException, InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		// WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		driver = new ChromeDriver(options);

		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\utils\\config.properties");
		Properties prop = new Properties();
		prop.load(file);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("chrome://history/");
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		WebElement clearHistory = (WebElement) jse.executeScript(
				"return document.querySelector('history-app').shadowRoot.querySelector('history-side-bar').shadowRoot.querySelector('#clear-browsing-data')");
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", clearHistory);

		Set<String> windowHandles = driver.getWindowHandles();
		windowHandles.forEach((eachWindow) -> {
			driver.switchTo().window(eachWindow);
		});

		Thread.sleep(3000);

		WebElement drpDown = (WebElement) jse.executeScript(
				"return document.querySelector(\"body > settings-ui\").shadowRoot.querySelector(\"#main\").shadowRoot.querySelector(\"settings-basic-page\").shadowRoot.querySelector(\"#basicPage > settings-section:nth-child(8) > settings-privacy-page\").shadowRoot.querySelector(\"settings-clear-browsing-data-dialog\").shadowRoot.querySelector(\"#clearFromBasic\").shadowRoot.querySelector(\"#dropdownMenu\")");
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", drpDown);
		Thread.sleep(3000);

		WebElement option = (WebElement) jse.executeScript(
				"return document.querySelector(\"body > settings-ui\").shadowRoot.querySelector(\"#main\").shadowRoot.querySelector(\"settings-basic-page\").shadowRoot.querySelector(\"#basicPage > settings-section:nth-child(8) > settings-privacy-page\").shadowRoot.querySelector(\"settings-clear-browsing-data-dialog\").shadowRoot.querySelector(\"#clearFromBasic\").shadowRoot.querySelector(\"#dropdownMenu > option:nth-child(5)\")");
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
		Thread.sleep(3000);
		WebElement clearBtn = (WebElement) jse.executeScript(
				"return document.querySelector(\"body > settings-ui\").shadowRoot.querySelector(\"#main\").shadowRoot.querySelector(\"settings-basic-page\").shadowRoot.querySelector(\"#basicPage > settings-section:nth-child(8) > settings-privacy-page\").shadowRoot.querySelector(\"settings-clear-browsing-data-dialog\").shadowRoot.querySelector(\"#clearBrowsingDataConfirm\")");
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", clearBtn);

		/*
		 * WebElement allTime = (WebElement) jse.executeScript(
		 * "return document.querySelector(\"body > settings-ui\").shadowRoot.querySelector(\"#main\").shadowRoot.querySelector(\"settings-basic-page\").shadowRoot.querySelector(\"#basicPage > settings-section:nth-child(8) > settings-privacy-page\").shadowRoot.querySelector(\"settings-clear-browsing-data-dialog\").shadowRoot.querySelector(\"#clearFromBasic\").shadowRoot.querySelector(\"#dropdownMenu > option:nth-child(5)\")"
		 * ); ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
		 * allTime);
		 */

	}

}
