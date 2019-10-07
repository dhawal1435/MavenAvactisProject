package Application;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProductSelectPage extends LoadableComponent<ProductSelectPage> 
{
	WebDriver driver;
	String url;
	@FindBy(xpath= "//input[@value='Add To Cart']")
	WebElement addToCart;
	
	@FindBy(xpath= "//a[@href= 'http://localhost/Avactis/index.php']")
	WebElement goToHomePage;
	
	@FindBy(xpath = "//a[@href = 'http://localhost/Avactis/cart.php']")
	WebElement viewCart;
	
	@FindBy(xpath ="//a[@class = 'top-cart-info-count']")
	WebElement numberOfItem;
	
	@FindBy(xpath ="//div[@class = 'form-group']//a[@href = 'http://localhost/Avactis/checkout.php'][contains(text(),'Checkout')]")
	WebElement checkoutButton;
	
	public ProductSelectPage(WebDriver driver, String url) 
	{
		this.driver = driver;
		this.url=url;
		PageFactory.initElements(driver, this);
	}

	public ProductSelectPage() 
	{
		Assert.assertEquals(driver.getCurrentUrl(), url);
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

	public void addInCart() throws InterruptedException 
	{
		addToCart.click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	public void goToHome() 
	{
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		goToHomePage.click();
	}
	
	public void clickViewCart() throws InterruptedException 
	{	
		 Actions builder = new Actions(driver);
	     builder.moveToElement(numberOfItem).build().perform();	      
	    // Thread.sleep(4000);
	     driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	     viewCart.click();
//		 Actions builder1 = new Actions(driver);
//	     builder1.moveToElement(viewCart).build().perform();
	}

	public void clickcheckoutbutton() 
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);  
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
		checkoutButton.click();
	//	return checkoutButton;
	}


}
