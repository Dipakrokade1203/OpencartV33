package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups= {"regression","master"})
	public void verify_login()
	{
		logger.info("**** Starting execution TC002_LoginTest *****");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setUsername(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		MyAccountPage macc=new MyAccountPage(driver);
		boolean target_page=macc.isMyAccountExist();
		
		Assert.assertTrue(target_page);
		//Assert.assertEquals(target_page, true,"login failed...");
		}
		catch(Exception e)
		{
			logger.error("Login test failed...");
			Assert.fail();
		}
		
		logger.info("**** Finished execution TC002_LoginTest *****");
	}

}
