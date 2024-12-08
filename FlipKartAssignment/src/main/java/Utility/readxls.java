package Utility;

import java.io.FileInputStream;

import javax.imageio.stream.FileImageInputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.util.HashMap;



public class readxls {

	public String filelocation = null;
	public FileInputStream filestr =null;
	public HSSFWorkbook wb = null;
	public HSSFSheet ws = null;
	
	public readxls(String filelocation)
	{
		this.filelocation = filelocation;
		try
		{
			filestr = new FileInputStream(filelocation);
			wb = new HSSFWorkbook(filestr);
			ws = wb.getSheetAt(0);
			filestr.close();	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		
		public HashMap<String, String> getlistofTC(String wsname)
		{
			try
			{
				int rowNum = retrieveNoofRows(wsname);
				HashMap<String, String> listOfTC = new HashMap<>();
				for(int i=0; i<rowNum;i++)
				{
					HSSFRow row = ws.getRow(i);
					listOfTC.put(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue());
					
				}
				return listOfTC;
			}
			catch (Exception e) {
				return null;
				// TODO: handle exception
			}
		}
	
		
		public int retrieveNoofRows(String wsName)
		{
			int sheetIndex = wb.getSheetIndex(ws);
			if(sheetIndex == -1)
			{
				return 0;
			} else 
			{
				ws = wb.getSheetAt(sheetIndex);
				int rowCount = ws.getLastRowNum()+1;
				return rowCount;
			}
		}
			
	
		
	
}