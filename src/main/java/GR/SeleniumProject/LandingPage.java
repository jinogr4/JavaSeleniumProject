package GR.SeleniumProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GR.Abstract.AbstractPage;

public class LandingPage extends AbstractPage{

	WebDriver driver;
	// Constructor
	public LandingPage(WebDriver driver)
	{
		// Constructor is the first method to execute, It is same name as class name
		// Initialization for object of the class
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement UserEmailTxt1 = driver.findElement(By.id("userEmail"));
	//WebElement UserPasswordTxt1 = driver.findElement(By.id("userPassword"));
	//WebElement LoginBrn1 = driver.findElement(By.id("login"));
	
	// PageFactory
	@FindBy(id = "userEmail")
	WebElement UserEmailTxt;
	
	@FindBy(id = "userPassword")
	WebElement UserPasswordTxt;
	
	@FindBy(id = "login")
	WebElement LoginBtn;
	
	
	public void GoTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public void LoginApplication(String email, String password)
	{
		UserEmailTxt.sendKeys(email);
		UserPasswordTxt.sendKeys(password);
		LoginBtn.click();		
	}
}
