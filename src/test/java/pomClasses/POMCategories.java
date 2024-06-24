package pomClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import waitUtility.WaitUtility;
import webDriverUtility.WebDriversActions;

public class POMCategories 
{
	WebDriversActions objDriversActions;
	WaitUtility objWait;
	
	WebDriver driver;
		
public POMCategories(WebDriver driver)
	{
		this.driver=driver;
		objDriversActions = new WebDriversActions(driver);
		PageFactory.initElements(driver, this);
	}
		@FindBy(xpath="//*[@id='tour_step5']/ul/li[9]/a/span")
		public WebElement Categories;
		@FindBy(xpath="/html/body/div[2]/div[1]/section[2]/div[1]/div[1]/div/button")
		public WebElement CategoryAdd;
		@FindBy(xpath="//*[@id='name']")
		public WebElement CategoryName;
		@FindBy(xpath="//*[@id='short_code']")
		public WebElement CategoryCode;
		@FindBy(xpath="//*[@id='category_add_form']/div[3]/button[1]")
		public WebElement CategorySave;
		@FindBy(xpath="//*[@id='toast-container']/div")
		public WebElement CategorySuccessMessage;
		@FindBy(xpath="//*[@id='category_table_filter']/label/input")
		public WebElement CategorySearch;
	
		@FindBy(xpath="//*[@id='category_table']/tbody/tr[1]/td[1]")
		public WebElement CategoryWebtable;
		
		@FindBy(xpath="//*[@id='category_table']/tbody/tr[1]/td[3]/button[2]")
        public WebElement Delete;
		
		@FindBy(xpath="/html/body/div[4]/div/div[4]/div[2]/button")
		public WebElement OKbutton;
		
		@FindBy(xpath="//*[@id='toast-container']/div")
		public WebElement Deletemessagecat;
		 


public void Categorydetails() throws InterruptedException {
	Thread.sleep(3000);
	objDriversActions.click(Categories);
	Thread.sleep(5000);
}
	public void CategoryAdddetails(String catname,String catcode) throws InterruptedException {
		Thread.sleep(3000);
		objDriversActions.click(CategoryAdd);
	Thread.sleep(3000);
	objDriversActions.sendkeys(CategoryName, catname);
	objDriversActions.sendkeys(CategoryCode, catcode);
	objDriversActions.click(CategorySave);
	objWait=new WaitUtility(driver);
	objWait.presenceOfElementlocated(By.xpath("//*[@id='toast-container']/div"),5);
	} 
	
	
	public String SuccessMessageCat()
	{
		objWait.presenceOfElementlocated(By.xpath("//*[@id='toast-container']/div"),5);
		
		return objDriversActions.getText(CategorySuccessMessage);

  }
	
	
	public boolean searchcategoriesDetails(String categorymessage) throws InterruptedException {	
		objDriversActions.click(CategorySearch);
		objDriversActions.sendkeys(CategorySearch,categorymessage);
		Thread.sleep(3000);
		String tablenamecat=objDriversActions.getText(CategoryWebtable);
		Thread.sleep(3000);
		if(tablenamecat.equalsIgnoreCase(categorymessage))
		{
			return true;
			
		}
		else
		{
			return false;
		}

		}
	public void deletecategoriesDetails() throws InterruptedException {	
		objDriversActions.click(Delete);
		Thread.sleep(3000);
		objDriversActions.click(OKbutton);
		objWait.presenceOfElementlocated(By.xpath("//*[@id='toast-container']/div"),5);
		
	}
	public String DeleteDialogboxcat() {

	String messagedeletem=objDriversActions.getText(Deletemessagecat);
	System.out.println("Deletemessage="+messagedeletem);

	return messagedeletem;

	}
	}