package Utility;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;

import TestSuiteBase.SuiteBase;



public class FetchExcelDataSet {
	
	SuiteBase objSB = new SuiteBase();
	
	HashMap<Integer, LinkedHashMap<String , String>> hashDataSet = new HashMap<Integer, LinkedHashMap<String , String>>();
	
	private LinkedHashMap<String, String> getData(HashMap<Integer, LinkedHashMap<String , String>> hashMap, int rowNumber){
		LinkedHashMap<String , String> hashData = null;
		hashData = hashMap.get(rowNumber);
		return hashData;
	}
	
	public Object[][] getDataSetAsObjectArray(String strExcelPath, String sheetName, String testcaseName){
		HashMap<Integer, LinkedHashMap<String , String>> hashDataSet = makeTestData(strExcelPath, sheetName, testcaseName);

		Object[][] objArray = new Object[hashDataSet.size()][1];
		for(int i=0; i<hashDataSet.size(); i++) {
			objArray[i][0]=getData(hashDataSet, i);
		}
		return objArray;
	}
	
	public HashMap<Integer, LinkedHashMap<String , String>> makeTestData(String strExcelPath, String sheetName, String testcaseName){
		
		FetchExcelDataSet fetchTestDataSet = new FetchExcelDataSet();
		HSSFSheet excelsheet = null;
		try {
			FileInputStream execelFileStream = new FileInputStream(strExcelPath);
			@SuppressWarnings("resource")
			HSSFWorkbook excelWorkBook = new HSSFWorkbook(execelFileStream);
			excelsheet = excelWorkBook.getSheet(sheetName);
			int numrows = excelsheet.getLastRowNum();
			int colindex = -1;
			for (int count =0; count<excelsheet.getRow(0).getLastCellNum(); count++)
			{
				if (excelsheet.getRow(0).getCell(count).getStringCellValue().equalsIgnoreCase("TestCaseName"))
				{
					colindex = count;
					break;
				}			
			}
			 for (int rowcount =1 , validerows =1 ; rowcount<=numrows;rowcount++)
			 {
				 if (excelsheet.getRow(rowcount).getCell(colindex).getStringCellValue().equalsIgnoreCase(testcaseName))
					{
					 hashDataSet.put(validerows-1, fetchTestDataSet.getRowData(excelsheet,rowcount));
					 validerows++;
					 
					}
			 }		
	}catch(Exception e){
			e.printStackTrace();
		}
		
		return hashDataSet;
			
	}
	
	
	private LinkedHashMap<String, String> getRowData(HSSFSheet excelsheet, int rowcount){
		LinkedHashMap<String, String> hashrowdata = new LinkedHashMap<String, String>();
		HSSFRow headerrow = excelsheet.getRow(0);
		HSSFRow row = excelsheet.getRow(rowcount);
		int totalInputvalues = row.getLastCellNum();
		for( int cellCount=0; cellCount<totalInputvalues; cellCount++)
		{
			HSSFCell headercell = headerrow.getCell(cellCount);
			HSSFCell cell = row.getCell(cellCount, MissingCellPolicy.CREATE_NULL_AS_BLANK);
			cell.setCellType(CellType.STRING);
			String cellValue = cell.getStringCellValue();
			hashrowdata.put(headercell.getStringCellValue(), cellValue);
		}
		return hashrowdata;
		
	}
		
	
			
			
		
	
	


	
	
	
	
	
	
	
	
	
}