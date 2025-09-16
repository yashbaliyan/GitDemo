package yashbaliyanacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import yashbaliyanacademy.AbstractComponenets.AbstractComponents;

public class OrderPage extends AbstractComponents {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="td:nth-child(3)")
	List<WebElement> productNames;
	
	
	public Boolean verifyOrderDisplay(String productName) {
		boolean match = productNames.stream()
				.anyMatch(Product -> Product.getText().equalsIgnoreCase(productName));
		return match;
	}

}
