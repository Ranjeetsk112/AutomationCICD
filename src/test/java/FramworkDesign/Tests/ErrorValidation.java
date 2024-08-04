package FramworkDesign.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import FramworkDesign.Pageobject.Cartpage;
import FramworkDesign.Pageobject.Productcatalog;
import FramworkDesign.Testcomponent.BaseTest;

public class ErrorValidation extends BaseTest {

	

	@Test(groups= {"ErrorHandling"},retryAnalyzer = FramworkDesign.Testcomponent.Retry.class)
	public void productErrorvalidation() throws InterruptedException, IOException 
	{
	
		String Productname="ZARA COAT 3";
		Productcatalog productcatalog=landingpage.loginApplication("ranjeetks123@gmail.com", "Ranjeet@123");
		List<WebElement> Products=productcatalog.getProductlist();
		productcatalog.addProducttoCart(Productname);
		Cartpage cartpage=productcatalog.GoTocartPage();
		Boolean match=cartpage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
		}

	
	}