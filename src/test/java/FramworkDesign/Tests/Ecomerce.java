package FramworkDesign.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import FramworkDesign.Pageobject.Landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Ecomerce {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String Productname="ZARA COAT 3";
     	WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("ranjeetks123@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Ranjeet@123");
		driver.findElement(By.id("login")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("col-lg-4")));
		List<WebElement> Prod=driver.findElements(By.className("col-lg-4"));
		WebElement product=Prod.stream().filter(Prods->Prods.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3"))
		.findFirst().orElse(null);
		
		product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();
		
		 List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
		 Boolean match=cartProducts.stream().anyMatch(cartProd->cartProd.getText().equalsIgnoreCase(Productname));
		 Assert.assertTrue(match);
		 Thread.sleep(5000);
		 JavascriptExecutor js= (JavascriptExecutor)driver;
		 js.executeScript("window.scrollBy(0,1000)");
		WebElement checkOut= driver.findElement(By.cssSelector(".totalRow button"));
		 js.executeScript("arguments[0].click();",checkOut);
         
		  driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("India");
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		 driver.findElement(By.xpath("(//span[contains(@class,'ng-star-inserted')])[2]")).click();
		 js.executeScript("window.scrollBy(0,1000)");
		 js.executeScript("document.querySelector('.action__submit').scrollTop=1000");
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmation=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmation.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
	}

}
