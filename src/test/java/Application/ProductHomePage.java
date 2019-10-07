package Application;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class ProductHomePage extends LoadableComponent<ProductHomePage> 
{

	WebDriver driver;
	String url;
	
	@FindBy(xpath = "//div[@class = 'owl-carousel owl-carousel5 owl-theme']//div[@class = 'owl-next']")
	WebElement NextArrow;
	
	@FindBy(xpath = "//a[@href = 'http://localhost/Avactis/cart.php']")
	WebElement viewCart;
	
	@FindBy(xpath ="//a[@class = 'top-cart-info-count']")
	WebElement numberOfItem;

	
	public ProductHomePage(WebDriver driver) 
	{
		this.driver = driver;
		//this.url=url;
		PageFactory.initElements(driver, this);
	}

	public ProductHomePage() 
	{
		Assert.assertEquals(driver.getCurrentUrl(), url);
	}

	@Override
	protected void load() 
	{
		driver.get("http://localhost/Avactis/index.php");
	}

	@Override
	protected void isLoaded() throws Error 
	{
			
	}

	public void selectproduct(String productName) 
	{
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebElement product = driver.findElement(By.xpath("//h3[contains(text(),'"+productName+"')]"));
		product.click();
	}

//	public void clickViewCart() throws InterruptedException 
//	{
//		 Actions builder = new Actions(driver);
//	     builder.moveToElement(numberOfItem).build().perform();	      
//	     Thread.sleep(4000);
//	     viewCart.click();
//		 Actions builder1 = new Actions(driver);
//	     builder1.moveToElement(viewCart).build().perform();
//	}

	public void ViewCart() throws InterruptedException
	{
		 Actions builder = new Actions(driver);
	     builder.moveToElement(numberOfItem).build().perform();	      
	     Thread.sleep(4000);
	     viewCart.click();
	}

	public void verifyaddress(String address1, String address2) 
	{
		// TODO Auto-generated method stub
		
	}

}