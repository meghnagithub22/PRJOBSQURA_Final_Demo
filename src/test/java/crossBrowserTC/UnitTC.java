package crossBrowserTC;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import commonUtility.PropertyFileread;
import excelUtility.ExcelRead;
import extendReport.ExtendTestManager;
import pomClasses.POMAddProduct;
import pomClasses.POMLogin;
import pomClasses.POMUnits;
import waitUtility.WaitUtility;
import webDriverUtility.DriverManager;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.sql.Driver;
import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class UnitTC extends ExtendTestManager 
{
	public static WebDriver driver;
	static String url="https://qalegend.com/billing/public/login";
	//static String browser="chrome";

	POMLogin objPOMLogin;
	POMUnits objPOMUnit;
	
	ExtentTest test;
	public ExtentReports extent;
	ExtendTestManager objTestManager;
	
	SoftAssert softassert=new SoftAssert();
	

	
	@Test(priority=1,enabled=true)
	public void Unitexcel() throws Exception 
	{
		test=extent.createTest("Validating login scenario");
		boolean Status;
       

		String username=ExcelRead.readStringData(1, 0);
		 String password=ExcelRead.readNumbericData(1, 1);
		  
	    objPOMLogin.login(username, password);
	
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

	
	@Test(priority=2,enabled=true,dataProvider="testdata")
	public void UnitAdd(String name,String shortname) throws Exception 
	{
		test=extent.createTest("Validating login scenario");
		
		
		objPOMLogin.Clickproduct();
		objPOMUnit.UnitDetails();
	
		String objunitName=name;
		String objShortName=shortname;
		objPOMUnit.UnitAddDetails(name,shortname);	
		
	    String actual_message=objPOMUnit.successmessage();
	    String expected_message="Unit added successfully";
	  
	    Assert.assertTrue(actual_message.contains(expected_message));

	    test.log(com.aventstack.extentreports.Status.PASS, "Unit added successfully to the application");
	    
	}
	
	
	@Test(priority=3,enabled=true)
	public void SearchUnit() throws Exception
	{
	boolean value=objPOMUnit.UnitSearchDetails(PropertyFileread.readConfigFile("fetchsearch"));
	
	SoftAssert assertion=new SoftAssert();
	assertion.assertEquals(value, true);
	assertion.assertAll();
	}

	
	
	@Test(priority=4,enabled=true)
	public void deleteunit() throws Exception {
		objPOMUnit.deleteunitDetails();
		
		String actualmessage=objPOMUnit.DeleteMessage();
		String expectedmessage="Unit deleted successfully";
	softassert.assertTrue(expectedmessage.contains(actualmessage));
		
	}
  @BeforeTest
  @Parameters({"browser2"})
  public void beforeTest(String browser2)throws InterruptedException {
		
	  DriverManager objDriverManager=new DriverManager();
	  objDriverManager.launchBrowser(url, browser2);
	  driver=objDriverManager.driver;
	  objPOMLogin=new POMLogin(driver);
		objPOMUnit=new POMUnits(driver);
		
		objTestManager=new ExtendTestManager();
		extent=objTestManager.extendreportgenerate();
  }

  
  @AfterTest
	public void afterTest() {
		objTestManager.closereport();
		driver.close();
	
	}
 
  @DataProvider(name="testdata")
  public Object[][]TestDataFeed(){
	  //Create object array with 2 rows and 2 column-first parameter is row and second is column
	  Object[][] Unitdata=new Object[1][2];
	  //Enter data to row 0 column 0
	  Unitdata[0][0]="Unit Demo";
	  //Enter data to row 0 column 1
	  Unitdata[0][1]="UD1";
      return Unitdata;
	  }

  }