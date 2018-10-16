package RioQA.DemoMavenEclipseProject;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;

public class sampleAlert {
	
	 WebDriver driver;
 
  @BeforeTest
  public void beforeTest() {
	  
	  System.setProperty("webdriver.chrome.driver", "E:\\Senthil Data\\MY Docs\\Software\\chromedriver_win32\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  
	  driver.get("http://toolsqa.com/handling-alerts-using-selenium-webdriver/");  
	  
  }
  
  @Test
  public void f() throws InterruptedException {
	  
	 //Simple Alert 
	  Thread.sleep(10000);
	 driver.findElement(By.xpath("//div[@id='content']/p[4]/button")).click();
	 
	 Alert handle = driver.switchTo().alert();
	 System.out.println(handle.getText());
	 handle.accept();
	 
	//Simple Alert with Ok & Cancel
	driver.findElement(By.xpath("//div[@id='content']/p[8]/button")).click();
	Alert handle1 = driver.switchTo().alert();
	System.out.println(handle1.getText());
	handle1.dismiss();
	String Val = driver.findElement(By.xpath("//div[@id='content']/p[8]/Span")).getText();
	System.out.println(Val);
	
	// Prompt Alert box
	driver.findElement(By.xpath("//button[@onclick='promptConfirm()']")).click();	
	Alert handle2 = driver.switchTo().alert();
	System.out.println(handle2.getText());
	handle2.sendKeys("Yes"); // This is not working in chrome
	handle1.accept();
	 
	  
  }
  

  @AfterTest
  public void afterTest() {
	  driver.close();
  }

}
