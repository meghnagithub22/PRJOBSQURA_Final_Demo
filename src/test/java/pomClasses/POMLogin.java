package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import waitUtility.WaitUtility;
import webDriverUtility.WebDriversActions;

public class POMLogin 
{
	WebDriversActions objDriverActions;
	WebDriver driver;
	WaitUtility objWait;
	public POMLogin(WebDriver driver)
	
	{
		this.driver=driver;
		objDriverActions = new WebDriversActions(driver);
		PageFactory.initElements(driver, this);
	}
	@FindBy (xpath="//input[@id='username']")
	public WebElement userName;

	@FindBy (xpath="//input[@id='password']")
	public WebElement passwrd;

	@FindBy (xpath="//button[@type='submit']")
	public WebElement LoginBtn;
	
	@FindBy (xpath="//*[@id=\"step-0\"]/div[3]/button[3]")
    public WebElement Endtourbtn	;
	
	@FindBy (xpath="//*[@id=\"tour_step5_menu\"]/span[1]")
	public WebElement ProductBtn;
	
	@FindBy(xpath="/html/body/div[2]/header/nav/div/ul/li[2]/a")
	public WebElement profile;
	
	@FindBy (xpath="//a[text()='Sign Out']")
	public WebElement signOut;


public void login(String username, String password) throws InterruptedException {

	objDriverActions.sendkeys(userName,username);
	objDriverActions.sendkeys(passwrd, password);
	objDriverActions.click(LoginBtn);
	
	try {
		
		if(Endtourbtn.isDisplayed())
			{
			WebDriversActions objDriverActions	=new WebDriversActions(driver);	

		objDriverActions.click(Endtourbtn);
		}
		}
		catch(Exception e)
		{
			System.out.println("handle exception");
		}
}
		
	public void Clickproduct() throws InterruptedException
	{

		objDriverActions.click(ProductBtn);
		objWait=new WaitUtility(driver);
		objWait.normalWait(2000);

	}
	
	
	public void signout() throws InterruptedException
	{
		objDriverActions.click(profile);
		objWait=new WaitUtility(driver);
		objWait.normalWait(4000);
		objDriverActions.JavascriptClick(signOut);
		objWait.normalWait(8000);
	}

}





