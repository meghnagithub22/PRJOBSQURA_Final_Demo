package sequential;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import commonUtility.PropertyFileread;
import excelUtility.ExcelRead;
import pomClasses.POMCategories;
import pomClasses.POMLogin;
import pomClasses.POMUnits;
import waitUtility.WaitUtility;
import webDriverUtility.DriverManager;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.sql.Driver;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class CategoriesTC 
{
POMLogin objPOMLogin;

	
	POMCategories objPOMCategory;
	SoftAssert softassert=new SoftAssert();
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
	public void CategoryAdd(String catgryname,String catgrycode)throws InterruptedException{
		
		objPOMLogin.Clickproduct();
        objPOMCategory.Categorydetails();
        
		String objCatName=catgryname;
		String objCatCode=catgrycode;
		objPOMCategory.CategoryAdddetails(catgryname,catgrycode);	
		
		String actualmessage=objPOMCategory.SuccessMessageCat();
		String expectedmessage="Category added successfully";
		
		Assert.assertTrue(expectedmessage.contains(actualmessage));
		
		}
		@Test(priority=3,enabled=true)
		public void categoriesSearch()throws InterruptedException{
		
		boolean value1=objPOMCategory.searchcategoriesDetails(PropertyFileread.readConfigFile("fetchSearchCategory"));
		softassert.assertEquals(value1, true);
	
		}
		@Test(priority=4,enabled=true)
		public void deletecategoriesDetails() throws Exception {
			objPOMCategory.deletecategoriesDetails();
			String actualmessage=objPOMCategory.DeleteDialogboxcat();
			String expectedmessage="Category deleted successfully";
			softassert.assertTrue(expectedmessage.contains(actualmessage));
			
		}
  @BeforeTest
  public void beforeTest()throws InterruptedException {
		
	  DriverManager objDriverManager=new DriverManager();
	  objDriverManager.launchBrowser(url, browser);
	  driver=objDriverManager.driver;
	  objPOMLogin=new POMLogin(driver);
		
	  objPOMCategory=new POMCategories(driver);
  }
  @AfterTest
  public void afterTest() {
	 driver.close();
  }
  @DataProvider(name="testdata")
  public Object[][]TestDataFeed(){
	  //Create object array with 2 rows and 2 column-first parameter is row and second is column
	  Object[][] Categorydata=new Object[1][2];
	  //Enter data to row 0 column 0
	  Categorydata[0][0]="Category Demo";
	  //Enter data to row 0 column 1
	  Categorydata[0][1]="CD1";
      return Categorydata;
	  }

  }
  
