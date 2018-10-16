package RioQA.DemoMavenEclipseProject;

import org.openqa.selenium.support.ui.WebDriverWait;

public class varInitialize {
	
	static int var = 45;
		
	public void A()
	{
		System.out.println(var);
	}

	public static void main(String[] args) {

		varInitialize v = new varInitialize();
			
		int var1=50;
		
		v.A();		
		System.out.println(var);
		System.out.println(var1);

	}

}
