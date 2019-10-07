package Application;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CheckoutPage extends LoadableComponent<CheckoutPage> 
{
	WebDriver driver;
	String url;
	WebDriverWait wait  ;

	@FindBy(xpath= "//input[@name = 'billingInfo[Firstname]']")
	WebElement firstnameTextbox;

	@FindBy(xpath= "//input[@name = 'billingInfo[Lastname]']")
	WebElement lastnameTextbox;
	
	@FindBy(xpath= "//input[@name = 'billingInfo[Email]']")
	WebElement emailTextbox;
	
	@FindBy(xpath= "//input[@name = 'billingInfo[Postcode]']")
	WebElement zipcodeTextbox;
	
	@FindBy(xpath= "//input[@name = 'billingInfo[City]']")
	WebElement cityTextbox;
	
	@FindBy(xpath= "//input[@name = 'billingInfo[Streetline1]']")
	WebElement address1Textbox;
	
	@FindBy(xpath= "//input[@name = 'billingInfo[Streetline2]']")
	WebElement address2Textbox;
		
	@FindBy(xpath= "//input[@name = 'billingInfo[Phone]']")
	WebElement phoneTextbox;
	
	@FindBy(xpath= "//input[@name = 'checkbox_shipping_same_as_billing']")
	WebElement sameShippingAddress;	
	
	@FindBy(xpath= "//div[@class = 'checkout_buttons']//input[@value='Continue Checkout'][@onclick = 'submitStep(1);']")
	WebElement continueCheckout1;
		
	@FindBy(xpath ="//div[@class = 'payment_method_name']")
	WebElement billingOption;	

	@FindBy(xpath ="//input[@value = 'BCE5D24D-666C-43CA-94A0-D6F775903BE2_2']")
	WebElement shippingOption;	
	
	@FindBy(xpath= "//div[@class = 'checkout_buttons']//input[@value='Continue Checkout'][@onclick = 'submitStep(2);']")
	WebElement continueCheckout2;
	
	@FindBy(xpath= "//input[@value = 'Place Order']")
	WebElement placeOrder;


	public CheckoutPage(WebDriver driver, String url) 
	{
		this.driver = driver;
		this.url=url;
		PageFactory.initElements(driver, this);
		
		wait= new WebDriverWait(driver, 10);  
	}

	@Override
	protected void load() 
	{
		driver.get(url);
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}

	public void click1() 
	{
	    JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");
        
//    	wait = new WebDriverWait(driver, 10);  
        wait.until(ExpectedConditions.elementToBeClickable(continueCheckout1));
		continueCheckout1.click();
	}
	
	public void selectBillingOption()
	{
//		wait = new WebDriverWait(driver, 10);  
        wait.until(ExpectedConditions.elementToBeClickable(billingOption));
		billingOption.click();
	}

	public void selectShippingOption() 
	{
//		wait = new WebDriverWait(driver, 10);  
        wait.until(ExpectedConditions.elementToBeClickable(shippingOption));
		shippingOption.click();	
	}

	public void click2() 
	{
	    wait.until(ExpectedConditions.elementToBeClickable(continueCheckout2));
		continueCheckout2.click();
	}

	public void placeOrder() 
	{
		wait= new WebDriverWait(driver, 10);  
		wait.until(ExpectedConditions.elementToBeClickable(placeOrder));
		placeOrder.click();	
	}

	
	//MEHTODS FOR UNREGISTERED USER
	
	public void inputEmail(String email) 
	{
		wait= new WebDriverWait(driver, 10);  
		wait.until(ExpectedConditions.elementToBeClickable(emailTextbox));
		emailTextbox.sendKeys(email);
	}

	public void inputFirstName(String firstName) 
	{
		firstnameTextbox.sendKeys(firstName);		
	}

	public void inputLastName(String lastName) 
	{
		lastnameTextbox.sendKeys(lastName);		
	}

	public void inputZip(String zip) 
	{
		zipcodeTextbox.sendKeys(zip);
	}

	public void inputCity(String city) 
	{
		cityTextbox.sendKeys(city);
	}

	public void inputAddress1(String address1) 
	{
		address1Textbox.sendKeys(address1);
	}

	public void inputAddress2(String address2) 
	{
		address2Textbox.sendKeys(address2);
	}

	public void inputPhone(String phone) 
	{
		phoneTextbox.sendKeys(phone);	
	}

	public void clickSameShippingAddress() 
	{
		sameShippingAddress.click();
	}

	public void verifyaddress(String address1, String address2) 
	{
		String enteredAddress1 = address1Textbox.getAttribute("value");
		String enteredAddress2 = address2Textbox.getAttribute("value");
	//	System.out.println(" ======= " +enteredAddress1 + "=======" + enteredAddress2);
		Assert.assertEquals(address1, enteredAddress1);
		Assert.assertEquals(address2, enteredAddress2);
	}

}
