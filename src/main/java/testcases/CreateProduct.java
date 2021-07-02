package testcases;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commonMethods.CommonMethods;
import pages.LoginPage;

public class CreateProduct extends CommonMethods{
	
	@BeforeTest
	public void readSheet()
	{
		sheetName = "CreateProduct";
	}
	
	//@Test(retryAnalyzer = utility.RetryLogic.class)
	@Test(dataProvider="readvalues")
	public void runCreate_Product(String productName,String productCode,String desc) throws IOException, InterruptedException
	{		
		new LoginPage()
		.enterUserName()
		.enterPassword()
		.clickLogin()
		.clickAppLauncher()
		.clickProducts()
		.selectAllProducts()
		.ClickNewButton()
		.enterNewProductsDetails(productName,productCode,desc)
		.clickSave()
		.VerifyProductCreation()		
		;
		
	}

}
