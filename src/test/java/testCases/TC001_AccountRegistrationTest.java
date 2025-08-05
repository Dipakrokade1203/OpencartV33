package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
		
	@Test(groups= {"sanity","master"})
	public void verify_accountRegistration()
	{
		logger.info("*** Starting TC001_AccountRegistrationTest ***");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link...");
		hp.clickRegister();
		logger.info("Clicked on Register Link...");
		
		AccountRegistrationPage rp=new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details...");
		rp.setFirstName(randomeString().toUpperCase());
		rp.setLastName(randomeString().toUpperCase());
		rp.setEmail(randomeString()+"@gmail.com");
		rp.setTelephone(randomeNumber());
		
		String password=randomeAlphaNumeric();
		
		rp.setPassword(password);
		rp.setConfirmPassword(password);
		rp.setPrivacyPolicy();
		rp.clickContinue();
		
		logger.info("Validating expected message...");
		String confmsg=rp.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.info("Test failed...");
			logger.debug("Debug log...");
			Assert.fail();
		}
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			logger.error("Test failed...");
			Assert.fail();
		}
		logger.info("*** Finished TC001_AccountRegistrationTest ***");
	}
	
	
}
