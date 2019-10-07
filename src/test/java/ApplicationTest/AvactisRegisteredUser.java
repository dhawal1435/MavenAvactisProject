package ApplicationTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Application.AdminDashboard;
import Application.AdminLoginPage;
import Application.CheckoutPage;
import Application.CustomerHomePage;
import Application.CustomerRegisterPage;
import Application.CustomerSignedIn;
import Application.OrderedPage;
import Application.ProductHomePage;
import Application.ProductSelectPage;
import Application.ProductVerifyPage;
import Application.Utilities;
import Application.adminCustomerPage;

public class AvactisRegisteredUser 
{
	WebDriver driver;
	
	String url;
	String filename;	
	String userName, emailId, address1,address2;
	String orderId,	finalPrice;
	 String actualPage, expectedPage;
	
	ProductSelectPage productSelect;
	ProductHomePage product;
	CustomerSignedIn custSignedIn;
	ProductVerifyPage productVerify;
	CheckoutPage checkout;
	AdminDashboard admin;
	
	static Logger log = Logger.getLogger(AvactisRegisteredUser.class); 

  @Parameters({ "browser" })	
  @BeforeClass
  public void init(String browser) 
  {
	  try  
	  { 
		  if(browser.equalsIgnoreCase("chrome"))
		  {
			  System.setProperty("webdriver.chrome.driver","src\\test\\resources\\chromedriver.exe");
			  driver = new ChromeDriver();
		  }
		  else if(browser.equalsIgnoreCase("firefox"))
		  {
			  System.setProperty("webdriver.gecko.driver","src\\test\\resources\\geckodriver-64bit.exe");
			  driver = new FirefoxDriver();
		  }
		  else if(browser.equalsIgnoreCase("ie"))
		  {
			  System.setProperty("webdriver.ie.driver", "src\\test\\resources\\IEDriverServer.exe");
			  driver = new InternetExplorerDriver();
		  }
		  PropertyConfigurator.configure("log4j.properties");
	  } 
	  catch (Exception e) 
	  {
		  e.printStackTrace();
	  }
  }

  @Test            
  public void CustomerHome() throws InterruptedException      
  {
	  try 
	  {
		  CustomerHomePage cust = new CustomerHomePage(driver);         //Create object as global
		  cust.get();
		  cust.clickOnRegister();
		  log.info("Inside CustomerHome");
	  }
	  catch(Exception e)
	  {
		  log.error("Exception occcured :" +e.getMessage());
	  }
  }

 
  @Test(dependsOnMethods ="CustomerHome", dataProviderClass = Utilities.class, dataProvider = "provideCustomerRegisterData")
  public void CustomerRegister(String email, String password, String rePassword, String firstName, String lastName, 
		                       String country, String state, String zip, String city, String address1, String address2, 
		                       String phone) throws InterruptedException, IOException
  {	 
	  expectedPage = "http://localhost/Avactis/register.php"; 
	  try
	  {
		  this.address1=address1;
		  this.address2=address2;
		  log.info("Inside CustomerRegisteration Form");
		  CustomerRegisterPage custReg = new CustomerRegisterPage(driver);
		  custReg.get();		  
		  custReg.inputEmail(email);
		  custReg.inputPassword(password);
		  custReg.inputrePassword(rePassword);
		  custReg.inputFirstName(firstName);
		  custReg.inputLastName(lastName);
		  custReg.inputCountry(country);
		  custReg.inputState(state);
		  custReg.inputZip(zip);
		  custReg.inputCity(city);
		  custReg.inputAddress1(address1);
		  custReg.inputAddress2(address2);
		  custReg.inputPhone(phone);
		  custReg.clickOnRegister();
		      
//		  if(!password.equals(rePassword)) 
//		  {
//			  assertEquals(actualPage,expectedPage);
//			  log.info("Passwords do not match, hence negative test case passed");
//		  }
		  
		  Thread.sleep(3000);
		  filename="Registeration";
	      Utilities.screenshot(filename, driver);      //TAKE SCREENSHOT AFTER CLICK ON REGISTER
	      
	      Thread.sleep(3000);
	      actualPage= driver.getCurrentUrl();
	      if(actualPage.equals(expectedPage))   
		  {
			  custReg.clearWebElements();              // CLEAR ALL TEXTBOXES FOR NEXT SET OF INPUTS
		  }		  
	  }                                                //END OF TRY
	  catch(Exception e)
	  {
		  log.error("Exception occcured :" +e.getMessage());
	  }
	  
	}                                                  //END OF METHOD CustomerRegister                       
	
  
//*********************************  PRODUCT PART ********************************************************************
  
