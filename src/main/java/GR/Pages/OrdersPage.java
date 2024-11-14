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

public class OrdersPage extends AbstractPage{

	WebDriver driver;
	// Constructor
	public OrdersPage(WebDriver driver)
	{
		// Constructor is the first method to execute, It is same name as class name
		// Initialization for object of the class
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// PageFactory
	
	@FindBy(xpath = "//tr[@class='ng-star-inserted']/th")
	List<WebElement> OrderIDList;
	
	
	
	public void VerifyOrders(List<String> OrderIDs)
	{
		for(WebElement orderID : OrderIDList)
		{
			String actualOrderID = orderID.getText();
			Assert.assertTrue(OrderIDs.contains(actualOrderID));
			System.out.println(actualOrderID);		
		}
	}
	

	
	
	/*
	 * List<String> actualItems = new ArrayList<>();
	for(WebElement cartProduct : cartProducts)
	{
		String actualItem = cartProduct.getText().trim();
		actualItems.add(actualItem);
	}
	for( int i = 0; i< Math.min(products.size(), actualItems.size()); i++)
	{
		String expectedItem = products.get(i).getText().trim();
		String actualItem = actualItems.get(i).trim();
		Assert.assertEquals(actualItem, expectedItem, "Item at index " + i + " does not exist");
	}
	*/
	
}
