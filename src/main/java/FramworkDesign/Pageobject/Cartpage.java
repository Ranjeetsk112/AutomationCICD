package FramworkDesign.Pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FramworkDesign.Abstractcomponent.AbstractComp;

public class Cartpage extends AbstractComp {

		// TODO Auto-generated method stub
		WebDriver driver;
		public Cartpage(WebDriver driver)
		{
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		//driver.findElements(By.cssSelector(".cartSection h3"));
		
		@FindBy(css=".cartSection h3")
		List<WebElement> cartProducts;
		
		//driver.findElement(By.cssSelector(".totalRow button")).click();
		
		@FindBy(css=".totalRow button")
		WebElement checkOut;
		
		
		public Boolean VerifyProductDisplay(String Productname)
		{
			Boolean match=cartProducts.stream().anyMatch(cartProd->cartProd.getText().equalsIgnoreCase(Productname));
			return match;
		}
		public CheckOutpage goTocheckout() throws InterruptedException
		{    Thread.sleep(5000);
			 JavascriptExecutor js= (JavascriptExecutor)driver;
			 js.executeScript("window.scrollBy(0,1000)");
			js.executeScript("arguments[0].click();",checkOut);
			return new CheckOutpage(driver); 
		}
	}