  @Test(dependsOnMethods = "CustomerRegister", dataProviderClass = Utilities.class, dataProvider = "provideCustomerSignInData")
//  @Test(dataProviderClass = Utilities.class, dataProvider = "provideCustomerSignInData")      
  public void CustomerSignIn(String email, String password)
  {
	  userName = email;
	  try
	  {
		  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//        LOGOFF AND SIGNIN AGAIN		  
//		  CustomerHomePage cust = new CustomerHomePage(driver);
//		  cust.get();
//		  cust.doLogin(email, password);		  
	  }
	  catch(Exception e)
	  {
		  log.error("Exception occcured :" +e.getMessage());
	  }
  }
 
  @Test(dependsOnMethods = "CustomerSignIn")
  public void CustomerSignedInPage()
  {
	  try
	  {
		  custSignedIn = new CustomerSignedIn(driver);
		  custSignedIn.get();
		  custSignedIn.goToHome();		  
		  log.info("product home page logged in");
	  }
	  catch(Exception e)
	  {
		  log.error("Exception occcured :" +e.getMessage());
	  }	  
  }  
 
  @Test(dependsOnMethods = "CustomerSignedInPage",dataProviderClass = Utilities.class, dataProvider = "provideProductData")
  public void ProductHomePage(String productName, String productPrice, String url)
  {
	  try
	  {
		  ProductHomePage product = new ProductHomePage(driver);
		  product.get();
		  product.selectproduct(productName);	  
		 
		  ProductSelectPage productSelect = new ProductSelectPage(driver, url);  
		  productSelect.get();
		  productSelect.addInCart();
		  productSelect.goToHome();
		  log.info(productName + "product selected ");
		 // Assert.assertEquals(driver.getCurrentUrl(), url);
	  }	  
	  catch(Exception e)
	  {
		  log.error("Exception occcured :" +e.getMessage());
	  }
  }
  
  @Test(dependsOnMethods = "ProductHomePage")
  public void clickViewCart()
  {
	  try
	  {
		  ProductHomePage product = new ProductHomePage(driver);
		  product.get();
		  product.ViewCart();
		  log.info("Cart viewed");
	  }
	  catch(Exception e)
	  {
		  log.error("Exception occcured :" +e.getMessage());	  
	  }
  }
  
  @Test(dependsOnMethods = "clickViewCart", dataProviderClass = Utilities.class, dataProvider = "provideProductData")
  public void verifyProduct(String productName, String productPrice, String url)
  {
	  try
	  {
		  url="http://localhost/Avactis/cart.php";
		  ProductVerifyPage productVerify = new ProductVerifyPage(driver, url);
		  productVerify.get();
		  productVerify.verifyProductInCart(productName, productPrice);
		  log.info(productName + "product verified");
	  }
	  catch(Exception e)
	  {
		  log.error("Exception occcured :" +e.getMessage());
	  }	  
  }

  @Test(dependsOnMethods = "verifyProduct")
  public void clickCheckout()
  {
	  try
	  {
		  url="http://localhost/Avactis/cart.php";
		  productSelect = new ProductSelectPage(driver, url);
		  productSelect.get();
		  productSelect.clickcheckoutbutton();
		  log.info("checkout button clicked");
	  }
	  catch(Exception e)
	  {
		  log.error("Exception occcured :" +e.getMessage());
	  }
  }
  
  @Test(dependsOnMethods = "clickCheckout")
  public void clickOnContinueCheckout()
  {
	  try
	  {
		  url="http://localhost/Avactis/checkout.php";
		  CheckoutPage checkout = new CheckoutPage(driver, url);     
		  checkout.get();
		  checkout.click1();
		  checkout.verifyaddress(address1,address2);   //VERIFY ADDRESSES0
		  
     	  log.info("Continue checkout button clicked");
	  }
	  catch(Exception e)
	  {
		  log.error("Exception occcured :" +e.getMessage());
	  }	  
  }

  @Test(dependsOnMethods = "clickOnContinueCheckout")
  public void selectBillingOption()
  {
	  try
	  {
		  url="http://localhost/Avactis/checkout.php";
		  CheckoutPage checkout = new CheckoutPage(driver, url);
		  checkout.get();
		  checkout.selectBillingOption();
     	  log.info("Billing option selected");
	  }
	  catch(Exception e)
	  {
		  log.error("Exception occcured :" +e.getMessage());
	  }
  }
  
