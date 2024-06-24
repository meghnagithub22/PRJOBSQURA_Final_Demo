package crossBrowserTC;

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
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;

public class Login extends ExtendTestManager 
{
	public static WebDriver driver;
	POMLogin objPomLogin;
	
	ExtentTest test;
	public ExtentReports extent;
	
	static String url = "https://qalegend.com/billing/public/login";
	static String browser = "chrome";
	
	ExtendTestManager objTestManager;
	
	
	@Test(priority = 1, enabled = true)
	public void Loginexcel() throws Exception 
	{
		test=extent.createTest("Validating login scenario");
		boolean Status;
		
		String username=ExcelRead.readStringData(1, 0);
		 String password=ExcelRead.readNumbericData(1, 1);
		 objPomLogin.login(username,password);
	  
	  String current_url=driver.getCurrentUrl();
	  SoftAssert assertion =new SoftAssert();
	  if(PropertyFileread.readConfigFile("url").contains(current_url))
		{
			
		  assertion.assertTrue(true);
			Status=true;
		}
		else {
			assertion.assertTrue(false);
			Status=false;
		}
		
	  assertion.assertAll();
		if(Status==true)
		{
			test.log(com.aventstack.extentreports.Status.PASS, "Login successfully to the application");
		}
		else if(Status==false) {
			test.log(com.aventstack.extentreports.Status.FAIL, "Login failed");
		}
		
	}
	


	@Test(priority = 2, enabled = true, groups={"failed"})
	public void logInFailed() throws IOException, InterruptedException {
		String username=ExcelRead.readStringData(1, 0);
		 String password=ExcelRead.readNumbericData(1, 1);
		 objPomLogin.login(username,password);

		String current_url = driver.getCurrentUrl();
		SoftAssert asser = new SoftAssert();
		asser.assertEquals("123", current_url);
		objPomLogin.signout();
		asser.assertAll();
	}
	

	@BeforeTest(alwaysRun = true)
	@Parameters({"browser1"})
	public void beforeTest() {
		DriverManager objDriverManager = new DriverManager();
		objDriverManager.launchBrowser(url, browser);
		driver = objDriverManager.driver;
		objPomLogin = new POMLogin(driver);
		objTestManager=new ExtendTestManager();
		extent=objTestManager.extendreportgenerate();
		
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

}