package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"sanity","Master"})
	public void verify_login()
	{
		logger.info("****Starting TC002_LoginTest****");
	
	try
	{
	HomePage hp=new HomePage(driver);
	hp.clickMyAccount();
	hp.clickLogin();
	
	LoginPage lp=new LoginPage(driver);
	lp.setEmail(p.getProperty("email"));
	lp.setPassword(p.getProperty("password"));
	lp.clickLogin();
	
	MyAccountPage ap = new MyAccountPage(driver);
	boolean accmess=ap.isMyAccountExsists();
	
	Assert.assertEquals(accmess, true,"Login FAILED");
	//Assert.assertTrue(accmess);
	
	logger.info("***TC002_LoginTest finished***");
	
	}
	catch(Exception e)
	{
		Assert.fail();
	}
}
}
