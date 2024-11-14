 package GR.Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import GR.Pages.CartPage;
import GR.Pages.CheckoutPage;
import GR.Pages.ConfirmationPage;
import GR.Pages.LandingPage;
import GR.Pages.OrdersPage;
import GR.Pages.ProductCatalouge;
import GR.TestComponents.BaseTest;

public class EcomerceOrderPlacingTest extends BaseTest{

	public LandingPage landingPage;
	List<String> buyItems = Arrays.asList("ADIDAS ORIGINAL", "IPHONE 13 PRO");
	List<String> orderIDs;
	
	@Test (dataProvider="GetData", groups= {"purchase","Regression"})
	public void OrderPlacingTest(HashMap <String, String> testData) throws IOException, InterruptedException {

		LandingPage landingPage = new LandingPage(driver);
		//landingPage = LaunchApplication();
		ProductCatalouge PC = landingPage.LoginApplication(testData.get("email"), testData.get("password"));
		
		// Add products to Cart
		List<WebElement> products = PC.GetProducts();
	    PC.AddProductsToCart(products, buyItems);
				
		// Navigate to Cart	    
	    CartPage CP = PC.NavigateToCart();
				
		// Verify Cart products
	    CP.VerifyCartProducts(buyItems);
		
		// Place ORder
	    CheckoutPage COP = CP.CheckOut();	
	    COP.SelectCountry("Ind", "India");		
		ConfirmationPage CFP = COP.PlaceOrder();
		
		Boolean expectedPlacingORderMessage = CFP.GetConfirmationMessage();
		Assert.assertTrue(expectedPlacingORderMessage);	
		orderIDs = CFP.GetOrderIDs();
		System.out.println("Test Passed");
		
	}

	
	@Test (dependsOnMethods = {"OrderPlacingTest"})
	public void OrderHistoryTest() throws IOException, InterruptedException
	{
		LandingPage landingPage = new LandingPage(driver);
		ProductCatalouge PC = landingPage.LoginApplication("techworld7982@gmail.com", "Jinjin@123");
		
		OrdersPage OP = PC.GoToOrders();
		System.out.println(orderIDs);

		OP.VerifyOrders(orderIDs);
		
	}
	
	
	@DataProvider
	public Object[][] GetData() throws IOException
	{
		List<HashMap<String, String>> data =  GetJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\GR\\Data\\OrderPlacing.json");
		return new Object[][] { {data.get(0)}, {data.get(1)} };
		
		/*
		Object[][] testData = new Object[data.size()][1];
	    for (int i = 0; i < data.size(); i++) {
	        testData[i][0] = data.get(i);
	        */
		    
	}
	
	
	
}
