package yashbaliyanacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import yashbaliyanacademy.TestComponents.BaseTest;
import yashbaliyanacademy.pageObjects.CartPage;
import yashbaliyanacademy.pageObjects.CheckoutPage;
import yashbaliyanacademy.pageObjects.ConfirmationPage;
import yashbaliyanacademy.pageObjects.LandingPage;
import yashbaliyanacademy.pageObjects.ProductCatalogue;

public class stepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page") // agar given h to give ki annotation se shru krna h just,koi aur h to uski se
	public void i_landed_on_ecommerce_page() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username , password);
	}
	
	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) {
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_cofirmationPage(String string) {
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void error_message_is_displayed(String string) {
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();
	}

}
