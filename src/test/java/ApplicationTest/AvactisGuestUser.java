 package ApplicationTest;

import org.testng.annotations.Test;

import Application.AdminDashboard;
import Application.AdminLoginPage;
import Application.CheckoutPage;
import Application.CustomerHomePage;
import Application.CustomerSignedIn;
import Application.OrderedPage;
import Application.ProductHomePage;
import Application.ProductSelectPage;
import Application.ProductVerifyPage;
import Application.Utilities;
import Application.adminCustomerPage;

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
 
public class AvactisGuestUser 
{
	WebDriver driver;
	
	String url;
	String filename;
	String userName, emailId, address1;
	String orderId, finalPrice;
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
			  log.error("Exception occcured :" +e.getMessage());
		  }
	  }

  @Test            
  public void customerHome() throws InterruptedException      
  {
	  try
	  {
		  CustomerHomePage cust = new CustomerHomePage(driver);     
		  cust.get();
		  cust.gotoHomePage();
		  log.info("product home page browsed");
	  }
	  catch(Exception e)
	  {
		  log.error("Exception occcured :" +e.getMessage());
	  }
  }

  ////////////////////////////////////////////////PRODUCT PART ////////////////////////////////////////////////////////////////////
   
  @Test(dependsOnMethods = "customerHome",dataProviderClass = Utilities.class, dataProvider = "provideProductData")
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
  
  @Test(dependsOnMethods ="clickCheckout", dataProviderClass = Utilities.class, dataProvider = "provideGuestUserData")
  public void guestCustomerDetails(String firstName, String lastName, String email, String country, String zip, String state, String city, 
		                           String address1, String address2, String phone) throws InterruptedException, IOException
  {
	  try
	  {
		  emailId = email;
		  String url = "http://localhost/Avactis/checkout.php";
		  checkout = new CheckoutPage(driver, url);
		  checkout.get();		  
		  checkout.inputEmail(email);
		  
		  checkout.inputFirstName(firstName);
		  checkout.inputLastName(lastName);
		  checkout.inputZip(zip);
		  checkout.inputCity(city);
		  checkout.inputAddress1(address1);
		  checkout.inputAddress2(address2);
		  checkout.inputPhone(phone);
		  checkout.clickSameShippingAddress();
		  
		  filename="GuestDetails ";
	      Utilities.screenshot(filename, driver);                                //SCREENSHOT AFTER GUEST DETAILS ENTERED
	      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	      log.info("Guest details entered");
	  }                                                                           //END OF TRY
	  catch(Exception e)
	  {
		  log.error("Exception occcured :" +e.getMessage());
	  }	  
  }
 
  @Test(dependsOnMethods = "guestCustomerDetails")
  public void clickOnContinueCheckout()
  {
	  try
	  {
//		  Thread.sleep(3000);
		  url="http://localhost/Avactis/checkout.php";
		  CheckoutPage checkout = new CheckoutPage(driver, url);
		  checkout.get();
		  
		  checkout.click1();  
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
 
  @Test(dependsOnMethods = "selectShippingOption")
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

//////// ADDMIN ACTIVITIES //////////////////////////////////////////////////////////////////////////////////

  @Test(dependsOnMethods ="verifyOrder", dataProviderClass = Utilities.class, dataProvider = "provideAdminLoginData")  
  //@Test(dataProviderClass = Utilities.class, dataProvider = "provideAdminLoginData")  	 
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

  @Test(dependsOnMethods = "clickOnCustomer")
  public void checkDetails()
  {
	  try
	  {
		  adminCustomerPage adminCust = new adminCustomerPage(driver);
		  adminCust.get();
		  adminCust.findCustomerName(orderId, emailId,finalPrice);
		  
		  filename="AdminList";
	      Utilities.screenshot(filename, driver);                                 //SCREENSHOT FOR CUSTOMER DETAILS IN ADMIN MODULE
	      log.info("Order details verified from Admin module ");
	  }
	  catch(Exception e)
	  {
		  log.error("Exception occcured :" +e.getMessage());
	  }
  }
  

}                    //END OF CLASS AvacticGuestUser
