package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import commonMethods.CommonMethods;

public class LoginPage extends CommonMethods {
	

/*	public LoginPage(Properties prop ) {
		
		this.prop = prop;
	}*/

	public LoginPage enterUserName()
	{
		getDriver().findElement(By.id("username")).sendKeys(getProp().getProperty("username"));
		return this;
	}
	
	public LoginPage enterPassword()
	{
		getDriver().findElement(By.id("password")).sendKeys(getProp().getProperty("password"));
		return this;
	}
	
	public Salesforce_HomePage clickLogin()
	{
		getDriver().findElement(By.id("Login")).click();
		return new Salesforce_HomePage();
	}

}
