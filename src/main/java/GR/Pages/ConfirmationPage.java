package GR.Pages;

import java.util.ArrayList;
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

public class ConfirmationPage extends AbstractPage{

	WebDriver driver;
	// Constructor
	public ConfirmationPage(WebDriver driver)
	{
		// Constructor is the first method to execute, It is same name as class name
		// Initialization for object of the class
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// PageFactory

	@FindBy(xpath = "//h1[text()=' Thankyou for the order. ']")
	WebElement OrderConfirmationMessageLabel;
	
	@FindBy(xpath = "//label[@class='ng-star-inserted']")
	List<WebElement> OrderIDs;
	
	public boolean GetConfirmationMessage()
	{
		boolean message = OrderConfirmationMessageLabel.isDisplayed();
		return message;
	}
	
	public List<String> GetOrderIDs()
	{
		List<String> orderedProductsID = new ArrayList<>();
		
		for(WebElement ID : OrderIDs)
		{
			String orderID = ID.getText().replaceAll("\\|", "").trim();
			//String ID1 = orderID[0];
			//String ID2 = orderID[1];
			orderedProductsID.add(orderID);
		}

		return orderedProductsID;
	}
}
