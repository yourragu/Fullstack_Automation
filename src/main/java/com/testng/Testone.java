package com.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testone {
	RemoteWebDriver driver;

	@Parameters({"browserName"})
	@Test
	public void google(@Optional("firefox") String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			System.out.println("google website");
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("https://www.google.com/");
			driver.findElement(By.name("q")).sendKeys("selenium");
			driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		} else {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.get("https://www.google.com/");
			driver.findElement(By.name("q")).sendKeys("selenium");
			driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		}

	}
	
	@Parameters({"browserName"})
	@Test
	public void icici(@Optional("firefox") String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			System.out.println("ICICI net banking");
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("https://www.icicibank.com/Personal-Banking/insta-banking/internet-banking/index.page");
			driver.findElement(By.xpath("//a[text()='LOGIN']")).click();
		} else {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.get("https://www.icicibank.com/Personal-Banking/insta-banking/internet-banking/index.page");
			driver.findElement(By.xpath("//a[text()='LOGIN']")).click();
		}
	}

	@Parameters({"browserName"})
	@Test
	public void unitedBreweries(@Optional("firefox")  String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			System.out.println("Unitedbreweries limited");
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("https://www.unitedbreweries.com/");
			driver.findElement(By.xpath("//span[text()='Yes']")).click();
		} else {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.get("https://www.unitedbreweries.com/");
			driver.findElement(By.xpath("//span[text()='Yes']")).click();

		}

	}

}
