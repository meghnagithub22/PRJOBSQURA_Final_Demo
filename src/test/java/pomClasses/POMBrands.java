package pomClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import waitUtility.WaitUtility;
import webDriverUtility.WebDriversActions;

public class POMBrands 
{
	WebDriversActions objDriversActions;
	WaitUtility objWait;
	WebDriver driver;
	public static String brandmessage = "";

	public POMBrands(WebDriver driver) {
		this.driver = driver;
		objDriversActions = new WebDriversActions(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id=\"tour_step5\"]/ul/li[10]/a")
	public WebElement Brands;
	@FindBy(xpath = "/html/body/div[2]/div[1]/section[2]/div[1]/div[1]/div/button")
	public WebElement AddBrands;
	@FindBy(xpath = "//*[@id=\"name\"]")
	public WebElement BrandName;
	@FindBy(xpath = "//*[@id=\"description\"]")
	public WebElement BrandDesc;
	@FindBy(xpath = "//*[@id=\"brand_add_form\"]/div[3]/button[1]")
	public WebElement BrandSave;
	
	@FindBy(xpath = "/html/body/div[3]")
	public WebElement BrandSuccessMessage;
	
	@FindBy(xpath = "//*[@id=\"brands_table_filter\"]/label/input")
	public WebElement Brandsearch;
	
	@FindBy(xpath = "//*[@id='brands_table']/tbody/tr[1]/td[1]")
	public WebElement BrandWebtable;

	public void Branddetails() throws InterruptedException {
		Thread.sleep(3000);
		objDriversActions.click(Brands);
		Thread.sleep(5000);
	}

	public void BrandAddDetails(String brandname, String brandDesc) throws InterruptedException {
		Thread.sleep(3000);
		objDriversActions.click(AddBrands);
		Thread.sleep(3000);
		objDriversActions.sendkeys(BrandName, brandname);
		objDriversActions.sendkeys(BrandDesc, brandDesc);
		objDriversActions.click(BrandSave);
		objWait=new WaitUtility(driver);
		objWait.presenceOfElementlocated(By.xpath("//*[@id='toast-container']/div"),5);
		} 
	
	
		public String SuccessMessageCat()
		{
			objWait.presenceOfElementlocated(By.xpath("//*[@id='toast-container']/div"),5);
			
			return objDriversActions.getText(BrandSuccessMessage);

	  }
 
	public boolean searchBrandDetails(String messagebrand) throws InterruptedException {
		objDriversActions.click(Brandsearch);
		objDriversActions.sendkeys(Brandsearch, messagebrand);
		Thread.sleep(3000);
		String tablenamebrand = objDriversActions.getText(BrandWebtable);
		Thread.sleep(3000);
		if (tablenamebrand.equalsIgnoreCase(messagebrand)) {
			return true;

		} else {
			return false;
		}
	}
}