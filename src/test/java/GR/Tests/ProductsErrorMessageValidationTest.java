 package GR.Tests;

import java.io.IOException;
import org.testng.annotations.Test;

import GR.Pages.LandingPage;
import GR.Pages.ProductCatalouge;
import GR.TestComponents.BaseTest;

public class ProductsErrorMessageValidationTest extends BaseTest{

	@Test (groups = {"Regression"})
	public void EcomerceOrderPlacingTest() throws IOException, InterruptedException {

		LandingPage landingPage = LaunchApplication();
		ProductCatalouge PC = landingPage.LoginApplication("techworld7982@gmail.com", "Jinjin@123");
		
		// Product Error validation

	}

	
}
