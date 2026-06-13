package crm.automation.utility;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseClass {
	
	public WebDriver driver;
	public ConfigDataProvider config;
	public ExcelDataProvider excel;
	public ExtentReports report;
	public ExtentTest logger;
	
	
	@BeforeSuite
	public void setupSuit() {
	 excel=new ExcelDataProvider();
	 
	 ExtentSparkReporter extent=new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/FreeCRM"+CommonFunction.getCurrentDateTime()+".html");
	 
	 report=new ExtentReports();
	 report.attachReporter(extent);
	 
	}
	
	@Parameters("browser")
    @BeforeClass
	public void browserlaunch(String nameofbrowser)
	{
		config=new ConfigDataProvider();
		
		if(nameofbrowser.equals(config.getBrowser())) {
			driver=new ChromeDriver();
		}
		
		else if(nameofbrowser.equals(config.getBrowser())) {
			driver=new FirefoxDriver();
		}
		
		else if(nameofbrowser.equals(config.getBrowser())) {
			driver=new EdgeDriver();
		}
		else {
			System.out.println("We don't support this browser");
		}
		
		driver.get(config.getTestUrl());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	

	@AfterClass
	public void browserquit() throws InterruptedException {
		Thread.sleep(1000);
		driver.quit();
	}
	
	@AfterMethod
	public void tearDownMethod(ITestResult result) {
		
		if(result.getStatus()==ITestResult.FAILURE)
		{
			CommonFunction.captureScreenShot(driver);
		}
		if(result.getStatus()==ITestResult.SKIP)
		{
			CommonFunction.captureScreenShot(driver);
		}
		
		report.flush();
		
	}
	

}
