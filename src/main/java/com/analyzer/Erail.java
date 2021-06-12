package com.analyzer;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;

public class Erail {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://erail.in/");
		
		driver.findElement(By.id("txtStationFrom")).clear();
		driver.findElement(By.id("txtStationFrom")).sendKeys("MS"+Keys.TAB);
		
		driver.findElement(By.id("txtStationTo")).clear();
		driver.findElement(By.id("txtStationTo")).sendKeys("TPJ" + Keys.TAB);
		
		WebElement lastTrain = driver.findElement(By.xpath("//*[@id=\"divTrainsList\"]/table[1]/tbody/tr[31]/td[2]/a"));
			
		Coordinates coordinates = ((Locatable)lastTrain).getCoordinates();
		coordinates.inViewPort();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"divTrainsList\"]/table[1]/tbody/tr[31]/td[2]/a")).click();
	}

}
