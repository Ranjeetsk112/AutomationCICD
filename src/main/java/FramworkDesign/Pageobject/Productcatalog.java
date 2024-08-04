package FramworkDesign.Pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import FramworkDesign.Abstractcomponent.AbstractComp;

public class Productcatalog extends AbstractComp {

	// TODO Auto-generated method stub
	WebDriver driver;

	public Productcatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// List<WebElement> Prod=driver.findElements(By.cssSelector(".col-lg-4"));
	// Pagefactory
	@FindBy(css = ".col-lg-4")
	List<WebElement> Products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By ProductsBy = By.cssSelector(".col-lg-4");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastmessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductlist() {
		waitforElementtoAppear(ProductsBy);
		return Products;
	}

	public WebElement getProductByName(String Productname) {

		WebElement prod = getProductlist().stream()
				.filter(Prods -> Prods.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);
		return prod;
	}

	public void addProducttoCart(String Productname) {
		WebElement prod = getProductByName(Productname);
		prod.findElement(addToCart).click();
		waitforElementtoAppear(toastmessage);
		waitforElementtodisAppear(spinner);
	}

}
