package commonMethods;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;
import utility.ReadExcel;

public class CommonMethods{

	//public ChromiumDriver driver;
	//public Properties prop;
	public FileInputStream file;
	public JavascriptExecutor js;

	private static final ThreadLocal<RemoteWebDriver> tlDriver = new ThreadLocal<RemoteWebDriver>();
	private static final ThreadLocal<Properties> tlprop = new ThreadLocal<Properties>();
	//private static final ThreadLocal<String> sheetName = new ThreadLocal<String>();
	public String sheetName;
	
	public void setDriver(RemoteWebDriver driver) {
		tlDriver.set(driver);
	}
	
	public WebDriver getDriver() {
		return tlDriver.get();
	}

	public void setProp(Properties prop) {
		tlprop.set(prop);
	}
	
	public Properties getProp() {
		return tlprop.get();
	}
	
	/*public void setSheetName(String sheet) {
		sheetName.set(sheet);
	}
	
	public String getSheetName() {
		return sheetName.get();
	}*/

	@DataProvider(name="readvalues")
	public String[][] sendExcelData() throws IOException
	{
		
		//return ReadExcel.excel(this.getClass().getSimpleName());
		return ReadExcel.excel(sheetName);
	}

	@BeforeMethod
	public void launchBrowser() throws IOException {

		file = new FileInputStream(".\\src\\main\\resources\\utility\\config.properties");
		setProp(new Properties());		
		getProp().load(file);
		if (getProp().getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			setDriver(new ChromeDriver());
			//driver = new ChromeDriver();
			getDriver().navigate().to(getProp().getProperty("url"));
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} else if (getProp().getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			setDriver(new FirefoxDriver());
			getDriver().navigate().to(getProp().getProperty("url"));
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} else {
			System.err.println("Specified browser not found.Please check name of the driver");
		}
	}

	@AfterMethod
	public void closeBrowser() {
		getDriver().close();
	}
}
