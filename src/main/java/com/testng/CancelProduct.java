package com.testng;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.utils.ReadExcel;

public class CancelProduct extends TestNGHooks {
	JavascriptExecutor js;
	String filePath = ".\\src\\main\\resources\\testdata\\";
	String wbName = "CreateLocation.xlsx";
	String fileName = "CancelProduct";

	@Test(dataProvider = "readValues", threadPoolSize = 2)
	public void cancelproduct(String productName, String productCode, String ProductDesc) throws InterruptedException {
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
		WebElement productActive = driver.findElement(By.xpath("(//label[contains(@class,'inputLabel')])[2]/following-sibling::input"));
		js.executeScript("arguments[0].click();",productActive);
		driver.findElement(By.xpath("(//span[text()='Cancel'])[2]")).click();
		driver.findElement(By.xpath("(//input[@type='search'])[2]")).sendKeys(productName);
		driver.findElement(By.xpath("(//input[@type='search'])[2]")).sendKeys(Keys.ENTER);		
		Thread.sleep(3000);		
		String NoData = driver.findElement(By.xpath("//span[text()='No items to display.']")).getText();		
		if (NoData.equalsIgnoreCase("No items to display.")) {
			System.out.println(productName + " creation is cancelled");
		} else {
			System.err.println(productName + " is already exist in the table");
		}
	}

	@DataProvider(name = "readValues")
	public String[][] sendData() throws IOException {
		return ReadExcel.readData(filePath, wbName, fileName);
	}

}
