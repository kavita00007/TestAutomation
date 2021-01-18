package pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class HomePageObjects {
	
public static final Logger logger = LogManager.getLogger(HomePageObjects.class);

WebDriver driver;
private By SignUp=By.className("login");
private By Loginemail=By.id("email");
private By Loginpass=By.id("passwd");
private By Submit=By.id("SubmitLogin");
private By Enter_Email=By.id("email_create");
private By CreateAcc=By.id("SubmitCreate");
private By Female_Gender=By.id("id_gender2");
private By Firstname=By.id("customer_firstname");
private By Lastname=By.id("customer_lastname");
private By Password=By.id("passwd");
private By Fname=By.id("firstname");
private By Lname=By.id("lastname");
private By Company=By.id("company");
private By Address=By.id("address1");
private By City=By.id("city");
private By Postcode=By.id("postcode");
private By Mobile=By.id("phone_mobile");
private By addrs=By.id("alias");
private By Register=By.id("submitAccount");
private By AccountCreated=By.xpath("//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/p[1]");


public HomePageObjects(WebDriver driver) {
	super();
	this.driver = driver;
}

public void Login_Account() {
	WebDriverWait wait = new WebDriverWait(driver, 20);
	WebElement b=wait.until(ExpectedConditions.elementToBeClickable(Submit));
	driver.findElement(Loginemail).sendKeys("kavipatil2@gmail.com");
	driver.findElement(Loginpass).sendKeys("Kavita@123");
	driver.findElement(Submit).click();

}
public void ClickOnSignUp() {
	boolean b=driver.findElement(SignUp).isDisplayed();
	Assert.assertEquals("Sign Up tab is visible", true, b);
	logger.info("User clicked on Sign Up");
	driver.findElement(SignUp).click();
	
}

public void Create_Account() throws Exception {
	boolean b=driver.findElement(Enter_Email).isDisplayed();
	Assert.assertEquals("Creating new account", true, b);
	Thread.sleep(2000);
	driver.findElement(Enter_Email).sendKeys("kavitapatil@gmail.com");
	driver.findElement(CreateAcc).click();
	Thread.sleep(2000);
}

public void Filling_Form_Details() throws Exception  {
	WebDriverWait wait=new WebDriverWait(driver,20);
	Boolean b = wait.until(ExpectedConditions.titleIs("Login - My Store"));
    WebElement radio1 = driver.findElement(Female_Gender);
    radio1.click();
    driver.findElement(Firstname).sendKeys("Kavita");
    driver.findElement(Lastname).sendKeys("Patil");
    driver.findElement(Password).sendKeys("Kavita@123");
    Select day=new Select(driver.findElement(By.id("days")));
    day.selectByValue("27");
    Select month=new Select(driver.findElement(By.id("months")));
    month.selectByIndex(7);
    Select year=new Select(driver.findElement(By.id("years")));
    year.selectByValue("1992");
    Thread.sleep(1000);
    driver.findElement(Fname).sendKeys("Kavita");
    driver.findElement(Lname).sendKeys("Patil");
    driver.findElement(Company).sendKeys("Nirmala Web Store Private Limited");
    driver.findElement(Address).sendKeys("Ring Road, Boston");
    driver.findElement(City).sendKeys("Boston");
    Select state=new Select(driver.findElement(By.id("id_state")));
    state.selectByVisibleText("Indiana");
    driver.findElement(Postcode).sendKeys("02101");
    Select country=new Select(driver.findElement(By.id("id_country")));
    country.selectByVisibleText("United States");
    driver.findElement(Mobile).sendKeys("9175333718");
    driver.findElement(addrs).sendKeys("LA");
    driver.findElement(Register).click();
    //Thread.sleep(3000);
    

}

public void Account_creation_validaion() {
	String actual=driver.findElement(AccountCreated).getText();
   	String expected="Welcome to your account. Here you can manage all of your personal information and orders.";
   	Assert.assertEquals("Account created successfully",expected, actual);
   	System.out.println("Welcome to your account");
}


  public void validatePageTitleMatch(String expectedTitle) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		Boolean b = wait.until(ExpectedConditions.titleContains(expectedTitle));
		Assert.assertEquals("Title Validation",true, b);
		logger.info("Page title matched: " + expectedTitle );
	}


}