  @Test(dependsOnMethods = "selectBillingOption")
  public void selectShippingOption()
  {
	  try
	  {		  
		  url="http://localhost/Avactis/checkout.php";
		  CheckoutPage checkout = new CheckoutPage(driver, url);
		  checkout.get();
		  checkout.selectShippingOption();
		  log.info("Shipping option selected");
	  }
	  catch(Exception e)
	  {
		  log.error("Exception occcured :" +e.getMessage());
	  }
  }
   
  @Test(dependsOnMethods = "selectShippingOption")
  public void clickOnContinueCheckout2()
  {
	  try
	  {
		  url="http://localhost/Avactis/checkout.php";
		  CheckoutPage checkout = new CheckoutPage(driver, url);
		  checkout.get();
		  checkout.click2();
		  log.info("Continuecheckout clicked");
	  }
	  catch(Exception e)
	  {
		  log.error("Exception occcured :" +e.getMessage());
	  }	  
  }
 
  @Test(dependsOnMethods = "clickOnContinueCheckout2")
  public void clickPlaceOrder()
  {
	  try
	  {
		  url="http://localhost/Avactis/checkout.php";
		  CheckoutPage checkout = new CheckoutPage(driver, url);
		  checkout.get();
		  checkout.placeOrder();
		  log.info("Order placed ");
	  }
	  catch(Exception e)
	  {
		  log.error("Exception occcured :" +e.getMessage());
	  }
  }

  @Test(dependsOnMethods = "clickPlaceOrder")
  public void verifyOrder()
  {
  	  try
  	  {
  		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  		
  		 filename="OrderPlaced ";
	     Utilities.screenshot(filename, driver);
	     Thread.sleep(3000);
	     
  		 OrderedPage orderPage = new OrderedPage(driver);
  		 orderPage.get();
  		 
  		 finalPrice = orderPage.verifyPrice();	 
  		 orderId = orderPage.verifyOrderId();
  		 emailId = orderPage.verifyEmailId();
  		 log.info("Order details verified");	
  	  }
  	  catch(Exception e)
  	  {
  		log.error("Exception occcured :" +e.getMessage());  	  
  	  }
  }	  	

//////// ADMIN ACTIVITIES //////////////////////////////////////////////////////////////////////////////////

 @Test(dependsOnMethods ="verifyOrder", dataProviderClass = Utilities.class, dataProvider = "provideAdminLoginData")
 // @Test(dataProviderClass = Utilities.class, dataProvider = "provideAdminLoginData")
  public void adminLogin(String adminId, String cred)   
  {
	  expectedPage = "http://localhost/Avactis/avactis-system/admin/signin.php";
	  try
	  {
		  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		  url="http://localhost/Avactis/avactis-system/admin/signin.php";
		  AdminLoginPage newAdminPage = new AdminLoginPage(driver);
		  newAdminPage.get();
		  newAdminPage.doLogin(adminId, cred);
		  filename="AdminSignIn ";
	      Utilities.screenshot(filename, driver);
		  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	      actualPage= driver.getCurrentUrl();		  
		  
	      if(actualPage.equals(expectedPage))   
		  {
	    	  newAdminPage.clearWebElements();              // CLEAR ALL TEXTBOXES FOR NEXT SET OF INPUTS
		  }
	  }
	  catch(Exception e)
	  {
		  log.error("Exception occcured :" +e.getMessage());
	  }	  
  }
  
  @Test(dependsOnMethods = "adminLogin")
  public void clickOnCustomer()
  {
	  try
	  {
		  log.info("logged in with admin");
		  filename="AdminRegisteration";
	      Utilities.screenshot(filename, driver);
	      Thread.sleep(3000);
	      
		  admin = new AdminDashboard(driver);
		  admin.get();
		  admin.clickCustomer();		
		  log.info("Customer option clicked (in Admin)");  
	  }
	  catch(Exception e)
	  {
		  log.error("Exception occcured :" +e.getMessage());	
	  }
  }
  
 ///email id and price during login and after placing order 
  @Test(dependsOnMethods = "clickOnCustomer")
  public void checkDetails()
  {
	  try
	  {
		  adminCustomerPage adminCust = new adminCustomerPage(driver);
		  adminCust.get();
		  adminCust.findCustomerName(orderId, emailId,finalPrice);
		  
		  filename="AdminList";
	      Utilities.screenshot(filename, driver);                              //SCREENSHOT FOR CUSTOMER DETAILS IN ADMIN MODULE
	      log.info("Order details verified from Admin module ");
	  }
	  catch(Exception e)
	  {
		  log.error("Exception occcured :" +e.getMessage());
	  }
  }
  
 
}         //END OF CLASS  AvactisRegisteredUser
