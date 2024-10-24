package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	
	//constructor
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	//locators
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement InkMyAccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement InkRegister;
	
	@FindBy(linkText="Login")
	WebElement linkLogin;
	
	
	//action methods
	public void clickMyAccount() 
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InkMyAccount.click();
	}
	public void clickRegister()
	{
		InkRegister.click();
	}
	public void clickLogin()
	{
		linkLogin.click();
	}
	
	
	
	

}
