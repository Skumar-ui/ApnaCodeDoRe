package TestSuiteBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.System.Logger;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;
import Utility.readxls;
import org.apache.logging.log4j.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
public class SuiteBase {

	public static org.apache.logging.log4j.Logger add_logs =null;
		public static Properties Config =null;
		//private WebDriver driver;
	//	private String baseUrl;
		public static double exeStarttime, exefinishtime;
		public static readxls TestcaseTestdatafile = null;
		readxls filepath = null;
		public HashMap<String, String> listofTC = null;
		public String CaseToRun=null;
		//protected WebDriver driver = new ChromeDriver();
		protected WebDriver driver = null;
		protected WebDriver driver2 = null;

		public WebDriver getDriver()
		{
			return driver;
		}
		
		public WebDriver getDriver2()
		{
			return driver2;
		}
	
	@BeforeSuite
	public void beforesuite() throws IOException 
	{
		Config = new Properties();
		FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\Config.properties");
		Config.load(fip);
		exeStarttime = System.currentTimeMillis();
		
		
	}
	
	public void loadwebBrowser()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		((WebDriver) driver).manage().window().maximize();
		add_logs.info("Browser loaded Sussuessfuly");
	}
	
	public void loadwebBrowser2()
	{
		WebDriverManager.chromedriver().setup();
		driver2 = new ChromeDriver();
		((WebDriver) driver).manage().window().maximize();
		add_logs.info("Browser2 loaded Sussuessfuly");
	}

	
	@AfterSuite
	public void aftersuite()
	{
		exefinishtime = System.currentTimeMillis();
	}
	
	public void initialsetup() throws IOException{
			add_logs = LogManager.getLogger();
			TestcaseTestdatafile = new readxls(Config.getProperty("testdatasheetpath"));
			filepath = TestcaseTestdatafile;
			//appurl = TestcaseTestdatafile.getappurl("appurl");
	}
	
	public String getData(LinkedHashMap<String, String> data, String key) {
		if(data.get(key)!=null & data.get(key).length()>0) {
			return data.get(key);
		} else {
			return "";
		}
	}
	
}