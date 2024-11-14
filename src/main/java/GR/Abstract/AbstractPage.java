package GR.Abstract;

import java.time.Duration;

import org.openqa.selenium.By;
<<<<<<< HEAD
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import GR.Pages.CartPage;
import GR.Pages.OrdersPage;

public class AbstractPage {

	WebDriver driver;
	public AbstractPage (WebDriver driver){
		
		this.driver = driver;
	}
	
	@FindBy(xpath = "//div[@class='ng-tns-c31-1 ng-star-inserted']")
	WebElement LoadIcn;
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement CartBtn;
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']") 
	public WebElement OrdersButton;
	
	public static By LoadIcon = By.xpath("//div[@class='ng-tns-c31-1 ng-star-inserted']");
	public static By CartButton = By.xpath("//button[@routerlink='/dashboard/cart']");

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	public void WaitForVisibilityOfElement(By Locator)
	{
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(Locator));

	}
	
	public void WaitForInvisiblityOfElement(By Locator)
	{
		wait.until(ExpectedConditions.invisibilityOfElementLocated(Locator));

	}
	
	public void WaitUntilElementToBeClicable(By Locator)
	{
	    wait.until(ExpectedConditions.elementToBeClickable(Locator));

	}
	
	public CartPage NavigateToCart() throws InterruptedException
	{
		
		WaitUntilElementToBeClicable(CartButton);
		Thread.sleep(2000);
		CartBtn.click();
		WaitUntilElementToBeClicable(CartPage.CheckOutBy);
		CartPage CP = new CartPage(driver);
		return CP;
	}
	
	public OrdersPage GoToOrders()
	{
		OrdersButton.click();
		OrdersPage OP = new OrdersPage(driver);
		return OP;
=======
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {

	WebDriver driver;
	public AbstractPage (WebDriver driver){
		
		this.driver = driver;
	}
	
	@FindBy(xpath = "//div[@class='ng-tns-c31-1 ng-star-inserted']")
	WebElement LoadIcn;
	
	public static By LoadIcon = By.xpath("//div[@class='ng-tns-c31-1 ng-star-inserted']");
	
	public void WaitForVisibilityOfElement(By Locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(Locator));

	}
	
	public void WaitForInvisiblityOfElement(By Locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(Locator));

	}
	
	public void WaitUntilElementToBeClicable(By Locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(Locator));

>>>>>>> branch 'master' of https://github.com/jinogr4/JavaSeleniumProject.git
	}

}
