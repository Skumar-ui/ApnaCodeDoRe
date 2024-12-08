package Utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.devtools.v128.page.Page.CaptureScreenshotFormat;
import org.testng.ITestResult;

import TestSuiteBase.SuiteBase;


public class screenshotutility {
	
	String Screenshotsonfail="Yes";
	
	public void captureScreenShot(ITestResult result, String Status)
	{
		String destDir ="";
		String passfailmethod = result.getMethod().getRealClass().getSimpleName()+"."+result.getMethod().getMethodName();
		File sourceFile = ((TakesScreenshot) ((SuiteBase) result.getInstance()).getDriver()).getScreenshotAs(OutputType.FILE);
		DateFormat dateformat = new SimpleDateFormat("dd-mm-yyyy_hh_mm_ssaa");
		if(Status.equalsIgnoreCase("fail"))
		{
			destDir = SuiteBase.Config.getProperty("Screentshots");
		}
		
		new File(destDir).mkdirs();
		String destFile= passfailmethod+"-"+dateformat.format(new Date() + ".jpeg");
		
		try {
			FileUtils.copyFile(sourceFile, new File(destDir+"/"+destFile));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//@Override
	public void onTestfailur(ITestResult Tr)
	{
		 if(Screenshotsonfail.equalsIgnoreCase("Yes"))
		 {
			 captureScreenShot(Tr,"Fail");
		 }
	}

}
