package Application;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class AdminLoginPage extends LoadableComponent<AdminLoginPage>{
			
		WebDriver driver;
		
		@FindBy(xpath= "//input[@class = 'form-control placeholder-no-fix'][@name = 'AdminEmail']")
		WebElement userName;
			
		@FindBy(xpath ="//input[@class = 'form-control placeholder-no-fix'][@name = 'Password']")
		WebElement password;	

		@FindBy(xpath ="//button[@class = 'btn blue pull-right']")
		WebElement login;	
		
		public AdminLoginPage(WebDriver driver) 
		{
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		public void doLogin(String username, String creds) 
		{	
			userName.sendKeys(username);
			password.sendKeys(creds);
			login.click();
			
		}
		
		@Override
		protected void load() 
		{
			//driver.get("http://localhost/Avactis/sign-in.php");
			driver.get("http://localhost/avactis/avactis-system/admin/signin.php");	
		}

		@Override
		protected void isLoaded() throws Error 
		{
			assertEquals("Avactis Shopping Cart", driver.getTitle());	
		}

		public void clearWebElements() 
		{
			userName.clear();
			password.clear();
		}

}
