package GR.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import GR.Pages.LandingPage;
import GR.Pages.OrdersPage;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	
	/*
	public WebDriver InitilizeDriver() throws IOException
	{
		Properties prop = new Properties();
		String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\GR\\Resources\\GlobalData.properties";
        File file = new File(filePath);
		FileInputStream FIS = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\GR\\Resources\\GlobalData.properties");
		prop.load(FIS);
		String browserName = prop.getProperty("browser");
		
		if (browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("Edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if (browserName.equalsIgnoreCase("Firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();

		return driver;
	}
	*/
	
	public WebDriver InitilizeDriver() throws IOException {
	    WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.manage().window().maximize();

	    return driver;
	}
	
	@BeforeMethod (alwaysRun = true)
	public LandingPage LaunchApplication() throws IOException
	{
	    driver = InitilizeDriver();
		LandingPage landingPage = new LandingPage(driver);
	    landingPage.GoTo();
		return landingPage;
		
	}
	
	@AfterMethod (alwaysRun = true)
	public void TearDown()
	{
		driver.close();
	}
	
	public List<HashMap<String, String>> GetJsonDataToMap(String filePath) throws IOException
	{
		String jsonContent = FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		List <HashMap< String, String>> data  = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){	
				});
		return data;
	} 
	
	public String GetScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir") + "\\Reports\\" + testCaseName + ".png";
		File file = new File(filePath);
		FileUtils.copyFile(source, file);
		
		return filePath;
	}
	
}
