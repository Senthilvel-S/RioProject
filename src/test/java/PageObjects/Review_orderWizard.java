package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Review_orderWizard {
	
	public static WebDriver driver;
	public static JavascriptExecutor js;
	public static WebElement element1;

	public Review_orderWizard(WebDriver driver){
		this.driver = driver;
	}
	
	public void summaryPage()
	{
		
		js = (JavascriptExecutor) driver;
		//Terms & Conditions
		element1 = driver.findElement(By.xpath("//label[@for='AreTermsAndConditionsAccepted']"));	
		js.executeScript("arguments[0].scrollIntoView();", element1);
		element1.click();
		

	}
		
	
	public static WebElement submitBtn()
	{		
		//Submit
		element1 = driver.findElement(By.id("btn-Submit"));
		return element1;
	
	}
	

}
