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

public class DeleteProduct extends TestNGHooks {
	JavascriptExecutor js;

	@BeforeTest
	public void readSheet() {
		sheetName = "DeleteProduct";
	}

	@Test(dataProvider = "readValues", enabled = true,threadPoolSize=1)
	public void deleteProduct(String ProductName) throws InterruptedException {
		System.out.println("***Execution started for delete product***");
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
		driver.findElement(By.xpath("(//input[@type='search'])[2]")).sendKeys(ProductName);
		driver.findElement(By.xpath("(//input[@type='search'])[2]")).sendKeys(Keys.ENTER);
		Thread.sleep(10000);
		driver.findElement(By.xpath("(//table[tbody])[1]/tbody/tr[1]/th//a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//li[contains(@class,'oneActionsDropDown')])[1]//a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@title='Delete']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Delete']")).click();
		Thread.sleep(5000);
		String toastMsg = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();
		Thread.sleep(3000);
		if (toastMsg.contains(ProductName)) {
			System.out.println(ProductName + " -> Product is successfully Deleted");
		} else {
			System.out.println(ProductName + " -> Product is not Deleted");
		}
	}

}
