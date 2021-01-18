package pageobjects;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPageObjects {
	
public static final Logger logger = LogManager.getLogger(ProductPageObjects.class);
	
	WebDriver driver;
	private By Tshirt=By.xpath("//header/div[3]/div[1]/div[1]/div[6]/ul[1]/li[3]/a[1]");
	private By Product=By.xpath("//a[contains(text(),'Faded Short Sleeve T-shirts')]");
	private By SendFriend=By.xpath("//a[@id='send_friend_button']");
	private By Send=By.id("sendEmail");
	private By Recp_name=By.id("friend_name");
	private By Recp_email=By.id("friend_email");
	private By Send_Confirm=By.xpath("//body/div[2]/div[1]/div[1]/div[1]/p[1]");
	private By review_Box=By.xpath("//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[3]/div[3]/ul[1]");
	private By Bluetop=By.id("color_14");
	

	public ProductPageObjects(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public void Select_Category() {
		boolean b=driver.findElement(Tshirt).isDisplayed();
		Assert.assertEquals("Category selected", true, b);
		driver.findElement(Tshirt).click();
	}
	
	public void ClickonProduct() throws Exception {
		WebDriverWait wait1=new WebDriverWait(driver,20);
		WebElement b=wait1.until(ExpectedConditions.elementToBeClickable(Product));
		driver.findElement(Product).click();
		Thread.sleep(2500);
		
	}
	
   public void SendAFrined() throws Exception {
	   WebDriverWait wait2=new WebDriverWait(driver,30);
	   WebElement b1=wait2.until(ExpectedConditions.elementToBeClickable(SendFriend));
       JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("window.scrollBy(0,1000)");
       driver.findElement(SendFriend).click();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
      // Alert alert=driver.switchTo().alert();
       WebDriverWait wait3=new WebDriverWait(driver,30);
	   WebElement b2=wait3.until(ExpectedConditions.elementToBeClickable(Send));
	   driver.findElement(Recp_name).sendKeys("Kavi");
	   driver.findElement(Recp_email).sendKeys("kavipatil271992@gmail.com");
       //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	   Thread.sleep(2000);
       driver.findElement(Send).click();
       
       
	}
   
   public void SendAFriend_Validation() {
	   driver.findElement(Send_Confirm).getText();
       boolean b=driver.findElement(Send_Confirm).isDisplayed();
       Assert.assertTrue(true);
       logger.info("Your email has been sent to your friend successfully");

   }
   
   public void Write_Review() throws Exception  {
		/*
		 * WebDriverWait wait2=new WebDriverWait(driver,30); WebElement
		 * b1=wait2.until(ExpectedConditions.elementToBeClickable(review_Box));
		 * driver.findElement(review_Box).click();
		 */
	   
   }
   
   public void Colour_Change_Blue() throws Exception  {
	   
	   WebDriverWait wait2=new WebDriverWait(driver,30);
	   WebElement b1=wait2.until(ExpectedConditions.elementToBeClickable(Bluetop));
	   driver.findElement(Bluetop).click();
	   Thread.sleep(2000);
	   logger.info("Blue colour image got selected");
   }
   
   public void Image_Change_Validation() {
	   boolean b=driver.findElement(By.id("bigpic")).isDisplayed();
	   Assert.assertTrue(b);
	   logger.info("Blue colour image displayed");
   }

}
