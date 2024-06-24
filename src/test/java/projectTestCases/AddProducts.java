package projectTestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import commonUtility.PropertyFileread;
import excelUtility.ExcelRead;
import pomClasses.POMAddProduct;
import pomClasses.POMBrands;
import pomClasses.POMCategories;
import pomClasses.POMLogin;
import pomClasses.POMUnits;
import webDriverUtility.DriverManager;

public class AddProducts
{
	
	public static WebDriver driver;	
    static String url="https://qalegend.com/billing/public/login";
    static String browser="chrome";
    
    POMLogin objPOMLogin;
	POMAddProduct objPOMAddProd;

	
   @Test(priority=1,enabled=true)
    public void Addproexcel() throws Exception
   {
	    String username=ExcelRead.readStringData(1, 0);
	    String password=ExcelRead.readNumbericData(1, 1);
	    objPOMLogin.login(username, password);

        String current_url=driver.getCurrentUrl();
        SoftAssert assertion =new SoftAssert();
        assertion.assertEquals(PropertyFileread.readConfigFile("url"),current_url);
        assertion.assertAll();
}

    @Test(priority=2,enabled=true)
		public void AddProductrun() throws InterruptedException 
  {
	      objPOMLogin.Clickproduct();
	      objPOMAddProd=new POMAddProduct(driver);
	      objPOMAddProd.AddProductDetails();
		  String message=objPOMAddProd.successmessage();
		  String expected_message="Product added successfully";
		  SoftAssert assertion=new SoftAssert();
		  assertion.assertEquals(expected_message, message);
		  assertion.assertAll();
	
  }
 
  @BeforeTest	
  public void beforeTest()throws InterruptedException {
		
	  DriverManager objDriverManager=new DriverManager();
	  objDriverManager.launchBrowser(url, browser);
	  driver=objDriverManager.driver;
	  objPOMLogin=new POMLogin(driver);
	  }
  @AfterTest
  public void afterTest() {
	  driver.close();
  }
}

  
  

  


