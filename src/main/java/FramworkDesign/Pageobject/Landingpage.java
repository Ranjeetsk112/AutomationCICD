package FramworkDesign.Pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FramworkDesign.Abstractcomponent.AbstractComp;

public class Landingpage extends AbstractComp {

		// TODO Auto-generated method stub
		WebDriver driver;
		public Landingpage(WebDriver driver)
		{
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		//WebElement userEmail=driver.findElement(By.id("userEmail"));
		//Pagefactory
		@FindBy(id="userEmail")
		WebElement userEmail;
		
		@FindBy(id="userPassword")
		WebElement password;
		
		@FindBy(id="login")
		WebElement Login;
		
		@FindBy(css="div[aria-label='Incorrect email or password.']")
		WebElement errorMessage;
		
		public void GoTo()
		{
			driver.get("https://rahulshettyacademy.com/client");
		}
		
		public Productcatalog loginApplication(String email, String Password)
		{
			userEmail.sendKeys(email);
			password.sendKeys(Password);
			Login.click();
		Productcatalog productcatalog=new Productcatalog(driver);
		return productcatalog;
		}
		
		public String GeterrorMessage() throws InterruptedException
		{
			Thread.sleep(5000);
			return errorMessage.getText();
		}
		
	}


