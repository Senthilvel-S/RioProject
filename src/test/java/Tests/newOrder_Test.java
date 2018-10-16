package Tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageObjects.Patient_orderWizard;
import PageObjects.Product_orderWizard;
import PageObjects.Records_orderWizard;
import PageObjects.Review_orderWizard;
import PageObjects.Rx_orderWizard;
import PageObjects.loginPage;
import Setup.browserSetup;

public class newOrder_Test extends browserSetup{
	

	WebDriver driver;
	
	@BeforeTest
	public void setUp()
	{
			driver = getDriver();
	}
	
	//To create an New Order+New Patient
	@Test (priority=0)
	@Parameters ({"expedite", "arches", "retainer_fulfilloption", "existing_patient", "caseID", "impressionType", "impressionDate", "shippingInfo"})
	public void newPatientOrder(String expedite, String arches, String retainer_fulfilloption, String existing_patient, String caseID, String impressionType, String impressionDate,  @Optional("NoShippingInfo") String shippingInfo) throws IOException, InterruptedException  {
		// Accessing patient page and filling all the required fields
		Patient_orderWizard patient = new Patient_orderWizard(driver);
		patient.externalLogin();
		//patient.internalLogin();
		patient.newPatientFields();	
		//Wait for 'Next' button to appear
		loginPage.spinnerWait(driver);
		Patient_orderWizard.nxtBtn().click();		
		//String prodChk = driver.findElement(By.xpath("//h1[text()='Product']")).getText();
		//if(! prodChk.equalsIgnoreCase("Product"))

			boolean retValue1= patient.patientDuplication();
			//For duplication pass value as 'Duplicate Patient' 
			if(retValue1)
			{
				//patient.existingPatientFields(retValue1,"Duplicate Patient",caseID);
				//Wait for 'Next' button to appear
				loginPage.spinnerWait(driver);
				Patient_orderWizard.nxtBtn().click();
			}	
		//Product Page
		//Wait for 'Product' page to appear
		loginPage.spinnerWait(driver);
		Product_orderWizard product1 = new Product_orderWizard(driver);
		product1.selectProduct(arches);
		product1.shippingInfo(arches, retainer_fulfilloption, shippingInfo);
		product1.selectExpedite(expedite);
		//Wait for 'Next' button to appear
		//loginPage.spinnerWait(driver);
		Patient_orderWizard.nxtBtn().click();
		
		//Records Page
		Records_orderWizard records = new Records_orderWizard(driver);
		records.dentalstatus1();	
		records.intraoralRecords(impressionType, impressionDate);
		records.uploadRecords();
		Patient_orderWizard.nxtBtn().click();		
		if(! arches.contains("Retainer"))
		{
			//Rx Page
			//loginPage.spinnerWait(driver);
			Rx_orderWizard rx = new Rx_orderWizard(driver);
			rx.prescriptionPage();
			Patient_orderWizard.nxtBtn().click();
		}
		//Review page
		//loginPage.spinnerWait(driver);
		Review_orderWizard review = new Review_orderWizard(driver);
		review.summaryPage();
		Review_orderWizard.submitBtn().click();
	}
	
//	//To create an New Order+Existing Patient
//	@Test (priority=1)
//	@Parameters ({"expedite", "arches", "retainer_fulfilloption", "existing_patient", "caseID", "impressionType", "impressionDate", "shippingInfo"})
//	public void existingPatientOrder(String expedite, String arches, String retainer_fulfilloption, String existing_patientname, String caseID, String impressionType, String impressionDate, @Optional("NoShippingInfo") String shippingInfo) throws InterruptedException, IOException  {
//				
//			// Accessing patient and filling all the required fields
//			Patient_orderWizard patient = new Patient_orderWizard(driver);
//			patient.externalLogin();
//			//patient.internalLogin();
//			//To choose the existing patient pass value as 1
//			patient.existingPatientFields(existing_patientname,caseID);	
//			//Wait for 'Next' button to appear
//			loginPage.spinnerWait(driver);
//			Patient_orderWizard.nxtBtn().click();
//			//Product Page
//			//Wait for 'Product' page to appear
//			loginPage.spinnerWait(driver);
//			Product_orderWizard product1 = new Product_orderWizard(driver);			
//			product1.selectProduct(arches);
//			product1.shippingInfo(arches, retainer_fulfilloption, shippingInfo);
//			product1.selectExpedite(expedite);
//			//Wait for 'Next' button to appear
//			//loginPage.spinnerWait(driver);
//			Patient_orderWizard.nxtBtn().click();
//			//Records Page
//			Records_orderWizard records = new Records_orderWizard(driver);
//			records.dentalstatus();	
//			records.intraoralRecords(impressionType, impressionDate);
//			records.uploadRecords();
//			Patient_orderWizard.nxtBtn().click();			
//			if(! arches.contains("Retainer"))
//			{
//				//Rx Page
//				//loginPage.spinnerWait(driver);
//				Rx_orderWizard rx = new Rx_orderWizard(driver);
//				rx.prescriptionPage();
//				Patient_orderWizard.nxtBtn().click();
//			}
//			//Review page
//			//loginPage.spinnerWait(driver);
//			Review_orderWizard review = new Review_orderWizard(driver);
//			review.summaryPage();
//			Review_orderWizard.submitBtn().click();
//	}
	
	
	@AfterTest
	public void closeBrowser()
	{
		//driver.quit();
	}

}
