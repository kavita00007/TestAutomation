package pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.junit.Assert;

public class BuyProcess {
	public static final Logger logger = LogManager.getLogger(BuyProcess.class);
	WebDriver driver;
	private By Product_Price=By.xpath("//span[@id='our_price_display']");
	private By Quantity=By.xpath("//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[4]/form[1]/div[1]/div[2]/p[1]/a[2]/span[1]/i[1]");
	private By Addedtocart=By.xpath("//p[@id='add_to_cart']");
	private By total=By.id("layer_cart_product_price");
	private By Size_Colour=By.xpath("//span[@id='layer_cart_product_attributes']");
	private By quantity=By.xpath("//span[@id='layer_cart_product_quantity']");
	private By Checkout=By.linkText("Proceed to checkout");
	private By ProceedToCheckout=By.linkText("Proceed to checkout");
	private By AgainCheckout=By.xpath("//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/form[1]/p[1]/button[1]/span[1]");
	private By Checkbox=By.id("uniform-cgv");
	private By Check_Out=By.xpath("//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/div[1]/form[1]/p[1]/button[1]/span[1]");
	private By PaymentMethod=By.className("bankwire");
	private By Confirm_Order=By.xpath("//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/form[1]/p[1]/button[1]/span[1]");
	private By Order_success=By.xpath("//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/div[1]/p[1]/strong[1]");
	private By Stock=By.xpath("//tbody/tr[@id='product_1_2_0_431931']/td[3]/span[1]");
	private By Product_Name=By.linkText("Faded Short Sleeve T-shirts");
	
	
	public BuyProcess(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
public void Get_Product_Price() {
	
	String price=driver.findElement(Product_Price).getText();
	logger.info("Price of product");
	System.out.println("Price od product is" +price );
	
}

public void Quantity_Change() {
	WebDriverWait wait=new WebDriverWait(driver,20);
	WebElement b=wait.until(ExpectedConditions.elementToBeClickable(Quantity));
	driver.findElement(Quantity).click();
	logger.info("Quantity of product has been changed to 2");
}

public void Size_Change() throws Exception {
	Select size=new Select(driver.findElement(By.id("group_1")));
    size.selectByVisibleText("L");
	logger.info("Size of product has been changed to L");
    Thread.sleep(2000);
	
}

public void Add_To_Cart() {
	driver.findElement(By.id("add_to_cart")).click();
	logger.info("Product added to cart");
}

public void Added_Cart_Validation() {
	boolean b=driver.findElement(Addedtocart).isDisplayed();
	Assert.assertEquals("Added successfully", true, b);
    logger.info("Product added to cart successfully");
}

public void Total_Price_Validation() {
	
}

public void Size_Quntity_Confirmation() {
	   boolean b=driver.findElement(Size_Colour).isDisplayed();
	   Assert.assertEquals("Colour and size mentioned", true, b);
	   
	   boolean b1=driver.findElement(quantity).isDisplayed();
	   Assert.assertEquals("Quanticty checked", true, b1);
	   
	   logger.info("Quntity and colour displayed correctly");
}

public void Checkout_After_AddedToCart() throws Exception {
	WebDriverWait wait=new WebDriverWait(driver,20);
	WebElement b=wait.until(ExpectedConditions.elementToBeClickable(Checkout));
	driver.findElement(Checkout).click();
	Thread.sleep(2000);
	logger.info("Clicked on checkout to proceed further");
	
	
}

public void Procedd_To_Checkout() throws Exception {
	JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollBy(0,1000)");
    driver.findElement(ProceedToCheckout).click();
    logger.info("Checkout for address window");
    Thread.sleep(2000);
    
    JavascriptExecutor js1 = (JavascriptExecutor) driver;
    js1.executeScript("window.scrollBy(0,1000)");
    driver.findElement(AgainCheckout).click();
    logger.info("Checkout to proceed with shipping");
    Thread.sleep(2000);
    
    WebElement checkbox=driver.findElement(Checkbox);
    checkbox.click();
    logger.info("Terms and conditions accepted");
    
    driver.findElement(Check_Out).click();
    logger.info("Checkout to proceed with payment");
}

public void Checkout_Validations()  {
	boolean b=driver.findElement(Product_Name).isDisplayed();
	Assert.assertEquals("Product name is correct", true, b);
	logger.info("Product name is displayed");
	String expected="In stock";
	String actual=driver.findElement(Stock).getText();
	Assert.assertEquals("Product is in stock", expected, actual);
	logger.info("Product is available in stock");
}


public void Payment_Process() throws InterruptedException {
	driver.findElement(PaymentMethod).click();
    logger.info("Paymnet method has been selected");
    Thread.sleep(1500);
    driver.findElement(Confirm_Order).click();
    logger.info("I have confirmed order");
    
}

public void Order_Confirm() {
	WebDriverWait wait=new WebDriverWait(driver,20);
	WebElement b=wait.until(ExpectedConditions.presenceOfElementLocated(Order_success));
	String actual=driver.findElement(Order_success).getText();
	String expected="Your order on My Store is complete.";
	Assert.assertEquals("Order has been placed successfully", expected, actual);
	logger.info("Your order has been placed successfully");
}
}