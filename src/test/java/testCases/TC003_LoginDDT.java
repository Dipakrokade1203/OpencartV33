package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*data is valid - login success - pass - logout
data is valid - login unsuccess - fail 

data is invalid - login success - fail - logout
data is invalid - login unsuccess - pass
*/

public class TC003_LoginDDT extends BaseClass{
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class)
	public void verify_LoginDDT(String email, String pwd, String exp)
	{
		logger.info("***** Starting TC003_LoginDDT ******");
		
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setUsername(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		MyAccountPage macc=new MyAccountPage(driver);
		boolean target_page=macc.isMyAccountExist();
		
		//validations
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(target_page==true)
			{
				macc.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(target_page==true)
			{
				macc.clickLogout();
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
		
		logger.info("***** Finished TC003_LoginDDT ******");
		
	}

}
