package Application;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class ProductVerifyPage extends LoadableComponent<ProductVerifyPage> 
{
	WebDriver driver;
	String url;
	@FindBy(xpath= "//li//a[@href = 'http://localhost/Avactis/product-info.php?HP_TouchSmart-pid31.html'][contains(text(),'HP TouchSmart')]")
	WebElement HPTouchSmart;
	
	@FindBy(xpath= "//select[@name = 'quantity_in_cart[0]']")
	WebElement firstProductQuantity;
	
	@FindBy(xpath = "//li//*[contains(text(),'Custom T-Shirt (Upload your own design!)')]")
	WebElement Tshirt;
	
	@FindBy(xpath ="//select[@name = 'quantity_in_cart[1]']")
	WebElement secondProductQuantity;
	
	@FindBy(xpath = "//li//*[contains(text(), 'EKTORP Neckroll')]")
	WebElement EktorpNeckroll;
	
	@FindBy(xpath ="//select[@name = 'quantity_in_cart[2]']")
	WebElement thirdProductQuantity;
		
	
	public ProductVerifyPage(WebDriver driver, String url) 
	{
		this.driver = driver;
		this.url=url;
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

	public void verifyProductInCart(String productName, String pricevalue) 
	{
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebElement product = driver.findElement(By.xpath("//h3/a[contains(text(),'"+productName+"')]"));
		Assert.assertEquals(product.isDisplayed(),true);
		
		WebElement price = driver.findElement(By.xpath("//td[@class='goods-page-price']//strong[contains(text(),'"+pricevalue+"')]"));
		Assert.assertEquals(price.isDisplayed(),true);
		
	}

//	public void verifyfirstproduct(String productname, String price) 
//	{
//		String A = HPTouchSmart;
//		System.out.println("product  "+ A);
//		System.out.println("Text  ====  " + HPTouchSmart.getText());
//		//Assert.assertEquals(HPTouchSmart.getText(), price);
//		//HPTouchSmart.get
//		HPTouchSmart.click();
//	}

//	public void verifysecondproduct(String productname, String price) 
//	{
//		Assert.assertEquals(Tshirt.getText(),productname);
//		Assert.assertEquals(Tshirt.getText(), price);
//	}

//	public void verifythirdproduct(String productname, String price) 
//	{
//		Assert.assertEquals(EktorpNeckroll.getText(),productname);
//		Assert.assertEquals(EktorpNeckroll.getText(), price);
//	}
	
}     //END OF CLASS
