package com.testng;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.baseclass.TestNGHooks;
import com.utils.ReadExcel;

public class EditProduct extends TestNGHooks {
	JavascriptExecutor js;

	@BeforeTest
	public void readSheet()
	{
		sheetName = "EditProducts";
	}
	

	@Test(dataProvider = "readValues", enabled = true,threadPoolSize=1)
	public void editProduc(String oldProductName,String newProductName) throws InterruptedException {
		System.out.println("***Execution started for Edit product***");
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
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[@type='search'])[2]")).sendKeys(oldProductName);
		driver.findElement(By.xpath("(//input[@type='search'])[2]")).sendKeys(Keys.ENTER);
		Thread.sleep(10000);
		driver.findElement(By.xpath("//table[contains(@class,'uiVirtualDataTable')]/tbody/tr[1]/td[6]//a")).click();
		Thread.sleep(2000);		
		driver.findElement(By.xpath("((//ul[@class='scrollable'])[3]/li)[1]/a")).click();
		driver.findElement(By.xpath("(//input[@class=' input'])[1]")).clear();
		driver.findElement(By.xpath("(//input[@class=' input'])[1]")).sendKeys(newProductName);
		driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
		Thread.sleep(5000);
		String toastMsg = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();		
		Thread.sleep(3000);
		if (toastMsg.contains(newProductName)) {
			System.out.println(newProductName + " -> Product is successfully Edited");
		} else {
			System.out.println(newProductName + " -> Product is not Edited");
		}
	}


}
