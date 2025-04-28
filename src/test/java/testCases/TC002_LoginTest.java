package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountLoginPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"Sanity","Master"})
	public void verify_login()
	{
		logger.info("**** Starting Test Case TC002_LoginTest *****");
		
		try {
		// HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		// Login page
		AccountLoginPage lp = new AccountLoginPage(driver);
		// To get the email and password, Call property file imported in base class 
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//MyAccountPage
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetpage = macc.isMyAccountPageExists();
		
		
		Assert.assertTrue(targetpage); //Assert.assertEquals(targetpage, true, "Login Failed");
		}
		catch(Exception e ) 
		{
			Assert.fail();
		}
		
		
		
		logger.info("***** Finished TC002_LoginTest *****");
		
		// Run this test case through xml file because base class depends on master.xml for passing the browser.
		
	}

}
