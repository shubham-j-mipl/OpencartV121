package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;
	

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups={"Regression", "Master"})
	public void verify_account_registration() 
	{
		
		logger.info("**** Starting TC001_AccountRegistrationTest ****** ");
		
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info(" Clicked in My Account Link ");
		
		hp.clickRegister();
		logger.info("Clicked in Register Link ");
		
		AccountRegistrationPage regPage= new AccountRegistrationPage(driver);
		
		logger.info("Providing Customer Details.....");
		regPage.setFirstname(randomeString().toUpperCase());
		regPage.setLastName(randomeString().toLowerCase());
		
		regPage.setEmail(randomeString()+"@gmail.com");		// randomly generated the email
		
		regPage.setTelephone(randomeNumber());
		
		
		String password = randomeAlphaNumeric();
		
		
		regPage.setPassword(password);
		regPage.setConfirmPassword(password);
		
		
		regPage.setPrivacyPolicy();
		regPage.clickContinue();
		
		
		logger.info("Validating Expected Message....");
		String conf_msg= regPage.getConfirmationMsg();
		if(conf_msg.equals("Your Account Has Been Created!")) 
		{
			Assert.assertTrue(true);
		}
		else 
		{
			logger.error("... Test Failed ...");
			logger.debug("... Debug Logs ...");
			Assert.assertTrue(false);
		}
		
		
		//Assert.assertEquals(conf_msg, "Your Account Has Been Created!");
		}
		catch(Exception e) 
		{
			
			Assert.fail();
		}
		
		logger.info(" **** TC001_AccountRegistrationTest Finished ****** ");
		
		
	}
	
	
}
