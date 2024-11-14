 package GR.Tests;

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

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Test Start");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
				
		//LandingPage landingPage = new LandingPage(driver);
		 // Login 
		driver.findElement(By.id("userEmail")).sendKeys("techworld7982@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Jinjin@123");
		driver.findElement(By.id("login")).click();
		
		// Add products to Cart
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='card-body']//b"));

		String item;
		//String buyItems = "ADIDAS ORIGINAL, IPHONE 13 PRO";
		List<String> buyItems = Arrays.asList("ADIDAS ORIGINAL", "IPHONE 13 PRO");
			
		// Add items to Cart
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ng-tns-c31-1 ng-star-inserted']")));
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ng-tns-c31-1 ng-star-inserted']")));
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@routerlink='/dashboard/cart']")));
	    
		for( int i=0; i< products.size(); i++ )
		{
		     item = products.get(i).getText().trim();
			if(buyItems.contains(item))
			{
				//products.get(i).findElement(By.xpath("//button[@class='btn w-10 rounded']")).click();
				driver.findElement(By.xpath("//b[text()='" + item + "']/../following-sibling::button[@class='btn w-10 rounded']")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ng-tns-c31-1 ng-star-inserted']")));
			    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ng-tns-c31-1 ng-star-inserted']")));
			    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@routerlink='/dashboard/cart']")));

			}
		}	
		
		// Navigate to Cart
	    
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
				
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
