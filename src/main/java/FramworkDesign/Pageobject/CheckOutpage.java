package FramworkDesign.Pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import FramworkDesign.Abstractcomponent.AbstractComp;

public class CheckOutpage extends AbstractComp{

	WebDriver driver;
	public CheckOutpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElement(By.cssSelector("[placeholder='Select Country']")
	@FindBy(css="[placeholder='Select Country']")
	WebElement Country;
	
	//driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")
	
	@FindBy(xpath="//span[.=\" India\"]")
	WebElement SelectCountry;
	//driver.findElement(By.cssSelector(".action__submit")).click();
	
	@FindBy(css=".action__submit")
	WebElement Submit;
	
	By results=By.cssSelector(".ta-results");
	
	public void selectCountry(String CountryName) throws InterruptedException
	{
		Country.sendKeys(CountryName);
//		waitforElementtoAppear(By.xpath("//span[.=\" India\"]"));
		Thread.sleep(5000);
		SelectCountry.click();
		
	}
	public ConfirmationPage submitOrder()
	{ 
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1000)");
		 js.executeScript("document.querySelector('.action__submit').scrollTop=1000");
		Submit.click();
		return new ConfirmationPage(driver);
	
	
		 
}
}


