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

public class CartPage extends AbstractPage{

	WebDriver driver;
	// Constructor
	public CartPage(WebDriver driver)
	{
		// Constructor is the first method to execute, It is same name as class name
		// Initialization for object of the class
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// PageFactory
	@FindBy(xpath = "//div[@class='cartSection']/h3")
	List<WebElement> CartProducts;
	
	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement CheckOutButton;
	
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement SelectCountryTxt;
	
	@FindBy(xpath = "//button[@class='ta-item list-group-item ng-star-inserted']")
	List<WebElement> DropdownSearchOptions;
	
	@FindBy (xpath = "//a[text()='Place Order ']")
	WebElement PlaceOrderButton;
	
	@FindBy (xpath = "/preceding-sibling::p[@class='itemNumber']")
	List<WebElement> OrderID;
	
	public static By CartProductsBy = By.xpath("//div[@class='cartSection']/h3");
	public static By CheckOutBy = By.xpath("//button[text()='Checkout']");
	public static By OrderIDBy = By.xpath("./preceding-sibling::p[@class='itemNumber']");
	
	
	
	public List<String> VerifyCartProducts(List<String> buyItems)
	{
		List<String> orderedProductsID = new ArrayList<>();
		List<WebElement> cartProducts = CartProducts;				
		Assert.assertEquals(cartProducts.size(), buyItems.size());
		
		for(WebElement cartProduct : cartProducts)
		{
			String orderedProduct = cartProduct.getText();
			String orderID = cartProduct.findElement(OrderIDBy).getText();
			//boolean expected = buyItems.contains(actualItem);
			Assert.assertTrue(buyItems.contains(orderedProduct));
			//Assert.assertListContains(buyItems, actualItem, "Item does not exist");	
			orderedProductsID.add(orderID);
		}

		return orderedProductsID;
	}
	
	public CheckoutPage CheckOut()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", CheckOutButton);
		WaitUntilElementToBeClicable(CartPage.CheckOutBy);
		CheckOutButton.click();
		CheckoutPage COP = new CheckoutPage(driver);
		return COP;
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
