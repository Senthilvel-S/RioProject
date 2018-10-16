package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Rx_orderWizard {
	
	public static WebDriver driver;

	public Rx_orderWizard(WebDriver driver){
		this.driver = driver;
	}
	
	public void prescriptionPage()
	{
		//Occlusion
		//Overbite
		driver.findElement(By.xpath("//label[@for='PrescriptionInfo_Overbite_ShowOverbite']")).click();
		
		//Overjet
		driver.findElement(By.xpath("//label[@for='PrescriptionInfo_Overjet_ShowOverjet']")).click();
	}
	
	
}
