package GR.StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import GR.Pages.CartPage;
import GR.Pages.CheckoutPage;
import GR.Pages.ConfirmationPage;
import GR.Pages.LandingPage;
import GR.Pages.ProductCatalouge;
import GR.TestComponents.BaseTest;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalouge PC;
	public CartPage CP;
	public CheckoutPage COP;
	public ConfirmationPage CFP;
	
	@Given("I Landed on Ecommerce Application")
	public void I_Landed_on_Ecommerce_Application() throws IOException
	{
		landingPage = LaunchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")   // (.+) - Regular expression to call it
	public void Logged_with_username_and_password(String username, String password) throws InterruptedException
	{
		PC = landingPage.LoginApplication(username, password);
	}
	
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(List productname)
	{
		List<WebElement> products = PC.GetProducts();
	    PC.AddProductsToCart(products, productname);
	}
	
	@When("checkout product and place the order")
	public void Checkout_and_Place_Order(List productname) throws InterruptedException
	{
		CP = PC.NavigateToCart();
		
		// Verify Cart products
	    CP.VerifyCartProducts(productname);
		
		// Place ORder
	    COP = CP.CheckOut();	
	    COP.SelectCountry("Ind", "India");		
		CFP = COP.PlaceOrder();
	}
	
	@Then("{text} message is displayed on confirmation page")
	public void Message_displayed_Confirmation(String text)
	{
		Boolean expectedPlacingORderMessage = CFP.GetConfirmationMessage();
		Assert.assertTrue(expectedPlacingORderMessage);	
	}
	
	@ParameterType(".*")
    public String text(String text) {
        return text;
    }
}
