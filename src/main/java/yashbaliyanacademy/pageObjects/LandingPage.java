package yashbaliyanacademy.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import yashbaliyanacademy.AbstractComponenets.AbstractComponents;

public class LandingPage extends AbstractComponents{

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		// initializing a constructor to get the main 'driver' knowledge to this class
		// driver
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);// PageFactory is a method to write the locators in a short way
												// using FindBy method
	}

	// PageFactory

	@FindBy(id = "userEmail") // now this locator is assigned into the following next line i.e. userEmail
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement passwordele;

	@FindBy(id = "login")
	WebElement submit;

	@FindBy(id="toast-container")
	WebElement errorMessage;
	
	public ProductCatalogue loginApplication(String email, String password) {

		userEmail.sendKeys(email);
		passwordele.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}
