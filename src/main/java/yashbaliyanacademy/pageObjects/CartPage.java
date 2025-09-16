package yashbaliyanacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import yashbaliyanacademy.AbstractComponenets.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	public Boolean verifyProductDisplay(String productName) {
		boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckoutPage goToCheckout() {
		checkoutEle.click();
		return new CheckoutPage(driver);
	}



}
