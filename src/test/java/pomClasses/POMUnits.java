package pomClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import waitUtility.WaitUtility;
import webDriverUtility.WebDriversActions;

public class POMUnits {
//	public static String message="";
	WebDriversActions objunitActions;
	public WebDriver driver;
	WaitUtility objWait;
	
	public POMUnits(WebDriver driver)
	{
		this.driver=driver;
		objunitActions = new WebDriversActions(driver);
		PageFactory.initElements(driver, this);
	}
	


		@FindBy(xpath="//*[@id=\"tour_step5\"]/ul/li[8]/a/span")
		public WebElement Units;
		@FindBy(xpath="/html/body/div[2]/div[1]/section[2]/div[1]/div[1]/div/button")
		public WebElement Add;
		@FindBy(xpath="//*[@id=\"actual_name\"]")
		public WebElement UnitName;
		@FindBy(xpath="//*[@id=\"short_name\"]")
		public WebElement ShortName;
		@FindBy(xpath="//*[@id=\"allow_decimal\"]")
		public WebElement AllowDecimal;
		@FindBy(xpath="//button[@class='btn btn-primary']")
		public WebElement Save;
		@FindBy(xpath="//*[@id='toast-container']/div")
		public WebElement SuccessMessage;
		@FindBy(xpath="//*[@id=\"unit_table_filter\"]/label/input")
		public WebElement Searchfield;
		@FindBy(xpath="//table[@id='unit_table']/tbody/tr/td[1]")
		public WebElement Webtable;
		@FindBy(xpath="//*[@id='unit_table']/tbody/tr[1]/td[4]/button[2]")
		public WebElement UnitDeletebutton;
		@FindBy(xpath="/html/body/div[4]/div/div[4]/div[2]/button")
		public WebElement OK;
		@FindBy(xpath="	//*[@id='toast-container']/div")
		public WebElement DeleteMessage;
	
public void UnitDetails() throws InterruptedException
{
	objWait=new WaitUtility(driver);
	objWait.normalWait(3000);
	objunitActions.click(Units);
	objWait.normalWait(3000);

}
   public void UnitAddDetails(String name,String shortname) throws InterruptedException {	
	objunitActions.click(Add);
    objWait.normalWait(3000);
    objunitActions.sendkeys(UnitName,name);
    objunitActions.sendkeys(ShortName,shortname );
    objunitActions.DropdownselectByIndex(AllowDecimal,1);
    objunitActions.click(Save);
    objWait.presenceOfElementlocated(By.xpath("//*[@id='toast-container']/div"),5);
  }
   
   public String successmessage()
	{
		objWait.presenceOfElementlocated(By.xpath("//*[@id='toast-container']/div"),5);
		
		return objunitActions.getText(SuccessMessage);

  }
      public boolean UnitSearchDetails(String message) throws InterruptedException {	
	  objunitActions.click(Searchfield);
	  objunitActions.sendkeys(Searchfield,message);
	  Thread.sleep(3000);
	   
      String tablename=objunitActions.getText(Webtable);
      Thread.sleep(3000);
      
      if(tablename.equalsIgnoreCase(message))
{
	  return true;
	
}
      else
{
     	return false;
}
}
     public void deleteunitDetails() throws InterruptedException {	
	  objunitActions.click(UnitDeletebutton);
	   Thread.sleep(3000);
	  objunitActions.click(OK);
	  objWait.presenceOfElementlocated(By.xpath("//*[@id='toast-container']/div"),5);
	 
}
public String DeleteMessage() {

String messagedeletem=objunitActions.getText(DeleteMessage);
System.out.println("Deletemessage="+messagedeletem);

return messagedeletem;

}
}