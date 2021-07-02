package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import commonMethods.CommonMethods;

public class Salesforce_HomePage extends CommonMethods {
	
	
/*	public Salesforce_HomePage(Properties prop) {
		
		this.prop = prop;
	}
*/
	
	
	public Salesforce_HomePage clickAppLauncher() throws InterruptedException
	{
		Thread.sleep(3000);
		getDriver().findElement(By.xpath("//div[@role='navigation']//button[1]")).click();		
		Thread.sleep(3000);
		getDriver().findElement(By.xpath("//button[text()='View All']")).click();
		return this;
	}
	
	public Salesforce_ProductsPage clickProducts() throws InterruptedException
	{
		WebElement products = getDriver().findElement(By.xpath("//p[text()='Products']"));
		js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].scrollIntoView();", products);
		products.click();
		return new Salesforce_ProductsPage();
	}


}
