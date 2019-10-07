package Application;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class OrderedPage extends LoadableComponent<OrderedPage> 
{
	WebDriver driver;
	String url = "http://localhost/Avactis/order_placed.php?asc_action=SetCurrStep/step_id=4";
	WebDriverWait wait;  
	
	@FindBy(xpath = "//div[@class = 'note note-success note-bordered']")
	WebElement thankyouNote;            ////thankyou for the order
	
	@FindBy(xpath ="//label[@class = 'control-label col-lg-3'][contains(text(),'Order Id:')]/following-sibling::div")
	WebElement orderId;
	
	@FindBy(xpath = "//em[contains(text(),'Order Total:')]/following-sibling::strong")
	WebElement finalPrice;
	
	@FindBy(xpath ="//label[@class = 'control-label col-lg-10'][contains(text(),'.com')]")
	WebElement emailId;
	
	public OrderedPage(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 10);
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

	public String verifyPrice() throws InterruptedException 
	{
		
     //   wait.until(ExpectedConditions.urlToBe("http://localhost/Avactis/order_placed.php?asc_action=SetCurrStep/step_id=4"));
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		String price = finalPrice.getText();
		System.out.println("final price =  " + price);
		
	//	System.out.println(finalPrice);
		return price;
	}

	public String verifyOrderId() 
	{
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		String order = orderId.getText();
		System.out.println("Order ID =  " + order);
		
	//	System.out.println(finalPrice);
		return order;
	}

	public String verifyEmailId()
	{
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		String email = emailId.getText();
		System.out.println("Email ID =  " + email);
		return email;
	}

}
