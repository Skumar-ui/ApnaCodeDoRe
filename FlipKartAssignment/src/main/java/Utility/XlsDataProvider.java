package Utility;



import org.testng.annotations.DataProvider;

import TestSuiteBase.SuiteBase;

import java.lang.reflect.Method;




public class XlsDataProvider extends SuiteBase{
	
	static String filepath = Config.getProperty("testdatasheetpath");
	
	
	@DataProvider(name="Login")
	public static Object[][] Containssearchfetchdata1(Method a)
	{
		FetchExcelDataSet excelDatset = new FetchExcelDataSet();
		Object[][] dataset = excelDatset.getDataSetAsObjectArray(filepath, "Login",a.getName());
		return dataset;
	}

}