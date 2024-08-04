package FramworkDesign.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import FramworkDesign.Pageobject.Cartpage;
import FramworkDesign.Pageobject.CheckOutpage;
import FramworkDesign.Pageobject.ConfirmationPage;
import FramworkDesign.Pageobject.Orderpage;
import FramworkDesign.Pageobject.Productcatalog;
import FramworkDesign.Testcomponent.BaseTest;

public class Ecomerce1 extends BaseTest {

	String Productname="ZARA COAT 3";

	@Test(dataProvider="getdata",groups={"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException 
	{
	
		
		Productcatalog productcatalog=landingpage.loginApplication(input.get("email"), input.get("Password"));
		List<WebElement> Products=productcatalog.getProductlist();
		productcatalog.addProducttoCart(Productname);
		Cartpage cartpage=productcatalog.GoTocartPage();
		Boolean match=cartpage.VerifyProductDisplay(Productname);
		Assert.assertTrue(match);
		CheckOutpage checkoutpage=cartpage.goTocheckout();
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage=checkoutpage.submitOrder();
		String confirmation=confirmationpage.Getconfirmationmessage();
		Assert.assertTrue(confirmation.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistorytest() throws InterruptedException
	{
		
		Productcatalog productcatalog=landingpage.loginApplication("ranjeetks123@gmail.com", "Ranjeet@123");
		Orderpage orderpage= productcatalog.GoToOrderPage();
		Assert.assertTrue(orderpage.VerifyOrderToDisplay(Productname));
	}
	

	@DataProvider
	public Object[][] getdata() throws IOException
	{
		List<HashMap<String,String>> data= getjsonDataTomap(System.getProperty("user.dir")+"\\src\\test\\java\\FramworkDesign\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};

	}
	}






//@DataProvider
//public Object[][] getdata()
//{
//	return new Object[][] {{"ranjeetks123@gmail.com","Ranjeet@123"},{"shetty@gmail.com","Iamking@000"}};
//}
//@DataProvider
//public Object[][] getdata() throws IOException
//{
//	HashMap<String,String> map=new HashMap<String,String>();
//	map.put("email", "ranjeetks123@gmail.com");
//	map.put("Password", "Ranjeet@123");	
//	HashMap<String,String> map1=new HashMap<String,String>();
//	map1.put("email", "shetty@gmail.com");
//	map1.put("Password", "Iamking@000");

