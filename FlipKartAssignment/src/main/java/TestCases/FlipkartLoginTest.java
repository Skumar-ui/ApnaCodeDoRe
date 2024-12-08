package TestCases;

import PageObject.FlipkartLoginPage;
import TestSuiteBase.SuiteBase;
//import TestSuitBase.SuiteBase;
import Utility.readxls;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FlipkartLoginTest extends SuiteBase {
	
	readxls filepath = null;
	String TestCaseName=null;
	public int DataSet=-1;
	public boolean Testskip = false;
	
@BeforeClass
public void setup(){
	try {
		initialsetup();
	}catch(IOException e)
			{
		e.printStackTrace();
	}
	filepath = TestcaseTestdatafile ;
	listofTC = TestcaseTestdatafile.getlistofTC("TestCases");		
	/*
	 * FlipkartHomePage homepage = new FlipkartHomePage(); homepage.initialize();
	 * homepage.NavigateTo(); FlipkartLoginPage login = new FlipkartLoginPage();
	 * login.getFusername().sendKeys("psushilkumar9@gmail.com");
	 * login.getReqOTP().click();
	 */	
}

@Test(dataProvider = "Login" , dataProviderClass = Utility.XlsDataProvider.class)
public void loginWithValidCredentials(LinkedHashMap<String , String> data) {
	
	DataSet++;
	
	TestCaseName = getData(data, "TestCaseName");
	CaseToRun = listofTC.get(TestCaseName);
	
	if(CaseToRun.equalsIgnoreCase("N")) {
		System.out.println(TestCaseName+" : CaseToRun = N hence skipping the testcase.");
		Testskip=true;
		throw new SkipException(TestCaseName+"'s CaseToRun is N so skipping execution of ");
	} else {
		Reporter.log("Test Case ID : "+TestCaseName);
		
		loadwebBrowser();
		String appURL = getData(data, "AppURL");
		getDriver().get(appURL);
		//Create method for gamil actions
		loadwebBrowser2();
		getDriver2().get("https://gmail.com");

		FlipkartLoginPage objLogin = PageFactory.initElements(driver, FlipkartLoginPage.class);
		objLogin.loginWithValidCredentials(data);
		
		
	}
	
	
}


}
