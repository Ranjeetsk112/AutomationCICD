package FramworkDesign.Pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FramworkDesign.Abstractcomponent.AbstractComp;

public class Orderpage extends AbstractComp {

		// TODO Auto-generated method stub
		WebDriver driver;
		public Orderpage(WebDriver driver)
		{
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		//driver.findElements(By.cssSelector(".cartSection h3"));
		
		@FindBy(css="tr td:nth-child(3)")
		List<WebElement> Productnames;
		
		//driver.findElement(By.cssSelector(".totalRow button")).click();
		
		@FindBy(css=".totalRow button")
		WebElement checkOut;
		
		
		public Boolean VerifyOrderToDisplay(String Productname)
		{
			Boolean match=Productnames.stream().anyMatch(cartProd->cartProd.getText().equalsIgnoreCase(Productname));
			return match;
		}
		
	}


