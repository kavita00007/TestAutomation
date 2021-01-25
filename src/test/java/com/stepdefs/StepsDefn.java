package com.stepdefs;





import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.core.WebDriverFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.BuyProcess;
import pageobjects.HomePageObjects;
import pageobjects.ProductPageObjects;

public class StepsDefn {
	
	public static final Logger logger = LogManager.getLogger(StepsDefn.class);


	WebDriver driver;
	String Url =  "http://Automationpractice.com/";
	Scenario sc;
	
	HomePageObjects homepage;
	ProductPageObjects productpage;
	BuyProcess buy;
	
	
	@Before
	public void setUp(Scenario sc) throws Exception
	{
		this.sc = sc;
		String browserName = WebDriverFactory.getBrowserName();
		driver = WebDriverFactory.BrowserSelection(browserName);
		logger.info("Browser invoked.");
		
		
		homepage=new HomePageObjects(driver);
		productpage=new ProductPageObjects(driver);
		buy=new BuyProcess(driver);
   }
	
	@After(order=1)
	public void cleanUp() 
	{
		WebDriverFactory.quitDriver();
		 sc.log("Browser Closed");
	}
	
	@After(order=2)
	public void takeScreenShot(Scenario s) 
	
	{
		if (s.isFailed()) 
		{
			TakesScreenshot srnsht = (TakesScreenshot)driver;
			byte[] data = srnsht.getScreenshotAs(OutputType.BYTES);
			 sc.attach(data, "image/png","Failed Step Name: " + s.getName());
        }
		else
		{
            sc.log("Test case is passed, no screen shot captured");
        }
	}
	
	
//***************************************************SignUp feature file Implementation*******************************************************************************************************
		@Given("User navigated to website")
		public void user_navigated_to_website() {
			WebDriverFactory.navigateToTheUrl(Url);
			  sc.log("User navigated to home page of url");
		}



		@When("User click on sign up tab")
		public void user_click_on_sign_up_tab() {
			String expected="My Store";
            homepage.validatePageTitleMatch(expected);
		    homepage.ClickOnSignUp();
		}
		@When("user fill the form to complete sign up")
		public void user_fill_the_form_to_complete_sign_up() throws Exception {
			homepage.Create_Account();
			homepage.Filling_Form_Details();
		}
		@Then("User account created and User logged in successfully")
		public void user_account_created_and_user_logged_in_successfully() {
			homepage.Account_creation_validaion();

		}

//***************************************************Send a friend feature*************************************************************************************************


			@Given("User navigated to url")
			public void user_navigated_to_url() {
				WebDriverFactory.navigateToTheUrl(Url);
				  sc.log("User navigated to home page of url");
				  //homepage.Login_Account();
			}



			@When("Select the category as T-Shirts")
			public void select_the_category_as_t_shirts() {
	        	productpage.Select_Category();

			}
			@When("Click on the product: Faded Short Sleeve T-shirts")
			public void click_on_the_product_faded_short_sleeve_t_shirts() throws Exception {
				String expected="T-shirts - My Store";
				homepage.validatePageTitleMatch(expected);
				productpage.ClickonProduct();
			}
			@When("Click Send to a Friend Link, fill the details and click on Send.")
			public void click_send_to_a_friend_link_fill_the_details_and_click_on_send() throws Exception {
				productpage.SendAFrined();

			}
			@Then("Check the Message appeared that Email sent in a pop up")
			public void check_the_message_appeared_that_email_sent_in_a_pop_up() {
			    productpage.SendAFriend_Validation();
			}

//**************************************************Write a Review feature*************************************************************************************************************************************

			@When("Click on Send a Review and enter the details in the form.")
			public void click_on_send_a_review_and_enter_the_details_in_the_form() throws Exception {
				productpage.Write_Review();
				    
		}



			@Then("Check the Message appeared that New comment added.")
			public void check_the_message_appeared_that_new_comment_added() {
				   
		}

//**************************************************Colour change for product feature***************************************************************************************************************************
       

			@When("Click on Color Blue link")
			public void click_on_color_blue_link() throws Exception {
				productpage.Colour_Change_Blue();
		}



			@Then("Check the Image is changed")
			public void check_the_image_is_changed() {
			   productpage.Image_Change_Validation();
					   
		}

//***********************************************************End to End User Journey story**************************************************************************************************************************


				@Given("Login to url")
				public void login_to_url() {
					WebDriverFactory.navigateToTheUrl(Url);
					homepage.ClickOnSignUp();
					homepage.Login_Account();
		        }

                @When("Fetch the Amount of the product in a variable")
				public void fetch_the_amount_of_the_product_in_a_variable() {
				   buy.Get_Product_Price();
				}
				@When("Increase Quantity to {int}")
				public void increase_quantity_to(Integer int1) {
				    buy.Quantity_Change();
				}
				@When("Increase Size to L")
				public void increase_size_to_l() throws Exception {
				    buy.Size_Change();
				}
				@When("Click on Add to Cart")
				public void click_on_add_to_cart() {
				   buy.Add_To_Cart();
				}
				@When("Check the User sees the Pop Up: Product Successfully Added to Cart")
				public void check_the_user_sees_the_pop_up_product_successfully_added_to_cart() {
				    buy.Added_Cart_Validation();
				  // buy.Size_Quntity_Confirmation();
				}

				@When("Click on Proceed to Checkout.")
				public void click_on_proceed_to_checkout() throws Exception {
					   buy.Checkout_After_AddedToCart();
				}

                @When("Click on Proceed to Check out again and reach till payment and click on Terms and condition check box.")
				public void click_on_proceed_to_check_out_again_and_reach_till_payment_and_click_on_terms_and_condition_check_box() throws Exception {
                	  buy.Procedd_To_Checkout();
				}
				@When("On Payment Page click on Pay by bank wire and Click on I confirm my Order")
				public void on_payment_page_click_on_pay_by_bank_wire_and_click_on_i_confirm_my_order() throws Exception {
					    buy.Payment_Process();
				}
				@Then("Check the order submit page and message {string} also check is amount is right.")
				public void check_the_order_submit_page_and_message_also_check_is_amount_is_right(String string) {
					    buy.Order_Confirm();
				}





}
