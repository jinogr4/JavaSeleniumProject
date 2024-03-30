package GR.Abstract;

import java.time.Duration;

import org.openqa.selenium.By;
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

	}

}
