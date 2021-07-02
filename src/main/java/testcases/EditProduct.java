package testcases;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commonMethods.CommonMethods;
import pages.LoginPage;

public class EditProduct extends CommonMethods{
	
	@BeforeTest
	public void readSheet()
	{
		sheetName = "EditProduct";
	}
	
	
	//@Test(retryAnalyzer = utility.RetryLogic.class)
	@Test(dataProvider="readvalues")
	public void runEdit_Product(String productName) throws IOException, InterruptedException
	{
		
		new LoginPage()
		.enterUserName()
		.enterPassword()
		.clickLogin()
		.clickAppLauncher()
		.clickProducts()
		.selectAllProducts()
		.searchProduct(productName)
		.editProduct()
		.verifyEditProductName()
		
		;
		
	}

}
