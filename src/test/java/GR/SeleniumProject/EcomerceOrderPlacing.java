 package GR.SeleniumProject;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import GR.Abstract.AbstractPage;

public class EcomerceOrderPlacing{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
				
		LandingPage landingPage = new LandingPage(driver);
		landingPage.GoTo();
		landingPage.LoginApplication("techworld7982@gmail.com", "Jinjin@123");
		
		ProductCatalouge PC = new ProductCatalouge(driver);
		// Add products to Cart
		List<WebElement> products = PC.GetProducts();

		String item;
		//String buyItems = "ADIDAS ORIGINAL, IPHONE 13 PRO";
		List<String> buyItems = Arrays.asList("ADIDAS ORIGINAL", "IPHONE 13 PRO");
			
		// Add items to Cart
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PC.WaitForInvisiblityOfElement(By.xpath("//div[@class='ng-tns-c31-1 ng-star-inserted']"));
	    PC.WaitUntilElementToBeClicable(By.xpath("//button[@routerlink='/dashboard/cart']"));
	    
	    PC.AddProductsToCart(products, buyItems);
				
		// Navigate to Cart
	    
		PC.CartBtn.click();
				
		// Verify Cart products
		List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));				
		Assert.assertEquals(cartProducts.size(), buyItems.size());
		
		for(WebElement cartProduct : cartProducts)
		{
			String actualItem = cartProduct.getText();
			//boolean expected = buyItems.contains(actualItem);
			Assert.assertTrue(buyItems.contains(actualItem));
			//Assert.assertListContains(buyItems, actualItem, "Item does not exist");			
		}
		
		// Place ORder
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("Ind");
		
		List<WebElement> options = driver.findElements(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']"));
		
		// Find country
		for(WebElement option:options)
		{
			if(option.getText().equalsIgnoreCase("India"))
			{
				option.click();
				break;
			}
		}
		
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
		
		boolean expectedPlacingORder = driver.findElement(By.xpath("//h1[text()=' Thankyou for the order. ']")).isDisplayed();
		Assert.assertTrue(expectedPlacingORder);
		
		System.out.println("Test Passed");
		
		
		
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

	
}
