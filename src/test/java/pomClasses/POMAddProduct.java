package pomClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtility.PropertyFileread;
import waitUtility.WaitUtility;
import webDriverUtility.WebDriversActions;

public class POMAddProduct
{
	WebDriver driver;
	WebDriversActions objAddProActions;
    WaitUtility objWait;
    
	
	
	public POMAddProduct(WebDriver driver)
	{
		this.driver=driver;
		objAddProActions = new WebDriversActions(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//li[@id='tour_step5']/ul/li[2]/a")
	 public WebElement AddProductbtn;

	@FindBy(xpath="//input[@id='name']")
	public WebElement AddProductName;

	@FindBy(xpath="//span[@id='select2-brand_id-container'] ")
	public WebElement SelectBrandid;
	
	 @FindBy(xpath="/html/body/span/span/span[1]/input")
	public WebElement AddProBrandSearch;
	
	 @FindBy(xpath="//ul[@id='select2-brand_id-results']/li[1]")
	public WebElement AddProBrandName;

	 @FindBy(xpath="//span[@id='select2-category_id-container']")
	public WebElement SelectCatid;

	 @FindBy(xpath="/html/body/span/span/span[1]/input")
	public WebElement AddProCatSearch;
	
	 @FindBy(xpath="//ul[@id='select2-category_id-results']/li[1]")
	public WebElement AddProCatName;

	@FindBy(xpath="//*[@id='alert_quantity']")
	public WebElement AddProAlertQty ;


	@FindBy (xpath="//*[@id='expiry_period']")
	public WebElement AddProExpiry;

	@FindBy(xpath="//*[@id='single_dpp']")
	public WebElement AddProExcTax;

	 @FindBy(xpath="//div[@class='btn-group']/button[4]")
	public WebElement AddProSaveBtn;

	@FindBy(xpath="//*[@id='toast-container']/div")
	 public WebElement AddProdpopupmsg;
	

		public void AddProductDetails() throws InterruptedException
		{
		
		 objAddProActions.click(AddProductbtn);
		 objWait=new WaitUtility(driver);
		 objWait.normalWait(2000);
		 objAddProActions.sendkeys(AddProductName,PropertyFileread.readConfigFile("Productname"));
		 objAddProActions.click(SelectBrandid);
		 objWait.normalWait(2000);
		 objAddProActions.sendkeys(AddProBrandSearch,PropertyFileread.readConfigFile("Brandproduct"));
		 objAddProActions.click(AddProBrandName);
		 objWait.normalWait(2000);
		 objAddProActions.click(SelectCatid);
		 objWait.normalWait(2000);
		 objAddProActions.sendkeys(AddProCatSearch,PropertyFileread.readConfigFile("Categoryproduct"));
		 objAddProActions.click(AddProCatName);
		 objWait.normalWait(2000);
		 objAddProActions.sendkeys(AddProAlertQty,PropertyFileread.readConfigFile("AlertQuality")); 
		 objWait.normalWait(2000);
		 objAddProActions.sendkeys(AddProExpiry,PropertyFileread.readConfigFile("Expiry"));
		 objWait.normalWait(2000);
		 objAddProActions.sendkeys(AddProExcTax,PropertyFileread.readConfigFile("ExcTax"));
		 objWait.normalWait(2000);
		 objAddProActions.click(AddProSaveBtn);
		 objWait.normalWait(2000);
} 
		public String successmessage()
		 {
	
			objWait=new WaitUtility(driver);
			 objWait.presenceOfElementlocated(By.xpath("//*[@id='toast-container']/div"),3);
			 return objAddProActions.getText(AddProdpopupmsg);
		 }
}
		

	
	
	
	
	
	
	
	
	

