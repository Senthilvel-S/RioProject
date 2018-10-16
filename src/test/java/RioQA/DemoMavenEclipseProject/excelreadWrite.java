package RioQA.DemoMavenEclipseProject;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class excelreadWrite {

		@Test
		public void readExcel() throws EncryptedDocumentException, IOException
		{
			int totalRow, totalCol;
			Row r, r1;
			Cell c;
			FileInputStream inp = new FileInputStream("C:\\Users\\senthilvel.s\\eclipse-workspace\\DemoMavenEclipseProject\\testData\\shippingInfo.xlsx");
			
			Workbook wb= WorkbookFactory.create(inp);
			Sheet s =wb.getSheet("ShippingInfo");
			totalRow = s.getPhysicalNumberOfRows();
			r = s.getRow(0);
			totalCol = r.getLastCellNum();
			
			System.out.println(totalRow);
			System.out.println(totalCol);
			
			String[][] a = new String[totalRow][totalCol];		

			
			for(int i=0; i<totalRow; i++)
			{
				r1 = s.getRow(i);
				for(int j=0; j<totalCol; j++)
				{
					c = r1.getCell(j);
					a[i][j] = c.getStringCellValue();			
				}
			}
			

			for(int i=0; i<totalRow; i++)
			{
				for(int j=0; j<totalCol; j++)
				{
					System.out.println(a[i][j]);
				}
			}			
			
		}


}
