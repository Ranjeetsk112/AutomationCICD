package StandaloneApplication.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import FramworkDesign.Pageobject.Cartpage;
import FramworkDesign.Pageobject.CheckOutpage;
import FramworkDesign.Pageobject.ConfirmationPage;
import FramworkDesign.Pageobject.Landingpage;
import FramworkDesign.Pageobject.Productcatalog;
import FramworkDesign.Testcomponent.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionimp extends BaseTest {
	
	public Landingpage landingpage; 
	public Productcatalog productcatalog;
	public ConfirmationPage confirmationpage;
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException 
	{
		 landingpage=launchApplication();
	}
	
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_password(String username, String Password)
	{
		 productcatalog=landingpage.loginApplication(username,Password);
	}
	
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> Products=productcatalog.getProductlist();
		productcatalog.addProducttoCart(productName);
	}
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_submit_the_order(String productName) throws InterruptedException
	{
		Cartpage cartpage=productcatalog.GoTocartPage();
		Boolean match=cartpage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutpage checkoutpage=cartpage.goTocheckout();
		checkoutpage.selectCountry("india");
		confirmationpage=checkoutpage.submitOrder();
	}
	@Then("{string} message is display on ConfirmationPage")
	public void message_is_display_on_ConfirmationPage(String string) 
	{
		String confirmation=confirmationpage.Getconfirmationmessage();
		Assert.assertTrue(confirmation.equalsIgnoreCase(string));
		driver.close();
	}

}
