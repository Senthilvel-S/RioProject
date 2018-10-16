package PageObjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import Setup.commonClass;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Product_orderWizard {
	
	static WebDriver driver;
	WebElement element1, element2, element3, element4, element5, element6, element7;
	List<WebElement> delement;
	WebDriverWait wait;
	By WithExpedite, WithoutExpedite, retainer_option;
	JavascriptExecutor js;
	
	public Product_orderWizard(WebDriver driver) {
		
		this.driver = driver;		
	}
	
	
	
	public void selectProduct(String arches)
	{		
		wait = new WebDriverWait(driver, 60);
		js = (JavascriptExecutor) driver;
		
		//To choose product
		delement = driver.findElements(By.xpath("//div[@class='col-sm-10']/div/label"));		
		//To choose Opposing arch chkbox
		element1 = driver.findElement(By.xpath("//label[@for='IsOpposingArchSelected']"));			
		// With Expedite
		element2 = driver.findElement(By.xpath("//label[@for='IsExpedited_true']"));		
		// Without Expedite
		element3 = driver.findElement(By.xpath("//label[@for='IsExpedited_false']"));	
		//Retainer fulfillment option
		retainer_option = By.xpath("//input[@name='RetentionSetQuantity']");
		
		if(arches.equalsIgnoreCase("Only Upper Aligner"))
		{
			delement.get(0).click();
		}
		if(arches.equalsIgnoreCase("Only lower Aligner"))
		{
			delement.get(2).click();
		}
		if(arches.equalsIgnoreCase("Only Upper Retainer"))
		{
			delement.get(1).click();
		}
		if(arches.equalsIgnoreCase("Only lower Retainer"))
		{
			delement.get(3).click();
		}
		if(arches.equalsIgnoreCase("Lower Aligner with Opposing Arch"))
		{
			delement.get(2).click();
			element1.click();
			
		}
		if(arches.equalsIgnoreCase("Upper Aligner with Opposing Arch"))
		{
			delement.get(0).click();
			element1.click();
		}
		if(arches.equalsIgnoreCase("Both Arches"))
		{
			delement.get(0).click();
			delement.get(1).click();
			delement.get(2).click();
			delement.get(3).click();
		}
		if(arches.equalsIgnoreCase("Both Aligner"))
		{
			delement.get(0).click();
			delement.get(2).click();
		}
		if(arches.equalsIgnoreCase("Both Retainer"))
		{

			delement.get(1).click();
			delement.get(3).click();
		}		
	}
	
	public void shippingInfo(String arches, String retainer_fulfilloption, String shippingInfo) throws EncryptedDocumentException, IOException, InterruptedException
	{
		Select list1, list2;
		
		if(arches.contains("Aligner"))
		{
			element4 = driver.findElement(By.xpath("//select[@id='ShippingAddressProducts_0__FacilityId']"));
			list1 = new Select(element4);
			String firstOption1 = list1.getFirstSelectedOption().getText();
			System.out.println(firstOption1);
			if(firstOption1.equalsIgnoreCase("Select"))			
			{
				list1.selectByIndex(1);
				loginPage.spinnerWait(driver);
			}
			
		}
		else if(arches.contains("Retainer") || arches.contains("Both Arches"))
		{			
			element5 = driver.findElement(By.xpath("//select[@id='ShippingAddressProducts_1__FacilityId']"));
			list2 = new Select(element5);
			String firstOption2 = list2.getFirstSelectedOption().getText();
			System.out.println(firstOption2);
			
			if(shippingInfo.contains("AlternativeShippingAddress"))
			{
				list2.selectByIndex(1);
				
				String[][] b = commonClass.readExcel("C:\\Users\\senthilvel.s\\eclipse-workspace\\DemoMavenEclipseProject\\testData\\shippingInfo.xlsx", "ShippingInfo");
				
				for(int i=0; i<b.length; i++)
				{
					for(int j=0; j<b[0].length; j++)
					{
						if(b[i][j].contains("FirstName"))
						{
							driver.findElement(By.id("ShippingAddressProducts_1__FirstName")).sendKeys(b[i][j+1]);
							break;
						}
						if(b[i][j].contains("LastName"))
						{
							driver.findElement(By.id("ShippingAddressProducts_1__LastName")).sendKeys(b[i][j+1]);
							break;
						}
						if(b[i][j].contains("Address1"))
						{
							driver.findElement(By.id("ShippingAddressProducts_1__Address1")).sendKeys(b[i][j+1]);
							break;
						}
						if(b[i][j].contains("Address2"))
						{
							driver.findElement(By.id("ShippingAddressProducts_1__Address2")).sendKeys(b[i][j+1]);
							break;
						}
						if(b[i][j].contains("City"))
						{
							driver.findElement(By.id("ShippingAddressProducts_1__City")).sendKeys(b[i][j+1]);
							break;
						}
						if(b[i][j].contains("Zipcode"))
						{
							driver.findElement(By.id("ShippingAddressProducts_1__PostalCode")).sendKeys(b[i][j+1]);
							break;
						}
						if(b[i][j].contains("Phone"))
						{
							driver.findElement(By.id("ShippingAddressProducts_1__Phone")).sendKeys(b[i][j+1]);
							break;
						}
						if(b[i][j].contains("Country"))
						{
							element6 = driver.findElement(By.id("ShippingAddressProducts_1__Country"));
							Select country = new Select(element6);
							country.selectByVisibleText(b[i][j+1]);
							break;
						}
						if(b[i][j].contains("State"))
						{
							element7 = driver.findElement(By.id("ShippingAddressProducts_1__State"));
							Select state = new Select(element7);
							state.selectByVisibleText(b[i][j+1]);
							break;
						}
					}
				}												
			}
			else if(firstOption2.equalsIgnoreCase("Select"))			
					{
						list2.selectByIndex(2);
						loginPage.spinnerWait(driver);
					}
			
			//To fill value in Retainer fulfillment option field
			wait.until(ExpectedConditions.visibilityOfElementLocated(retainer_option)).sendKeys(retainer_fulfilloption);					
		}					
		
	}
	
	public void selectExpedite(String expedite)
	{
		//To choose Expedite Value
		if(expedite.equalsIgnoreCase("Yes"))
		{
			//To Scroll & View the 'Expedite' element 
			js.executeScript("arguments[0].scrollIntoView();", element2);
			wait.until(ExpectedConditions.visibilityOf(element2)).click();
		} 
		else if(expedite.equalsIgnoreCase("No"))
		{
			//To Scroll & View the 'Expedite' element 
			js.executeScript("arguments[0].scrollIntoView();", element3);			
			wait.until(ExpectedConditions.visibilityOf(element3)).click();
		}				
	}
		
}
