package PageObjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Records_orderWizard {
	
	public static WebDriver driver;
	boolean val1;
	public static WebElement element1, element2;
	WebDriverWait wait;
	public static JavascriptExecutor js;
	Properties p1, p2, p3;
	FileInputStream fis1, fis2, fis3;
	
	public Records_orderWizard(WebDriver driver){
		this.driver = driver;
	}
	
//	public void dentalstatus() throws IOException {
//		System.out.println("Dental Status");
//		
//		p1 = new Properties();			
//		fis1  = new FileInputStream("C:\\Users\\senthilvel.s\\eclipse-workspace\\DemoMavenEclipseProject\\testData\\dentalStatus.properties");
//		p1.load(fis1);
//		
//		Set<String> keys = p1.stringPropertyNames();
//		for (String key : keys) {
//			
//		      if(p1.get(key).equals("No Additional Status to report")) {
//		    	  driver.findElement(By.xpath("//label[@for='NoStatusToReport']")).click();
//		      }
//		      else {
//			
//		      if(p1.get(key).equals("Missing - No Pontic")) {
//		    	  driver.findElement(By.id(key)).click();
//		    	  driver.findElement(By.xpath("//label[@for='chkDentalToothStatusMissing']")).click();
//		    	  driver.findElement(By.id(key)).click();
//		      }
//		      if(p1.get(key).equals("Missing - Display Pontic")) {
//		    	  driver.findElement(By.id(key)).click();
//		    	  driver.findElement(By.xpath("//label[@for='chkDentalToothStatusMissingPontic']")).click();
//		    	  driver.findElement(By.id(key)).click();
//		      }
//		      if(p1.get(key).equals("To be Extracted")) {
//		    	  driver.findElement(By.id(key)).click();
//		    	  driver.findElement(By.xpath("//label[@for='chkDentalToothStatusExtract']")).click();
//		    	  driver.findElement(By.id(key)).click();
//		      }
//		      if(p1.get(key).equals("Primary")) {
//		    	  driver.findElement(By.id(key)).click();
//		    	  driver.findElement(By.xpath("//label[@for='chkDentalToothStatusPrimary']")).click();
//		    	  driver.findElement(By.id(key)).click();
//		      }
//		      if(p1.get(key).equals("Fixed")) {
//		    	  driver.findElement(By.id(key)).click();
//		    	  driver.findElement(By.xpath("//label[@for='chkDentalToothStatusFixed']")).click();
//		    	  driver.findElement(By.id(key)).click();
//		      }
//		      if(p1.get(key).equals("Implant")) {
//		    	  driver.findElement(By.id(key)).click();
//		    	  driver.findElement(By.xpath("//label[@for='chkDentalToothStatusImplant']")).click();
//		    	  driver.findElement(By.id(key)).click();
//		      }
//		      if(p1.get(key).equals("Mesial")) {
//		    	  driver.findElement(By.id(key)).click();
//		    	  driver.findElement(By.xpath("//label[@for='chkDentalToothStatusMesial']")).click();
//		    	  driver.findElement(By.id(key)).click();
//		      }
//		      if(p1.get(key).equals("Distal")) {
//		    	  driver.findElement(By.id(key)).click();
//		    	  driver.findElement(By.xpath("//label[@for='chkDentalToothStatusDistal']")).click();
//		    	  driver.findElement(By.id(key)).click();
//		      }
//		     }
//		   }		
//	}	
	
	//Alternative method for Dental Status
	public void dentalstatus1() throws IOException {
		System.out.println("Dental Status");
		
		p1 = new Properties();
		p3 = new Properties();
		fis1  = new FileInputStream("C:\\Users\\senthilvel.s\\eclipse-workspace\\DemoMavenEclipseProject\\testData\\dentalStatus.properties");
		p1.load(fis1);
		
		fis3  = new FileInputStream("C:\\Users\\senthilvel.s\\eclipse-workspace\\DemoMavenEclipseProject\\testData\\dentalStatusXpath.properties");
		p3.load(fis3);
		
		Set<String> keys1 = p1.stringPropertyNames();
		Set<String> keys2 = p3.stringPropertyNames();
		for (String key1 : keys1) 
		{
			for (String key2 : keys2)
			{
		      if(p1.get(key1).equals(key2) && key2.equals("NoAdditionalStatusToReport")) 
		      {
		    	  System.out.println("Dental Status is: "+key2);
		    	  driver.findElement(By.xpath(p3.getProperty(key2))).click();
		    	  break;
		      }
		      else 
		      {
			    if(p1.get(key1).equals(key2)) 
			    {
			      System.out.println(key1+" tooth Status is: "+p1.get(key1));	
		    	  driver.findElement(By.id(key1)).click();
		    	  driver.findElement(By.xpath(p3.getProperty(key2))).click();
		    	  driver.findElement(By.id(key1)).click();
		         }
		       }
		    }
	    }
	}
	
	public void intraoralRecords(String impression, String impressionDate)
	{	
		if(impression.equalsIgnoreCase("Scan"))
		{
			// Scan type
			driver.findElement(By.xpath("//label[@for='ImpressionType_scanRadio']")).click();
		}
		else if(impression.equalsIgnoreCase("Physical_Impression"))
		{
			// Physical Impression
			driver.findElement(By.xpath("//label[@for='ImpressionType_pvsRadio']")).click();
			//Impression Date
			driver.findElement(By.id("ImpressionDate")).sendKeys(impressionDate);
			driver.findElement(By.id("ImpressionDate")).sendKeys(Keys.TAB); // To tab out from the Impression Date field
		}
	}
	
	public void uploadRecords() throws IOException, InterruptedException
	{
		List<WebElement> editIcon1;
		int i=0;
		p2 = new Properties();	
		fis2  = new FileInputStream("C:\\Users\\senthilvel.s\\eclipse-workspace\\DemoMavenEclipseProject\\testData\\records.properties");
		wait = new WebDriverWait(driver, 180);
		p2.load(fis2);
		js = (JavascriptExecutor) driver;
		By edit = By.xpath("//span[@class='edit-action toolbar-button active']");	
		
		element2 = driver.findElement(By.id("file-upload-button"));	
		
		
		editIcon1 = driver.findElements(edit); // Returns an empty list if there are no elements matching the locator Strategy
		for(int k=0; k<editIcon1.size(); k++)
		{
			System.out.println("Finds Existing Edit Icon");
			i++;
		}

		Set<String> keys1 = p2.stringPropertyNames();
		for (String key : keys1) {
				System.out.println(key);
				System.out.println(p2.getProperty(key));
				element2.sendKeys(p2.getProperty(key));												
				for(int j=0; j<2; j++)
				{
					try
					{
						wait.until(ExpectedConditions.visibilityOfElementLocated(edit));
						editIcon1 = driver.findElements(edit);
						wait.until(ExpectedConditions.visibilityOf(editIcon1.get(i)));
						js.executeScript("arguments[0].scrollIntoView();", editIcon1.get(i));
						System.out.println(j);
					}
					catch(Exception E)
					{
						System.out.println("Not able to find Edit Icon.Try again "+E.getMessage());
						Thread.sleep(20000);
					}
				}
				i++;
		}
	}
	
}