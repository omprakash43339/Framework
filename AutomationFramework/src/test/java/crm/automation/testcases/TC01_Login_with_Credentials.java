package crm.automation.testcases;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.automation.pages.LoginPageCrm;
import crm.automation.utility.BaseClass;
import crm.automation.utility.CommonFunction;
import crm.automation.utility.ConfigDataProvider;

public class TC01_Login_with_Credentials extends BaseClass {

	public ConfigDataProvider config;
	
	@Test
	public void loginWithCRMCrdentials() throws InterruptedException  {
		
		logger=report.createTest("Login to CRM");
		
		LoginPageCrm loginpagecrm=PageFactory.initElements(driver, LoginPageCrm.class);	
		
		logger.info("Starting Application");
		
		loginpagecrm.loginTocrm(excel.getStringData("login", 0, 0), excel.getStringData("login", 0, 1));
		
		logger.pass("Login Pass");
		
		CommonFunction.captureScreenShot(driver);
		
	
		
	}

}
