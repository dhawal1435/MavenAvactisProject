package Application;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestAvactis 
{
	WebDriver driver;
	
	String url;
	
	String dataname;
	String CustRegfilename;
	
	ProductSelectPage productselect;
	ProductHomePage product;
	CustomerSignedIn custSignedIn;
	ProductVerifyPage productverify;
	CheckoutPage checkout;
	
	static Logger log = Logger.getLogger(TestAvactis.class); 
	  
  @BeforeClass
  public void init() 
  {
	  System.setProperty("webdriver.gecko.driver","src\\test\\resources\\geckodriver-64bit.exe");
	  driver = new FirefoxDriver();
//	  System.setProperty("webdriver.chrome.driver","src\\test\\resources\\chromedriver.exe");
//      driver = new ChromeDriver();
  }
  
//  @Test            
//  public void CustomerHome() throws InterruptedException      
//  {
//	  try
//	  {
//		  log.info("Inside CustomerHome");
//		  Thread.sleep(3000);
//		  CustomerHomePage cust = new CustomerHomePage(driver);         //Create object as global
//		  cust.get();
//		  cust.clickOnRegister();
//	  }
//	  catch(Exception e)
//	  {
//		  log.error("In method Customer Home  " + e);
//	  }
//  }
  

//  @Test(dependsOnMethods ="CustomerHome", dataProviderClass = Utilities.class, dataProvider = "provideDataFromCsv1")
//  public void CustomerRegister(String email, String password, String rePassword, String firstName, String lastName, String country, String state, String zip, String city, String address1, String address2, String phone) throws InterruptedException, IOException 
//  {
//	  try
//	  {
//		  Thread.sleep(3000);
//		  CustomerRegisterPage custReg = new CustomerRegisterPage(driver);
//		  custReg.get();
//		  
//		  custReg.inputEmail(email);
//		  custReg.inputPassword(password);
//		  custReg.inputrePassword(rePassword);
//		  custReg.inputFirstName(firstName);
//		  custReg.inputLastName(lastName);
//		  custReg.inputZip(zip);
//		  custReg.inputCity(city);
//		  custReg.inputAddress1(address1);
//		  custReg.inputAddress2(address2);
//		  custReg.inputPhone(phone);
//		  custReg.clickOnRegister();
//		  
//		  CustRegfilename="Registeration";
//	      custReg.screenshot(CustRegfilename);
//		  custReg.waitForClear();
//		  System.out.println(driver.getTitle());
//		  WebElement A1 = driver.findElement(By.xpath("/html/body/div[4]/div/div/div/div/div/div/div/h1"));
//		  if(A1.isDisplayed())
//		  {
//				custReg.clearWebElements();
//		  }
//		  
//	  }             //END OF TRY
//	  catch(Exception e)
//	  {
//		  System.out.println("in method CustomerRegister  " + e);
//	  }
//	  
//	}

//02  @Test(dependsOnMethods = "CustomerRegister",dataProviderClass = Utilities.class, dataProvider = "provideDataFromCsv2")
  
 
  
  
  ////////////////////////////////////////////////PRODUCT PART ////////////////////////////////////////////////////////////////////
  
  
  
 
 // @Test(dataProviderClass = Utilities.class, dataProvider = "provideDataFromCsv2")        //02
  
//  public void CustomerSignIn(String email, String password)
  @Test
  public void CustomerSignIn()
  {
	  try
	  {
		  Thread.sleep(3000);
		  CustomerHomePage cust = new CustomerHomePage(driver);
		  cust.get();
		  String email = "test1@testsite.com";
		  cust.doLoginemail(email);
//		  cust.doLogin(email, password);		  
	  }
	  catch(Exception e)
	  {
		  System.out.println("in method CustomerSignIn  " + e);
	  }
  }
 
 /* 
  @Test
  public void CustomerSignedInPage()
  {
	  try
	  {
		  Thread.sleep(3000);
		  custSignedIn = new CustomerSignedIn(driver);
		  custSignedIn.get();
		  custSignedIn.goToHome();		  
	  }
	  catch(Exception e)
	  {
		  System.out.println("in method CustomerSignedInPage  " + e);
	  }	  
  }  
 /*
  @Test(dependsOnMethods = "CustomerSignedInPage")
  public void ProductHomePage()
  {
	  try
	  {
		  Thread.sleep(3000);
		  product = new ProductHomePage(driver);
		  product.get();
		  product.selectFirstProduct();		  
	  }
	  catch(Exception e)
	  {
		  System.out.println("in method ProductHomePage  " + e);
	  }
  }
  
  @Test(dependsOnMethods = "ProductHomePage")
  public void addProduct()
  {
	  try
	  {
		  Thread.sleep(3000);
		  url="http://localhost/Avactis/product-info.php?HP_TouchSmart-pid31.html";
		  productselect = new ProductSelectPage(driver, url);
		  productselect.get();
		  productselect.addInCart();
		  productselect.goToHome();
		  //custSignedIn.goToHome();	
	  }
	  catch(Exception e)
	  {
		  System.out.println("in method addProduct  " + e);
	  }
  }

  //		  url ="http://localhost/Avactis/product-info.php?EKTORP_Neckroll-pid169.html";

  
//// SECOND PRODUCT METHODS   /////////////////////////////////////////////////////  
  @Test(dependsOnMethods = "addProduct")
  public void ProductHomePage1()
  {
	  try
	  {
		  Thread.sleep(3000);
		  product = new ProductHomePage(driver);
		  product.get();
		  product.selectSecondProduct();		  
	  }
	  catch(Exception e)
	  {
		  System.out.println("in method ProductHomePage1  " + e);
	  }
  }
  
  @Test(dependsOnMethods = "ProductHomePage1")
  public void addSecondProduct()
  {
	  try
	  {
//		  Thread.sleep(3000);
//		  ProductSelectPage2 productselect = new ProductSelectPage2(driver);
//		  productselect.get();
//		  productselect.addInCart();
//		  productselect.goToHome();
//		  //custSignedIn.goToHome();
		  
		  Thread.sleep(3000);
		  url="http://localhost/Avactis/product-info.php?Custom_T-Shirt_-_Upload_your_own_design-pid35.html";
		  ProductSelectPage productselect2 = new ProductSelectPage(driver, url);
		  productselect2.get();
		  productselect2.addInCart();
		  productselect2.goToHome();
	  }
	  catch(Exception e)
	  {
		  System.out.println("in method addSecondProduct  " + e);
	  }
  }

  
////THIRD PRODUCT METHODS   /////////////////////////////////////////////////////
  @Test(dependsOnMethods = "addSecondProduct")
  public void ProductHomePage2()
  {
	  try
	  {
		  Thread.sleep(3000);
		  product = new ProductHomePage(driver);
		  product.get();
		  product.selectThirdProduct();		  
	  }
	  catch(Exception e)
	  {
		  System.out.println("in method ProductHomePage2  " + e);
	  }
  }
  
  @Test(dependsOnMethods = "ProductHomePage2")
  public void addThirdProduct()
  {
	  try
	  {
		  Thread.sleep(3000);
		  url="http://localhost/Avactis/product-info.php?EKTORP_Neckroll-pid169.html";
		  ProductSelectPage productselect3 = new ProductSelectPage(driver, url);
		  productselect3.get();
		  productselect3.addInCart();
		  productselect3.clickViewCart();		  
	  }
	  catch(Exception e)
	  {
		  System.out.println("in method addThirdProduct  " + e);
	  }
  }
  
  @Test(dependsOnMethods = "addThirdProduct",dataProviderClass = Utilities.class, dataProvider = "provideDataFromCsv5")
  public void verifyProduct(String productName, String price)
  {
	  try
	  {
		  Thread.sleep(3000);
		  url="http://localhost/Avactis/cart.php";
		  ProductVerifyPage productverify = new ProductVerifyPage(driver, url);
		  productverify.get();
		  
		  productverify.verifyProductInCart(productname, price);
		  
//		  productname = "HP TouchSmart";
//		  price ="$799.99";
//		  productverify.verifyfirstproduct(productname, price);
//				  
//		  productname = "Custom T-Shirt (Upload your own design!)";
//		  price ="$25.99";
//		  productverify.verifysecondproduct(productname, price);
//			  
//		  productname = "EKTORP Neckroll";
//		  price ="$9.99";
//		  productverify.verifythirdproduct(productname, price);
		  
		  
	  }
	  catch(Exception e)
	  {
		  System.out.println("In Method verifyProduct  " + e);
	  }
	  
  }
  
  @Test(dependsOnMethods = "verifyProduct")
  public void clickCheckout()
  {
	  try
	  {
		  Thread.sleep(3000);
		  url="http://localhost/Avactis/product-info.php?EKTORP_Neckroll-pid169.html";
		  productselect = new ProductSelectPage(driver, url);
		  productselect.get();
		  productselect.clickcheckoutbutton();

	  }
	  catch(Exception e)
	  {
		  System.out.println("in Mehtod clickCheckout  " +e);
	  }
  }
  
  //http://localhost/Avactis/checkout.php
  
  //VERIFY ADDRESS
  
  
  @Test(dependsOnMethods = "clickCheckout")
  public void clickOnContinueCheckout()
  {
	  try
	  {
		  Thread.sleep(3000);
		  url="http://localhost/Avactis/checkout.php";
		  CheckoutPage checkout = new CheckoutPage(driver, url);
		  checkout.get();
		  checkout.click1();
	  }
	  catch(Exception e)
	  {
		  System.out.println("in Mehtod clickContinueCheckout  " +e);
	  }
	  
  }
 

  @Test(dependsOnMethods = "clickOnContinueCheckout")
  public void selectBillingOption()
  {
	  try
	  {
		  Thread.sleep(3000);
		  url="http://localhost/Avactis/checkout.php";
		  CheckoutPage checkout = new CheckoutPage(driver, url);
		  //checkout.get();
		  checkout.selectBillingOption();
	  }
	  catch(Exception e)
	  {
		  System.out.println("in Mehtod selectBillingOption  " +e);
	  }
	  
  }
  
  @Test(dependsOnMethods = "selectBillingOption")
  public void selectShippingOption()
  {
	  try
	  {
		  Thread.sleep(3000);
		  url="http://localhost/Avactis/checkout.php";
		  CheckoutPage checkout = new CheckoutPage(driver, url);
		  //checkout.get();
		  checkout.selectShippingOption();
	  }
	  catch(Exception e)
	  {
		  System.out.println("in Mehtod selectBillingOption  " +e);
	  }
	  
  }
 
  
  @Test(dependsOnMethods = "selectShippingOption")
  public void clickOnContinueCheckout2()
  {
	  try
	  {
		  Thread.sleep(3000);
		  url="http://localhost/Avactis/checkout.php";
		  CheckoutPage checkout = new CheckoutPage(driver, url);
		  //checkout.get();
		  checkout.click2();
	  }
	  catch(Exception e)
	  {
		  System.out.println("in Mehtod clickContinueCheckout  " +e);
	  }
	  
  }
 
  @Test(dependsOnMethods = "selectShippingOption")
  public void clickPlaceOrder()
  {
	  try
	  {
		  Thread.sleep(3000);
		  url="http://localhost/Avactis/checkout.php";
		  CheckoutPage checkout = new CheckoutPage(driver, url);
		  //checkout.get();
		  checkout.placeOrder();
	  }
	  catch(Exception e)
	  {
		  System.out.println("in Mehtod clickContinueCheckout  " +e);
	  }
	  
  }
  
  
//02  @Test(dependsOnMethods = "clickPlaceOrder", dataProviderClass = Utilities.class , dataProvider = "provideDataFromCsv")
//02  public void adminLogin(String adminId, String cred)
  
  @Test(dependsOnMethods = "clickPlaceOrder", dataProviderClass = Utilities.class, dataProvider = "provideDataFromCsv")         
  public void AdminLogin(String adminusername, String password) throws InterruptedException      
  {
	  try
	  {
		  Thread.sleep(3000);
		  url="http://localhost/avactis/avactis-system/admin/signin.php";
		  AdminLoginPage admin = new AdminLoginPage(driver);         //Create object as global
		  admin.get();
		  admin.doLogin(adminusername, password);
	  }
	  catch(Exception e)
	  {
		  System.out.println("In method Customer Home  " + e);
	  }
  }
    
*/ 
}         //END OF CLASS
