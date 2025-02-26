package FramworkDesign.Testcomponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import FramworkDesign.Pageobject.Landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public Landingpage landingpage;
	
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fls=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//FramworkDesign//resouces//GlobalData.properties");
		prop.load(fls);
		
		String browsername=System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		//String browsername=prop.getProperty("browser");
		
		if(browsername.contains("chrome"))
		{
			ChromeOptions options= new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browsername.contains("headless"))
			{
			options.addArguments("headless");
			}
			 driver = new ChromeDriver(options);
			 driver.manage().window().setSize(new Dimension(1400,900));
		}
		else if(browsername.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			 driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    return driver;
	}
	   public List<HashMap<String, String>> getjsonDataTomap(String Filepath) throws IOException
		{
			//Read json to string
			
			String jsonContent=FileUtils.readFileToString(new File(Filepath),
					StandardCharsets.UTF_8);
			
			//String to HashMap jackson databind
			
			ObjectMapper mapper=new ObjectMapper();
			List<HashMap<String, String>> data=mapper.readValue(jsonContent,new TypeReference<List<HashMap<String, String>>>(){
			});
			return data;
			}
	   public String getScreenshot(String testCasename, WebDriver driver) throws IOException
	   {
		  TakesScreenshot ts=  (TakesScreenshot)driver;
		 File source  =ts.getScreenshotAs(OutputType.FILE);
		 File file= new File(System.getProperty("user.dir")+"//reports//"+testCasename+".png");
		 FileUtils.copyFile(source, file);
		 return System.getProperty("user.dir")+"//reports//"+testCasename+".png";
		  
	   }
	
	@BeforeMethod(alwaysRun=true)
	public Landingpage launchApplication() throws IOException
	{
		driver=initializeDriver();
	    landingpage=new Landingpage(driver);
		landingpage.GoTo();
		return landingpage;
	}
	@AfterMethod(alwaysRun=true)
	public void closebroser()
	{
		driver.close();
	}

}
