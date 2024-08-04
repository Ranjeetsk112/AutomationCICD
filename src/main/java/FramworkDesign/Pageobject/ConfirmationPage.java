package FramworkDesign.Pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FramworkDesign.Abstractcomponent.AbstractComp;

public class ConfirmationPage extends AbstractComp {
      WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}
	//driver.findElement(By.cssSelector(".hero-primary")
	@FindBy(css=".hero-primary")
	WebElement ConfirmationMessage;
	
	public String Getconfirmationmessage()
	{
		return ConfirmationMessage.getText();
	}

}
