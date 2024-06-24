package sequential;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import commonUtility.PropertyFileread;
import excelUtility.ExcelRead;
import extendReport.ExtendTestManager;
import pomClasses.POMLogin;
import pomClasses.POMUnits;
import waitUtility.WaitUtility;
import webDriverUtility.DriverManager;

import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;

public class Login 
{
	public static WebDriver driver;
	POMLogin objPomLogin;
	
	static String url = "https://qalegend.com/billing/public/login";
	static String browser = "chrome";

	@Test(priority = 1, enabled = true)
	public void Loginexcel() throws Exception {
		String username=ExcelRead.readStringData(1, 0);
		 String password=ExcelRead.readNumbericData(1, 1);
		 objPomLogin.login(username,password);
	  
	  String current_url=driver.getCurrentUrl();
	  SoftAssert assertion =new SoftAssert();
	  assertion.assertEquals(PropertyFileread.readConfigFile("url"), current_url);
		assertion.assertAll();
	}
	
	@Test(priority = 2, enabled = true, groups={"failed"})
	public void logInFailed() throws IOException, InterruptedException {
		String username=ExcelRead.readStringData(1, 0);
		 String password=ExcelRead.readNumbericData(1, 1);
		 objPomLogin.login(username,password);

		String current_url = driver.getCurrentUrl();
		SoftAssert assertion = new SoftAssert();
		assertion.assertEquals("123", current_url);
		objPomLogin.signout();
		assertion.assertAll();
	}
	

	@BeforeTest(alwaysRun = true)
	public void beforeTest() {
		DriverManager objDriverManager = new DriverManager();
		objDriverManager.launchBrowser(url, browser);
		driver = objDriverManager.driver;
		objPomLogin = new POMLogin(driver);
	}


}