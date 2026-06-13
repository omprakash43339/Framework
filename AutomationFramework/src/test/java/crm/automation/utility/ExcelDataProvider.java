package crm.automation.utility;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {
	
	XSSFWorkbook wb;
	
	public ExcelDataProvider() {
		
		File src=new File("C:\\Framework Workspace\\AutomationFramework\\testdata\\data.xlsx");
		try {
			FileInputStream fis=new FileInputStream(src);
			wb=new XSSFWorkbook(fis);
		} catch (Exception e) {
			
			System.out.println("Unable to read Excel File "+ e.getMessage());
		}	
		
	}
	
	public String getStringData(String sheetname, int rowindex, int columnindex) {
		
		String stringvalue=wb.getSheet(sheetname).getRow(rowindex).getCell(columnindex).getStringCellValue();
		return stringvalue;
		
	}
	
	public double getNumericData(String sheetname, int rowindex, int columnindex) {
		
		double numericvalue=wb.getSheet(sheetname).getRow(rowindex).getCell(columnindex).getNumericCellValue();
		return numericvalue;
		
	}
	
	

}
