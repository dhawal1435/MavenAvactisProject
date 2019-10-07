package Application;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class adminCustomerPage extends LoadableComponent<adminCustomerPage> 
{
	WebDriver driver;
	String url ="http://localhost/Avactis/avactis-system/admin/customers.php";
	
	@FindBy(xpath = "//div[@class = 'table-responsive']/table/tbody")
	WebElement customerTable;
	public adminCustomerPage(WebDriver driver) 
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
	 
	public void findCustomerName(String orderId, String emailId, String price) 
	{
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		//WebElement A = driver.findElement(LocatorHelper.getLocator(prop.getProperty(dataname)));
		List<WebElement> rows;
		List<WebElement> columns;
		int a=0;
		rows = customerTable.findElements(By.tagName("tr")); 
		int rowCount = rows.size();
		columns = customerTable.findElements(By.tagName("td"));
		//int columnCount = columns.size();
		
		String email ="";   //for printing purpose
		
		for(int i=0;i<rowCount;i++)
		{
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

			WebElement row = rows.get(i);
			List<WebElement> colsInRow  = row.findElements(By.tagName("td"));
			
			for (WebElement e : colsInRow)
			{
				if(e.getText().equals(emailId))
				{
					a=1;
					email = e.getText();
					System.out.println(email);
				}
				if(e.getText().equals(price))
				{
					a=3;                                             //TO COME OUT FOR OUTER LOOP
					System.out.println(a);
					System.out.println(e.getText());
					break;
				}
				
			}      //END OF INNER FOR
	
			if(a==3)                                                //TO COME OUT FOR INNER LOOP
			{
				System.out.println(email + " email id found with ordered value");
				break;
			}
		}          //END OF OUTER FOR

	}
}
