package Application;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;
//import avactis.MavenAvactisProject.LocatorHelper;

public class AdminDashboard extends LoadableComponent<AdminDashboard> 
{
	WebDriver driver;
	String url = "http://localhost/Avactis/avactis-system/admin/index.php";
	
	@FindBy(xpath = "//a[@class = 'menu-toggler responsive-toggler']")
	WebElement menu;
	
	@FindBy(xpath ="//a[@href = 'users.php']/span[@class = 'title'][contains(text(),'Customers')]")
	WebElement customers;               //FOR MOUSEHOVER
	
	@FindBy(xpath = "//a[@href = 'customers.php']/span[@class = 'title'][contains(text(),'Customers')]")
	WebElement customerClick;
	
	@FindBy(xpath = "//div[@class = 'table-responsive']/table/tbody")
	WebElement customerTable;
	
	public AdminDashboard(WebDriver driver) 
	{
		this.driver=driver;
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

	public void clickCustomer() 
	{
		 Actions builder = new Actions(driver);
	     builder.moveToElement(customers).build().perform();	      
	     driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	     
    	 WebDriverWait wait = new WebDriverWait(driver, 10);  
         wait.until(ExpectedConditions.elementToBeClickable(customerClick));
	     customerClick.click();	
	     
	    driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	     JavascriptExecutor js = (JavascriptExecutor) driver;
	     js.executeScript("window.scrollBy(0,500)");
	}


		
	

}               //END OF CLASS
