package Application;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerHomePage extends LoadableComponent<CustomerHomePage> 
{

	static Logger log = Logger.getLogger(CustomerHomePage.class);
	
	WebDriver driver;
	String url = "http://localhost/Avactis/sign-in.php";
	
	@FindBy(xpath= "//input[@id= 'account_sign_in_form_email_id']")
	WebElement userName;
	
	@FindBy(xpath= "//input[@id= 'account_sign_in_form_passwd_id']")
	WebElement password;
	
	@FindBy(xpath= "//input[@class = 'btn btn-primary input_submit']")
	WebElement login;
	
	@FindBy(xpath= "//button[@class= 'btn btn-default']")
	WebElement clickRegister;
	
	@FindBy(xpath = "//a[@href='http://localhost/Avactis/index.php']")
	WebElement goToHomePage;
	
	public CustomerHomePage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void doLogin(String username, String creds) 
	{	
		log.info("Inside doLogin");
		userName.sendKeys(username);
		password.sendKeys(creds);
		login.click();
		
	}
	
	public void clickOnRegister() 
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);  //OBJECT IS CREATED
        wait.until(ExpectedConditions.urlToBe(url));
		clickRegister.click();		
	}
	
	@Override
	protected void load() 
	{
		driver.get(url);
	}

	@Override
	protected void isLoaded() throws Error 
	{
		assertEquals("Avactis Demo Store", driver.getTitle());	
	}

	public void gotoHomePage() 
	{
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		goToHomePage.click();
	}

	public void doLoginemail(String email) 
	{
		userName.sendKeys(email);
		
		String A = userName.getAttribute("value");
		System.out.println("  printable   "+A);
	}

}
