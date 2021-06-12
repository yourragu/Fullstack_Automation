package com.testng;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
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

public class Salesforce extends TestNGHooks{


	@Test
	public void sales() throws InterruptedException, IOException {		
		driver.findElement(By.xpath("//div[contains(@class,'slds-icon-waffle')]")).click();
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//p[text()='Sales']")).click();
		driver.findElement(By.xpath("//span[text()='View All Key Deals']")).click();
		driver.findElement(By.xpath("//a[contains(@class,'slds-button slds-button--reset')]")).click();
		driver.findElement(By.xpath("//span[text()='All Opportunities']")).click();
		driver.findElement(By.xpath("//div[@title='New']")).click();
		Thread.sleep(5000);		
		driver.findElement(By.xpath("//label[text()='Opportunity Name']/following::input")).sendKeys("SRM Steels");
		driver.findElement(By.xpath("//label[text()='Type']/following::input")).click();
		List<WebElement> elements = driver.findElements(By.xpath("//label[text()='Type']/following::span"));
		System.out.println("Type :" + elements.size());
		for (int i = 3; i <= elements.size(); i = i + 3) {
			String drpType = driver.findElement(By.xpath("//label[text()='Type']/following::span[" + i + "]")).getText();
			if (drpType.equalsIgnoreCase("New Customer")) {
				driver.findElement(By.xpath("//label[text()='Type']/following::span[" + i + "]")).click();
				break;
			}
		}
		driver.findElement(By.xpath("//label[text()='Amount']/following::input")).sendKeys("75000");
		driver.findElement(By.xpath("//label[text()='Close Date']/following::input")).sendKeys("6/20/2021");
		driver.findElement(By.xpath("//label[text()='Stage']/following::input")).click();
		List<WebElement> stage = driver.findElements(By.xpath("//label[text()='Stage']/following::span"));
		System.out.println("Stage :" + stage.size());
		for (int i = 3; i <= stage.size(); i = i + 3) {
			String drpstage = driver.findElement(By.xpath("//label[text()='Stage']/following::span[" + i + "]")).getText();
			if (drpstage.equalsIgnoreCase("Needs Analysis")) {
				driver.findElement(By.xpath("//label[text()='Stage']/following::span[" + i + "]")).click();
				break;
			}
		}
		Thread.sleep(7000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(100,250)");
		WebElement campaign = driver.findElement(By.xpath("//label[text()='Primary Campaign Source']/following::input"));
		js.executeScript("arguments[0].click();", campaign);
		
		WebElement selCampaign = driver.findElement(By.xpath("(//span[text()='Bootcamp'])[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(selCampaign)).click();

		driver.findElement(By.xpath("//label[text()='Lead Source']/following::input")).click();

		List<WebElement> leadSource = driver.findElements(By.xpath("//label[text()='Lead Source']/following::span"));
		System.out.println("Lead Source :" + leadSource.size());

		for (int i = 3; i <= leadSource.size(); i = i + 3) {
			String drpLeadSrc = driver.findElement(By.xpath("//label[text()='Lead Source']/following::span[" + i + "]")).getText();

			if (drpLeadSrc.equalsIgnoreCase("Partner Referral")) {
				driver.findElement(By.xpath("//label[text()='Lead Source']/following::span[" + i + "]")).click();
				break;
			}
		}

		driver.findElement(By.xpath("//button[text()='Save']")).click();		
		WebElement sucessMsg = driver.findElement(By.xpath(" //div[text()='\"SRM Steels\"']"));
		wait.until(ExpectedConditions.visibilityOf(sucessMsg));
		System.out.println("Success Message is "+sucessMsg.getText());		

		if (sucessMsg.getText().contains("SRM Steels")) {
			System.out.println("Sales record is created");

		} else {
			System.out.println("Sales record creation failed");
		}

	}

}
