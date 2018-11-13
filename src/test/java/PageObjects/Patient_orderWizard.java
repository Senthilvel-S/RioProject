package PageObjects;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Setup.commonClass;

public class Patient_orderWizard {
	
	public static WebDriver driver;
	boolean val1, Acc;
	public static WebElement element;
	WebDriverWait wait;
	public static JavascriptExecutor js;

	public Patient_orderWizard(WebDriver driver){
		this.driver = driver;
	}
	
	//To login as External user
	public void externalLogin() throws InterruptedException
	{
		loginPage.UserName(driver).clear();
		loginPage.UserName(driver).sendKeys("senthilr5.5");
		
		loginPage.passWord(driver).clear();
		loginPage.passWord(driver).sendKeys("Password@12345");
		
		loginPage.signIn(driver).click();
		
		//To Click 'New Order' button
		loginPage.spinnerWait(driver);
		driver.findElement(By.xpath("//button[@class='btn btn-primary' and text()='New Order']")).click();
		
	}
	
	//To login as Internal user
	public void internalLogin() 
	{
		wait = new WebDriverWait(driver, 60);
		loginPage.internalUser(driver);
		
		//To Click 'Cases' link
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='main-cases' and @href='/Cases']"))).click();		
		//To Click 'New Order' button
		loginPage.spinnerWait(driver);
		driver.findElement(By.xpath("//button[@class='btn btn-primary' and text()='New Order']")).click();
	}	
	
	public void newPatientFields() throws InterruptedException, EncryptedDocumentException, IOException
	{
		WebElement element1, element2;
		Select acc;
		js = (JavascriptExecutor) driver;
		
		// To click 'New Patient'
		driver.findElement(By.xpath("//label[@for='PatientSelect_new']")).click();
				//loginPage.spinnerWait(driver);
		
		
		String[][] b = commonClass.readExcel("C:\\Users\\senthilvel.s\\eclipse-workspace\\DemoMavenEclipseProject\\testData\\shippingInfo.xlsx", "New Patient");
		
		for(int i=0; i<b.length; i++)
		{
			for(int j=0; j<b[0].length; j++)
			{
				if(b[i][j].contains("FirstName"))
				{
					//First Name
					driver.findElement(By.id("PatientFirstname")).sendKeys(b[i][j+1]);
					break;
				}
				if(b[i][j].contains("LastName"))
				{
					//Last Name
					driver.findElement(By.id("Lastname")).sendKeys(b[i][j+1]);
					break;
				}
				if(b[i][j].contains("MiddleName"))
				{
					//Middle Name
					driver.findElement(By.id("Middlename")).sendKeys(b[i][j+1]);
					break;
				}
				if(b[i][j].contains("DOB"))
				{
					//DOB
					driver.findElement(By.id("Dob")).sendKeys(b[i][j+1]);
					break;
				}
				if(b[i][j].contains("Sex"))
				{
					element1 = driver.findElement(By.id("Gender"));
					Select sex = new Select(element1);
					sex.selectByVisibleText(b[i][j+1]);
					break;
				}
				if(b[i][j].contains("PMI"))
				{
					//PMI
					driver.findElement(By.id("PracticePatientId")).sendKeys(b[i][j+1]);
					break;
				}
				if(b[i][j].contains("Account"))
				{
					element2 = driver.findElement(By.id("AccountId"));
					//To Scroll & View the 'Account' element 
					js.executeScript("arguments[0].scrollIntoView();", element2);										
					acc = new Select(element2);
					//To get all the options present in the dropdown
					List<WebElement> allOptions = acc.getOptions();
					for (WebElement acclist : allOptions)
					{
						if(acclist.getText().contains(b[i][j+1]))
						{
							Acc=true;
							System.out.println("Account name Exists: "+acclist.getText());
						}
					}										
					if(Acc)
					{
							acc.selectByVisibleText(b[i][j+1]);
							loginPage.spinnerWait(driver);
							break;
					}
					else 
					{
						acc.selectByIndex(1);
						loginPage.spinnerWait(driver);	
						break;
					}
				}
				if(b[i][j].contains("Facilities"))
				{
					String[] val=b[i][j+1].split(";");
					
					for(int l=0; l<val.length; l++) // For Multiple facilities to be selected
					{
						
						List<WebElement> chkBox = driver.findElements(By.xpath("//input[@name='FacilityIds']"));
						List<WebElement> chkBoxTxt = driver.findElements(By.xpath("//label[@class='checkbox-label xsmall']"));
						
						for(int k=0; k<chkBoxTxt.size(); k++ )
						{
							if(chkBoxTxt.get(k).getText().contains(val[l]))				
							{
								if(! chkBox.get(k).isSelected())
								{
									System.out.println("Facility has not selected by default1");
									chkBoxTxt.get(k).click();
								}
								
							}													
						}
					}
						break;
				}
				
				//DOB
//				Thread.sleep(2000);
//				driver.findElement(By.xpath("//input[@id='Dob' and @name='Dob']")).click();
//				driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/table/tbody/tr[1]/td[7]/a")).click();						
					
//					String facility = acc.getFirstSelectedOption().getText();
//					
//					System.out.println(facility);
					
//					if(facility.equalsIgnoreCase("Select"))			
//					{
//						// TO select facility			
//						acc.selectByIndex(9);	
//						List<WebElement> chkBox = driver.findElements(By.xpath("//label[@class='checkbox-label xsmall']"));
//						System.out.println(chkBox.get(0).getText());
//						val1 = chkBox.get(0).isSelected();
//						System.out.println(val1);
//						if(val1 != true)
//							System.out.println("Facility has not selected by default1");
//							loginPage.spinnerWait(driver);
//							chkBox.get(0).click();					
//					}
//					else
//					{
//						List<WebElement> chkBox = driver.findElements(By.xpath("//label[@class='checkbox-label xsmall']"));
//						
//						if(chkBox.size()>1)
//						{
//							val1 = chkBox.get(0).isSelected();
//							if(val1 != true)
//							{
//								System.out.println("Facility has not selected by default2");	
//								loginPage.spinnerWait(driver);
//								chkBox.get(0).click();
//							}
//							
//						}	
//					}
					
				}
			}
		}		
				
	public void existingPatientFields(String existing_patientname, String case1)
	{
		wait = new WebDriverWait(driver, 30);
		
		if(existing_patientname.equalsIgnoreCase("Senthil Patient_Auto"))
		{
	
			if(case1.equalsIgnoreCase("New case"))
			{
				// To click 'Existing Patient'
				driver.findElement(By.xpath("//label[@for='PatientSelect_existing']")).click();	
				// To pass value to editable dropdown field
				driver.findElement(By.xpath("//input[@name='SelectPatient_input']")).sendKeys(existing_patientname);			
				// Selecting the particular element
				wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//ul[@role='listbox']/li")))).click();
				
//				//To click the dropdown list
//				driver.findElement(By.xpath("//span[@aria-controls='SelectPatient_listbox']")).click();
//				//Selecting the particular element
//				wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//ul[@role='listbox']/li[1]")))).click();
				loginPage.spinnerWait(driver);
				Select list1 = new Select(driver.findElement(By.id("DentalCaseGuidId")));
				list1.selectByVisibleText("New Case");
			}
			else
			{
				// To click 'Existing Patient'
				driver.findElement(By.xpath("//label[@for='PatientSelect_existing']")).click();	
				// To pass value to editable dropdown field
				driver.findElement(By.xpath("//input[@name='SelectPatient_input']")).sendKeys(existing_patientname);
				//Selecting the particular element
				wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//ul[@role='listbox']/li")))).click();
				
//				//To click the dropdown list
//				driver.findElement(By.xpath("//span[@aria-controls='SelectPatient_listbox']")).click();				
//				//Selecting the particular element
//				wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//ul[@role='listbox']/li[1]")))).click();									
				loginPage.spinnerWait(driver);
				Select list1 = new Select(driver.findElement(By.id("DentalCaseGuidId")));
				list1.selectByVisibleText(case1);			
			}
		}
	}
	
	public boolean patientDuplication()
	{
		try
		{
			
			//String popUp = driver.findElement(By.xpath("//div[@aria-describedby='duplicatePatient']")).getAttribute("style");			
				loginPage.spinnerWait(driver);
				String title = driver.findElement(By.xpath("//div[@aria-describedby='duplicatePatient']/div[2]/h1")).getText();
				if(title.contains("Potential Duplication"))
				{
					System.out.println("Inside Duplicate popup");
					//To click 'Continue' button
					driver.findElement(By.xpath("//div[@aria-describedby='duplicatePatient']/div[3]/div/button[2]")).click();
				}		
			return true;
		}
		catch(Exception E)
		{
			System.out.println("Not able to find the potential duplication popup "+E.getMessage());
			return false;			
		}
	}
	
	public static WebElement nxtBtn()
	{		
		js = (JavascriptExecutor) driver;	
		
		element = driver.findElement(By.id("btn-Next"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		
		return element;		
	}
	public static WebElement cancelBtn()
	{
		element = driver.findElement(By.id("btn-Cancel"));	
		return element;		
	}
}
