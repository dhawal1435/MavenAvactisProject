package Application;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.io.Files;


public class CustomerRegisterPage extends LoadableComponent<CustomerRegisterPage> 
{
	WebDriver driver;
	String url = "http://localhost/Avactis/register.php";
	
	@FindBy(xpath= "//input[@class = 'form-control input_text'][@name = 'customer_info[Customer][Email]']")
	WebElement emailTextbox;
	
	@FindBy(xpath= "//input[@class = 'form-control input_password'][@name = 'customer_info[Customer][Password]']")
	WebElement passwordTextbox;
	
	@FindBy(xpath= "//input[@class = 'form-control input_password'][@name = 'customer_info[Customer][RePassword]']")
	WebElement rePasswordTextbox;
	
	@FindBy(xpath= "//input[@class = 'form-control input_text'][@name = 'customer_info[Customer][FirstName]']")
	WebElement firstnameTextbox;
	
	@FindBy(xpath= "//input[@class = 'form-control input_text'][@name = 'customer_info[Customer][LastName]']")
	WebElement lastnameTextbox;
	
	@FindBy(xpath= "//select[@name = 'customer_info[Customer][Country]']")
	WebElement countryTextbox;
	
	@FindBy(xpath= "//select[@name = 'customer_info[Customer][State]']")
	WebElement stateTextbox;
	
	@FindBy(xpath = "//input[@class = 'form-control input_text'][@name = 'customer_info[Customer][ZipCode]']")
	WebElement zipTextbox;
	
	@FindBy(xpath = "//input[@name = 'customer_info[Customer][City]']")
	WebElement cityTextbox;
	
	@FindBy(xpath = "//input[@class = 'form-control input_text'][@name = 'customer_info[Customer][Streetline1]']")
	WebElement address1Textbox;
	
	@FindBy(xpath = "//input[@class = 'form-control input_text'][@name = 'customer_info[Customer][Streetline2]']")
	WebElement address2Textbox;
	
	@FindBy(xpath ="//input[@class = 'form-control input_text'][@name = 'customer_info[Customer][Phone]']")
	WebElement phoneTextbox;
	   
	@FindBy(xpath = "//input[@value = 'Register']")
	WebElement registerButton;
	
	
	public CustomerRegisterPage(WebDriver driver) 
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


	public void inputEmail(String email) 
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);  
        wait.until(ExpectedConditions.urlToBe(url));
		emailTextbox.sendKeys(email);
	}

	public void inputPassword(String password) 
	{
		passwordTextbox.sendKeys(password);
	}
	
	public void inputrePassword(String rePassword) 
	{
		rePasswordTextbox.sendKeys(rePassword);
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
		zipTextbox.sendKeys(zip);
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

	public void clickOnRegister() 
	{
		registerButton.click();
	}

	public void screenshot(String file) throws IOException 
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy h-m-s");
        Date date = new Date();
		TakesScreenshot capture = (TakesScreenshot) driver;
        File scrFile = capture.getScreenshotAs(OutputType.FILE);
        String filename = file;
        Files.copy(scrFile, new File("D:\\"+filename+"-"+dateFormat.format(date)+".png"));		
		
	}

//	public void waitForClear() throws InterruptedException 
//	{
//		Thread.sleep(3000);	
//	}

	public void clearWebElements() 
	{
		if(emailTextbox.isDisplayed())
		{
			emailTextbox.clear();
			passwordTextbox.clear();
			rePasswordTextbox.clear();
			firstnameTextbox.clear();
			lastnameTextbox.clear();
			zipTextbox.clear();
			cityTextbox.clear();
			address1Textbox.clear();
			address2Textbox.clear();
			phoneTextbox.clear();
//			countryTextbox.clear();
//			stateTextbox.clear();
		}
	}

	public void inputCountry(String country) 
	{
		WebElement countryCode = driver.findElement(By.xpath("//option[contains(text(),'"+country+"')]"));
        countryCode.click();
    }
        	
	public void inputState(String state) 
	{
		WebElement stateCode = driver.findElement(By.xpath("//option[contains(text(),'"+state+"')]"));
        stateCode.click();
		
	}



}
