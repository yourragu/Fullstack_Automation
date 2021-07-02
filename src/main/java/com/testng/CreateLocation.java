package com.testng;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.baseclass.TestNGHooks;
import com.utils.ReadExcel;

public class CreateLocation extends TestNGHooks {
	JavascriptExecutor js;
	
	@BeforeTest
	public void readSheet()
	{
		sheetName = "CreateLocation";
	}
	
	@Test(dataProvider = "readValues")
	public void createLoc(String locName, String locType) throws InterruptedException {
		driver.findElement(By.id("Login")).click();
		WebElement appLauncher = driver.findElement(By.xpath("//div[@role='navigation']//button[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(appLauncher)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		Thread.sleep(5000);
		js = (JavascriptExecutor)driver;
		WebElement location = driver.findElement(By.xpath("//p[text()='Locations']"));
		js.executeScript("arguments[0].scrollIntoView();",location);
		location.click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[text()='New']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[@class=' input'])[1]")).sendKeys(locName);
		driver.findElement(By.xpath("//a[@class='select']")).click();
		driver.findElement(By.xpath("(//li[contains(@class,'uiRadioMenuItem')])[2]/a")).click();
		driver.findElement(By.xpath("(//input[@class=' input'])[2]")).sendKeys(locType);
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		String label = driver.findElement(By.xpath("(//span[@class='uiOutputText'])[1]")).getText();
		if (label.equalsIgnoreCase(locName)) {
			System.out.println("Label created and matched");
		} else {
			System.out.println("Label is not created and matched");
		}

	}



}
