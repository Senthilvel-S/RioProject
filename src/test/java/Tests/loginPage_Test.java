package Tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import PageObjects.loginPage;
import Setup.browserSetup;

import org.testng.annotations.BeforeTest;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.remote.RemoteWebDriver;


public class loginPage_Test extends browserSetup {

	WebDriver driver;
	ExtentHtmlReporter reporter;
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeTest
	public void setUp()
	{
			driver = getDriver();		
			
			Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
			String browserName = cap.getBrowserName();
			String browserVersion = cap.getVersion();
			String platformName = cap.getPlatform().toString();
			
			
			// Initialize the HtmlReporter 
			// 'ExtentHtmlReporter' creates a rich standalone HTML file
			reporter = new ExtentHtmlReporter("C:\\Users\\senthilvel.s\\eclipse-workspace\\DemoMavenEclipseProject\\test-output\\ExtentReport.html");
			
			 //initialize ExtentReports and attach the HtmlReporter
			extent = new ExtentReports();
			extent.attachReporter(reporter);
	
			//To add system or environment info by using the setSystemInfo method.
			extent.setSystemInfo("Environment", "Sl3 Staging");
			extent.setSystemInfo("Browser", browserName);
			extent.setSystemInfo("Browser Version", browserVersion);
			extent.setSystemInfo("platform Name", platformName);
			extent.setSystemInfo("User Name", "senthildoctor");
	}
	
	
	//To check Invalid UN & Valid PWD
	@Test (priority=0)
	public void invalidUN()  {
		
		test = extent.createTest("Invalid Username & Valid Password");
			
		loginPage.UserName(driver).sendKeys("senthill");
		test.log(Status.INFO, "Provided Invalid Username");
		
		loginPage.passWord(driver).sendKeys("Password@1234");
		test.log(Status.INFO, "Provided Valid Password");
		
		loginPage.signIn(driver).click();
		test.log(Status.INFO, "Clicked Sign In");
		
		String Expected = "Your Oral Care ID or Password is incorrect.";
		
		Assert.assertEquals(loginPage.loginError(driver).getText(), Expected);
	
	}
	
/*	//To check valid UN & Invalid PWD
	@Test (priority=1)
	public void invalidPWD()  {
		
		loginPage.UserName(driver).clear();
		loginPage.UserName(driver).sendKeys("senthilr5.555");
		
		loginPage.passWord(driver).clear();
		loginPage.passWord(driver).sendKeys("Password@1");
		
		loginPage.signIn(driver).click();
		
		Assert.assertEquals("Your Oral Care ID or Password is incorrect.", loginPage.loginError(driver).getText());			
	}
	
	//To check Invalid UN & Invalid PWD
		@Test (priority=2)
		public void invalidUN_PWD()  {
			
			loginPage.UserName(driver).clear();
			loginPage.UserName(driver).sendKeys("senthilr");
			
			loginPage.passWord(driver).clear();
			loginPage.passWord(driver).sendKeys("Password@1");
			
			loginPage.signIn(driver).click();
			
			Assert.assertEquals("Your Oral Care ID or Password is incorrect.", loginPage.loginError(driver).getText());			
		}*/
		
	
		 @AfterMethod
		    public void getResult(ITestResult result) {
		        if(result.getStatus() == ITestResult.FAILURE) {
		        	test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
		        	test.fail(result.getThrowable());
		        }
		        else if(result.getStatus() == ITestResult.SUCCESS) {
		        	test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
		        }
		        else {
		        	test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
		        	test.skip(result.getThrowable());
		        }
		    }	
	
	
	@AfterTest
	public void closeBrowser() throws IOException, URISyntaxException
	{
		//to write or update test information to reporter
        extent.flush();
		driver.close();
		
		// To launch the report in default browser
		if (Desktop.isDesktopSupported()) {
		    Desktop.getDesktop().browse(new URI("file:///C:/Users/senthilvel.s/eclipse-workspace/DemoMavenEclipseProject/test-output/ExtentReport.html#"));
		}

	}
	
}
