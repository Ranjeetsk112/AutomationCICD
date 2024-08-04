package FramworkDesign.Abstractcomponent;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import FramworkDesign.Pageobject.Cartpage;
import FramworkDesign.Pageobject.Orderpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AbstractComp {
	
	WebDriver driver;
	public AbstractComp(WebDriver driver) {
		super();
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[contains(text(),'Cart')]")
	WebElement Cartheader;
	//
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement Orderheader;
	
    public void waitforElementtoAppear(By findBy)
    {
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
}
    public void waitforElementtodisAppear(WebElement ele) 
    {
    	//Thread.sleep(1000);
    	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
    	wait.until(ExpectedConditions.invisibilityOf(ele));
    }
    public Cartpage GoTocartPage() throws InterruptedException
    {
    	Thread.sleep(5000);
    	Cartheader.click();
    	Cartpage cartpage= new Cartpage(driver);
    	return cartpage;
    	
    }
    public Orderpage GoToOrderPage() throws InterruptedException
    {
    	Thread.sleep(5000);
    	Orderheader.click();
    	Orderpage orderpage= new Orderpage(driver);
    	return orderpage;
    	
    }

}
