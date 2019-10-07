package Application;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CustomerSignedIn extends LoadableComponent<CustomerSignedIn> 
{
	WebDriver driver;
	String url ="http://localhost/Avactis/home.php";
	
	@FindBy(xpath= "//a[@href= 'http://localhost/Avactis/index.php']")
	WebElement goToHomePage;
	
	public CustomerSignedIn(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@Override
	protected void load() 
	{
		driver.get(url);
		
	}

	@Override
	protected void isLoaded() throws Error 
	{
		Assert.assertEquals(driver.getCurrentUrl(), url);		
	}
	public void goToHome() 
	{
		System.out.println(driver.getCurrentUrl());
		WebDriverWait wait = new WebDriverWait(driver, 10);  
        wait.until(ExpectedConditions.urlToBe(url));
		goToHomePage.click();
	}
	
}
