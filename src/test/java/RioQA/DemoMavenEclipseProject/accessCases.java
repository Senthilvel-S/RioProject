package RioQA.DemoMavenEclipseProject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjects.loginPage;

public class accessCases {

	 WebDriver driver;
	 WebDriverWait wait;
	 WebElement ele1, ele2;
	 
	  @BeforeTest
	  public void beforeTest() {
		  
		System.setProperty("webdriver.chrome.driver", "E:\\Senthil Data\\MY Docs\\Software\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  
		  
		driver.get("https://3mocc-sl3-staging.3m.com");  
		  
		loginPage.UserName(driver).clear();
		loginPage.UserName(driver).sendKeys("senthilr5.5");
		
		loginPage.passWord(driver).clear();
		loginPage.passWord(driver).sendKeys("Password@12345");
		
		loginPage.signIn(driver).click();
  
		//To Click 'Cases' link
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='main-cases' and @href='/Cases']"))).click();
		loginPage.spinnerWait(driver);
	  }
	  
	  @Test
	  public void f() throws InterruptedException {
		  
		 //To click dropdown in 'Cases' column
		  driver.findElement(By.xpath("//th[@data-field='CaseNumber']/a[1]")).click();
	  
		  wait = new WebDriverWait(driver, 60);
		 
		Thread.sleep(2000);
		ele1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(.,'Filter')]")));
		 
		  Actions act = new Actions(driver);
		  act.moveToElement(ele1).build().perform();
		  
		  //To enter value in 'Filter' textbox field
		  ele2 = driver.findElement(By.xpath("//form[@class='k-filter-menu']/div/input[@title='Value']"));
		  
		  ele2.sendKeys("TKX2FD");
		  
		//Click Filter button 
		  driver.findElement(By.xpath("//button[@type='submit' and text()='Filter']")).click();		 
		  
	  }
	  

	  @AfterTest
	  public void afterTest() {
		//  driver.close();
	  }
	
}
