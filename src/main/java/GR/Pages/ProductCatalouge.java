package GR.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import GR.Abstract.AbstractPage;

public class ProductCatalouge extends AbstractPage{

	WebDriver driver;
	// Constructor
	public ProductCatalouge(WebDriver driver)
	{
		// Constructor is the first method to execute, It is same name as class name
		// Initialization for object of the class
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// PageFactory
	@FindBy(xpath = "//div[@class='card-body']//b")
	List<WebElement> Products;
	
	
	
	public static By ProductBy = By.xpath("//div[@class='card-body']//b");
	public static By AddToCartBtn = By.xpath("//button[@class='btn w-10 rounded']");
	
	
	
	public List<WebElement> GetProducts()
	{
		driver.findElement(ProductBy);
		
		return Products;
	}
	
	
	public void AddProductsToCart(List<WebElement> products, List<String> buyItems)
	{
		//PC.WaitForInvisiblityOfElement(AbstractPage.LoadIcon);
	    WaitUntilElementToBeClicable(AbstractPage.CartButton);
	    WaitUntilElementToBeClicable(AddToCartBtn);
		for( int i=0; i< products.size(); i++ )
		{
		    String item = products.get(i).getText().trim();
			if(buyItems.contains(item))
			{
				WaitForInvisiblityOfElement(AbstractPage.LoadIcon);
				//products.get(i).findElement(By.xpath("//button[@class='btn w-10 rounded']")).click();
				driver.findElement(By.xpath("//b[text()='" + item + "']/../following-sibling::button[@class='btn w-10 rounded']")).click();
				WaitForVisibilityOfElement(AbstractPage.LoadIcon);
				WaitForInvisiblityOfElement(AbstractPage.LoadIcon);

			}
		}	
	}
	
}
