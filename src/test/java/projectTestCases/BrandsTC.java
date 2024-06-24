package projectTestCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import commonUtility.PropertyFileread;
import excelUtility.ExcelRead;
import pomClasses.POMBrands;
import pomClasses.POMCategories;
import pomClasses.POMLogin;
import pomClasses.POMUnits;
import waitUtility.WaitUtility;
import webDriverUtility.DriverManager;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class BrandsTC 
{
	POMLogin objPOMLogin;
	POMUnits objPOMUnit;
	POMCategories objPOMCategories;
	POMBrands objPOMBrand;
		
		static String url="https://qalegend.com/billing/public/login";
		static String browser="chrome";

		public static WebDriver driver;
		@Test(priority=1,enabled=true)
		public void excel() throws Exception {
			
		String username=ExcelRead.readStringData(1, 0);
		String password=ExcelRead.readNumbericData(1, 1);
		objPOMLogin.login(username, password);
		
		String currenturl=driver.getCurrentUrl();
		SoftAssert url=new SoftAssert();
		url.assertEquals(PropertyFileread.readConfigFile("url") ,currenturl);
		url.assertAll();
		}
		
		@Test(priority=2,enabled=true,dataProvider="testdata")
		public void BrandAdd(String brandname,String brandDesc)throws InterruptedException{
			
			objPOMLogin.Clickproduct();
			objPOMBrand.Branddetails();
			
			String objBrandName= brandname;
			String objDescBrand=brandDesc;
			objPOMBrand.BrandAddDetails(brandname,brandDesc);	
			
		    String exp_message="Brand added successfully";
			String actl_message=objPOMBrand.brandmessage;
			Assert.assertTrue(exp_message.contains(actl_message));
		}
			
		@Test(priority=3,enabled=true)
			public void brandSearch()throws InterruptedException{
			
			boolean value2=objPOMBrand.searchBrandDetails(PropertyFileread.readConfigFile("fetchSearchBrand"));
			Assert.assertEquals(value2, true);
			}
		
		
	  @BeforeTest
	  public void beforeTest()throws InterruptedException {
			
		  DriverManager objDriverManager=new DriverManager();
		  objDriverManager.launchBrowser(url, browser);
		  driver=objDriverManager.driver;
		  objPOMLogin=new POMLogin(driver);
		  objPOMBrand=new POMBrands(driver);
	  }
	  @AfterTest
	  public void afterTest() {
		  driver.close();
	  }
	  @DataProvider(name="testdata")
	  public Object[][]TestDataFeed(){
		  //Create object array with 2 rows and 2 column-first parameter is row and second is column
		  Object[][] Branddata=new Object[1][2];
		  //Enter data to row 0 column 0
		  Branddata[0][0]="Brand Demo";
		  //Enter data to row 0 column 1
		  Branddata[0][1]="BD1";
	      return Branddata;
		  }
	  }