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

public class CreateNewProduct extends TestNGHooks {
	JavascriptExecutor js;

	@BeforeTest
	public void readSheet()
	{
		sheetName = "CreateProduct";
	}
	

	@Test(dataProvider = "readValues", enabled = true,threadPoolSize=1)
	public void createLoc(String productName, String productCode, String ProductDesc) throws InterruptedException {
		System.out.println("***Execution started for Create new product***");
		driver.findElement(By.id("Login")).click();
		WebElement appLauncher = driver.findElement(By.xpath("//div[@role='navigation']//button[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(appLauncher)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		Thread.sleep(5000);
		js = (JavascriptExecutor) driver;
		WebElement products = driver.findElement(By.xpath("//p[text()='Products']"));
		js.executeScript("arguments[0].scrollIntoView();", products);
		products.click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//*[@data-key='down'])[4]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='All Products']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()='New']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[@class=' input'])[1]")).sendKeys(productName);
		driver.findElement(By.xpath("(//input[@class=' input'])[2]")).sendKeys(productCode);
		driver.findElement(By.xpath("//textarea[@role='textbox']")).sendKeys(ProductDesc);
		driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
		String Title = driver.findElement(By.xpath("(//div[contains(@class,'page-header')])[7]")).getText();
		Thread.sleep(5000);
		if (Title.equalsIgnoreCase(productName)) {
			System.out.println(productName + " is created");
		} else {
			System.err.println(productName + " is not created");
		}
	}



}
