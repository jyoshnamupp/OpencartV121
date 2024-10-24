package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.testng.annotations.BeforeClass;

public class BaseClass {
	
public static WebDriver driver;
public Logger logger; //log4j
public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(@Optional("windows") String os, @Optional("chrome") String br) throws InterruptedException, IOException
	{
		
		//LOADING CONFIG.PROPERTIES FILE
		FileReader file = new FileReader(".//src/test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		
		logger=LogManager.getLogger(this.getClass());//log4j2.xml will get into this logger
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cp = new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				cp.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				cp.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("no matching os");
				return;
			}
			
			//br
			
			switch(br.toLowerCase())
			{
			case "chrome" : cp.setBrowserName("chrome");break;
			case "edge"   : cp.setBrowserName("MicrosoftEdge");break;
			case "firefox"   : cp.setBrowserName("firefox");break;
			default:System.out.println("No Matching browser");return;
			}
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cp);
			
			}
		
		
		
		
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case"chrome":driver = new ChromeDriver();break;
			case"edge":driver = new EdgeDriver();break;
			case"firefox":driver = new FirefoxDriver();break;
			default:System.out.println("invalid browser name:");return;
			
			}
		}
		
		
	 
	  driver.manage().deleteAllCookies();
	  driver.get(p.getProperty("appURL"));
	  driver.manage().window().maximize();
	}
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown()
	{
		driver.quit();
	}
	public String randomeString()
	 {
		 String generatedstring=RandomStringUtils.randomAlphabetic(6);
		 return generatedstring;
	 }
	 public String randomeNumber()
	 {
		 String generatednumber=RandomStringUtils.randomNumeric(10);
		 return generatednumber;
	 }
	 public String randomeAlphaNumeric()
	 {
		 String generatedstring=RandomStringUtils.randomAlphabetic(6);
		 String generatednumber=RandomStringUtils.randomNumeric(4);
		 return (generatedstring+"@"+generatednumber);
	 }
	 public String captureScreen(String tname) throws IOException {

			String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
					
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
			
			String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
			File targetFile=new File(targetFilePath);
			
			sourceFile.renameTo(targetFile);
				
			return targetFilePath;

		}

}
