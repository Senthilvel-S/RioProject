package Setup;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class commonClass {
	
	public static String[][] readExcel(String excelPath, String sheetName) throws EncryptedDocumentException, IOException
	{
		int totalRow, totalCol;
		Row r, r1;
		Cell c;
		FileInputStream inp = new FileInputStream(excelPath);
		
		Workbook wb= WorkbookFactory.create(inp);
		Sheet s =wb.getSheet(sheetName);
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
		
		return a;
	}
	

}
