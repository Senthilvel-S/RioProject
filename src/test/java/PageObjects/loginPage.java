package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class loginPage {
	
	static WebElement element;

	 public static WebElement UserName(WebDriver driver){
		 
         element = driver.findElement(By.xpath("//input[@id='Username' and @name='Username']")); 
         return element;
         
     }

	public static WebElement passWord(WebDriver driver) {

		 element = driver.findElement(By.xpath("//input[@id='Password' and @name='Password']")); 
         return element;
		
	}
	
	public static WebElement signIn(WebDriver driver) {

		 element = driver.findElement(By.xpath("//button[@id='button-submit' and @type='submit']")); 
         return element;
		
	}
	 
	public static WebElement loginError(WebDriver driver) {

		element = driver.findElement(By.xpath("//div[@class='col-sm-12']/p[2]")); 
        return element;
		
	}
	
	public static void spinnerWait(WebDriver driver) {
		
		do
		{
			By spinner = By.xpath("//div[@id='loader']");
			if(driver.findElement(spinner).getAttribute("style").toString().contains("none")){
				break;
		}
		}while(true);
	}
	
	public static void internalUser(WebDriver driver)
	{
		//To click 'Sign in with 3M'
		driver.findElement(By.linkText("Sign in with 3M")).click();
		//UserName
		driver.findElement(By.id("userName")).sendKeys("a6t2tzz");
		//Password
		driver.findElement(By.id("passwd")).sendKeys("Senthil@1234");
		//Click Login
		driver.findElement(By.xpath("//input[@type='submit' and @name='login']")).click();
		
	}

}
