package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven")
	public void verify_login(String email,String pwd,String exp)
	{
		logger.info("****Starting TC003_LoginDDT****");
	try
	{
	HomePage hp=new HomePage(driver);
	hp.clickMyAccount();
	hp.clickLogin();
	
	LoginPage lp=new LoginPage(driver);
	lp.setEmail(email);
	lp.setPassword(pwd);
	lp.clickLogin();
	
	MyAccountPage ap = new MyAccountPage(driver);
	boolean accmess=ap.isMyAccountExsists();
	
	//DATA VALID-----LOGIN SUCCESS---TESTCASE PASS---LOGOUT
	                 //LOGIN FAIL------TC FAIL
	//DATA INVALID----LOGIN SUCCESS----TESTCASE PASS---LOGOUT
	//LOGIN FAIL---TESTCASE pass
	
	if(exp.equalsIgnoreCase("Valid"))
	{
		if(accmess==true)
		{
			ap.clickLogout();
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
	}
	if(exp.equalsIgnoreCase("Invalid"))
	{
		if(accmess==true)
		{
			ap.clickLogout();
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}
	}
	catch(Exception e)
	{
		Assert.fail();
	}
	logger.info("***TC003_LoginDDT FINISHED****");

}
}
