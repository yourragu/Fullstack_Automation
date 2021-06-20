package com.testng;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateParentTer extends TestNGHooks{

	@Parameters({"Browser"})
	@Test(dependsOnMethods= {"com.testng.EditworkType.deleteworkTyp"})
	public void CreateParentTer(String browser) throws InterruptedException, IOException {
		
		Capabilities capabilities = driver.getCapabilities();
		Map<String, Object> asMap = capabilities.asMap();
		asMap.forEach((key, value)->{
			System.out.println("Key :"+key + "" + "Value :" +value);
			});
		
		//String version = driver.getCapabilities().getVersion();
		//System.out.println(browserName +":"+version);
		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\utils\\config.properties");
		Properties prop = new Properties();
		prop.load(file);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.get("https://login.salesforce.com/");
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.id("password")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.xpath("//input[@name='Login']")).click();
		WebElement appLauncher = driver.findElement(By.xpath("(//div[contains(@class,'tooltipTrigger')])[6]"));
		wait.until(ExpectedConditions.elementToBeClickable(appLauncher)).click();
		
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		Thread.sleep(3000); 
		WebElement st = driver.findElement(By.xpath("//p[text()='Service Territories']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", st);
		wait.until(ExpectedConditions.elementToBeClickable(st)).click();
		Thread.sleep(3000);
		
		WebElement stSearch = driver.findElement(By.xpath("//input[contains(@name,'ServiceTerritory-search')]"));
		wait.until(ExpectedConditions.elementToBeClickable(stSearch)).sendKeys("Brinda");
		Thread.sleep(3000);
		stSearch.sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//table[tbody]/tbody/tr[1]/td[5]")).click();
		driver.findElement(By.xpath("//table[tbody]/tbody/tr[1]/td[5]//button")).click();		
		driver.findElement(By.xpath("//span[text()='New Service Territory']")).click();
		String x = "Gowtham Adani";
		 driver.findElement(By.xpath("(//label[contains(@class,'uiLabel')])[3]/following-sibling::input[1]")).sendKeys(x);
		
			
		driver.findElement(By.xpath("//input[@placeholder='Search Operating Hours...']")).click();
		
		List<WebElement> operatingHrs = driver.findElements(By.xpath("(//ul[contains(@class,'lookup__list')])[2]/li"));
			for(int i=1;i<operatingHrs.size();i++)
			{
				if(i==3) {
					driver.findElement(By.xpath("(//ul[contains(@class,'lookup__list')])[2]/li["+i+"]")).click();
					break;
				}
			}
	
	WebElement btnSave = driver.findElement(By.xpath("(//span[text()='Save'])[2]"));
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].click();", btnSave);
	Thread.sleep(3000);
	
	String getParentTerName = driver.findElement(By.xpath("//table[tbody]/tbody/tr[1]/td[5]//a")).getAttribute("innerHTML");
	System.out.println("2nd Parent Ter is "+getParentTerName);
	
	if(getParentTerName.contains(x))
	{
		System.out.println("Parent Territory Name created and displayed");
	}else
	{
		System.out.println("Parent Territory Name is not created and displayed");
	}
	}
	
	@Parameters({"Browser"})
	@Test(dependsOnMethods= {"CreateParentTer"})
	public void boardExamSch() throws InterruptedException, IOException {
	
		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\utils\\config.properties");
		Properties prop = new Properties();
		prop.load(file);		
		driver.get("https://login.salesforce.com/");
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.id("password")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.id("Login")).click();
		
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();
		
		Set<String> windowHandles = driver.getWindowHandles();
		
		for (String window : windowHandles) {
			driver.switchTo().window(window);
			String title = driver.getTitle();
			if(title.equalsIgnoreCase("Certification - Architect Overview"))
					{
						System.out.println("Title of the page:"+title);
					}
			
		}
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		WebElement resource = driver.findElement(By.xpath("//span[text()='Resources']"));
		WebElement certification = driver.findElement(By.xpath("//span[text()='Salesforce Certification ']/parent::a"));
		action.moveToElement(resource).moveToElement(certification).click().build().perform();
		Set<String> windowHandles2 = driver.getWindowHandles();
		
		for (String window2 : windowHandles2) {
			driver.switchTo().window(window2);
			String title = driver.getTitle();
			if(title.equalsIgnoreCase("Certification - Architect Overview"))
					{
						System.out.println("Title of the page:"+title);
					}
			
		}
		driver.findElement(By.xpath("//div[text()='Salesforce Architect']")).click();
		WebElement ta = driver.findElement(By.xpath("//a[text()='Technical Architect']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", ta);
		
		driver.findElement(By.xpath("//a[text()='More Details']")).click();
		
	}
	
	@Parameters({"Browser"})
	@Test(dependsOnMethods= {"boardExamSch"})
	public void createIndiv(String browser) throws InterruptedException, IOException {
		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\utils\\config.properties");
		Properties prop = new Properties();
		prop.load(file);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.get("https://login.salesforce.com/");
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.id("password")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.id("Login")).click();
		WebElement appLauncher = driver.findElement(By.xpath("//div[@role='navigation']//button[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(appLauncher)).click();
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		Thread.sleep(5000); 
		WebElement workTypeGrp = driver.findElement(By.xpath("//p[text()='Work Type Groups']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", workTypeGrp);
		wait.until(ExpectedConditions.elementToBeClickable(workTypeGrp)).click();
		WebElement btnNew = driver.findElement(By.xpath("//div[@title='New']"));
		wait.until(ExpectedConditions.elementToBeClickable(btnNew)).click();
		//driver.findElement(By.xpath("//input[@class=' input']")).sendKeys("Automation");		
		driver.findElement(By.xpath("//textarea[@class=' textarea']")).sendKeys("Automation");		
		driver.findElement(By.xpath("//a[@class='select']")).click();
		driver.findElement(By.xpath("//a[text()='Capacity']")).click();		
		driver.findElement(By.xpath("//button[@title='Save']//span[1]")).click();				
		String msg = driver.findElement(By.xpath("//li[text()='These required fields must be completed: Work Type Group Name']")).getText();
		if(msg.equalsIgnoreCase("These required fields must be completed: Work Type Group Name"))
		{
			System.out.println("Worktype group name error message is captured");
		}
		else
		{
			System.out.println("Worktype group name is not captured");
		}

	}


	
}
