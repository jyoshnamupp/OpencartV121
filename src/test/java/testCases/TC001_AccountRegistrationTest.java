package testCases;


import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() throws InterruptedException
	{
		
		logger.info("started testcase");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("****Clicked on myaccount link*******");
		hp.clickRegister();
		logger.info("****Clicked on register link *******");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		logger.info("****Providing customer info*******");
		regpage.setFirstname(randomeString().toUpperCase());
		regpage.setLastname(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");
		regpage.setTelephone(randomeNumber());
		
		String password=randomeAlphaNumeric();
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivatePolicy();
		
		regpage.clickContinue();
		
		logger.info("****Validating expected message*******");
		String confmsg=regpage.getConfirmationmsg();
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed");
			logger.debug("Debug logs");
			Assert.assertTrue(false);
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		
		
		
		logger.info("****TC001_AccountRegistrationTest Finished *******");
		
	}
}
	
	 

