package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;

import commonMethods.CommonMethods;

public class Salesforce_ProductsPage extends CommonMethods {

	private String NewProductName;

	
	/*public Salesforce_ProductsPage(Properties prop) {
		
		this.prop = prop;
	}*/

	
	
	public Salesforce_ProductsPage selectAllProducts() throws InterruptedException {
		getDriver().findElement(By.xpath("(//*[@data-key='down'])[4]")).click();
		Thread.sleep(2000);
		getDriver().findElement(By.xpath("//span[text()='All Products']")).click();
		return this;
	}

	public Salesforce_ProductsPage ClickNewButton() throws InterruptedException {
		getDriver().findElement(By.xpath("//div[text()='New']")).click();
		return this;
	}

	public Salesforce_ProductsPage enterNewProductsDetails(String productName, String productCode, String ProductDesc)
			throws InterruptedException {
		NewProductName = productName;
		getDriver().findElement(By.xpath("(//input[@class=' input'])[1]")).sendKeys(productName);
		getDriver().findElement(By.xpath("(//input[@class=' input'])[2]")).sendKeys(productCode);
		getDriver().findElement(By.xpath("//textarea[@role='textbox']")).sendKeys(ProductDesc);
		getDriver().findElement(By.xpath("(//span[text()='Save'])[2]")).click();
		return this;
	}

	public Salesforce_ProductsPage clickSave() throws InterruptedException {
		getDriver().findElement(By.xpath("(//span[text()='Save'])[2]")).click();
		return this;
	}

	public Salesforce_ProductsPage VerifyProductCreation() throws InterruptedException {
		String Title = getDriver().findElement(By.xpath("(//div[contains(@class,'page-header')])[7]")).getText();
		Thread.sleep(5000);
		if (Title.equalsIgnoreCase(NewProductName)) {
			System.out.println(NewProductName + " is created");
		} else {
			System.err.println(NewProductName + " is not created");
		}
		return this;
	}
	
	public Salesforce_ProductsPage searchProduct(String productName) throws InterruptedException {
		Thread.sleep(2000);
		getDriver().findElement(By.xpath("(//input[@type='search'])[2]")).sendKeys(productName);
		getDriver().findElement(By.xpath("(//input[@type='search'])[2]")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		return this;
	}
	
	public Salesforce_ProductsPage editProduct() throws InterruptedException {
		getDriver().findElement(By.xpath("//table[contains(@class,'uiVirtualDataTable')]/tbody/tr[1]/td[6]//a")).click();
		Thread.sleep(2000);
		getDriver().findElement(By.xpath("((//ul[@class='scrollable'])[3]/li)[1]/a")).click();
		getDriver().findElement(By.xpath("(//input[@class=' input'])[1]")).clear();
		getDriver().findElement(By.xpath("(//input[@class=' input'])[1]")).sendKeys("test_new1");
		getDriver().findElement(By.xpath("(//span[text()='Save'])[2]")).click();
		return this;
	}
	
	public Salesforce_ProductsPage verifyEditProductName() throws InterruptedException {
		String toastMsg = getDriver().findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();		
		Thread.sleep(3000);
		if (toastMsg.contains("test_new1")) {
			System.out.println(toastMsg + " -> Product is successfully Edited");
		} else {
			System.out.println(toastMsg + " -> Product is not Edited");
		}
		return this;
	}



}
