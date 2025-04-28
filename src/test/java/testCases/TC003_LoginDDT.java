package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountLoginPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;




public class TC003_LoginDDT extends BaseClass{

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="Datadriven")
	public void verify_LoginDDT(String email, String pwd, String exp) throws InterruptedException 
	{
		
		logger.info("***** Starting TC_003_LoginDDT ***** ");
		
		try {
		// HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
				
		// Login page
		AccountLoginPage lp = new AccountLoginPage(driver);
		// To get the email and password, Call property file imported in base class 
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
				
		//MyAccountPage
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetpage = macc.isMyAccountPageExists();
		
		// Validations
		
		
		
		/*
		 * Data is valid -  login Success - test pass - logout
		 * 				 - login failed - Test Failed
		 */
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetpage==true) 
			{
				macc.clickLogout();
				Assert.assertTrue(true);
				
			}
			else 
			{
				Assert.assertTrue(false);
				
			}
			
		}
		
		/*
		 * Data is invalid - Login Success - Test Failed - logout
		 * 				   - Login Failed - Test Passed
		 * */
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetpage==true) 
			{
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else 
			{
				Assert.assertTrue(true);
				
			}
		}
		
		}catch(Exception e) 
		{
			Assert.fail();
		}
		
		Thread.sleep(3000);		
		logger.info("***** Finished TC_003_LoginDDT ***** ");
		
		// run the testcase from master.xml by adding the new test tc_003
	}
	
}
