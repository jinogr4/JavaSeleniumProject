package GR.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import GR.Abstract.AbstractPage;

public class CheckoutPage extends AbstractPage{

	WebDriver driver;
	// Constructor
	public CheckoutPage(WebDriver driver)
	{
		// Constructor is the first method to execute, It is same name as class name
		// Initialization for object of the class
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// PageFactory

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement SelectCountryTxt;
	
	@FindBy(xpath = "//button[@class='ta-item list-group-item ng-star-inserted']")
	List<WebElement> DropdownSearchOptions;
	
	@FindBy (xpath = "//a[text()='Place Order ']")
	WebElement PlaceOrderButton;
	
	
	
	public void SelectCountry(String searchTxt, String expectedOption)
	{
		SelectCountryTxt.sendKeys(searchTxt);
		
		List<WebElement> options = DropdownSearchOptions;
		
		// Find country
		for(WebElement option:options)
		{
			if(option.getText().equalsIgnoreCase(expectedOption))
			{
				option.click();
				break;
			}
		}
	}
	
	public ConfirmationPage PlaceOrder()
	{
		PlaceOrderButton.click();
		ConfirmationPage CFP = new ConfirmationPage(driver);
		return CFP;
	}
	
	
}
