package Setup;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


public class browserSetup {
	
	WebDriver driver;
	
		public WebDriver getDriver()
		{	
			return driver;
	
		}
	
		@Parameters({"browserType" , "appURL"})
		@BeforeTest
		public void setDriver(String browserType, String appURL) throws InterruptedException
		{
			System.out.println("Executing Browser Type");
			switch(browserType)
			{
			case "firefox" :
				driver = initializeFirefox(appURL);
			break;
			
			case "chrome" :
				driver = initializeChrome(appURL);
			break;
			
			default :
				driver = initializeFirefox(appURL);
			break;
			
			}
		}
		
		public WebDriver initializeFirefox(String appURL) throws InterruptedException
		{
			// To Launch Firefox driver
			System.out.println("Launching Firefox Driver...");
			
			WebDriver driver = new FirefoxDriver();
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			//To Load Website URL
			driver.navigate().to(appURL);		
			
			return driver;
			
		}
		public WebDriver initializeChrome(String appURL) throws InterruptedException
		{
			// To Launch Chrome driver
			System.out.println("Launching Chrome Driver...");
			
			System.setProperty("webdriver.chrome.driver", "E:\\Senthil Data\\MY Docs\\Software\\chromedriver_win32\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			
			//To Load Website URL
			System.out.println(appURL);
			driver.get(appURL);
			
			return driver;
			
		}
		



}
