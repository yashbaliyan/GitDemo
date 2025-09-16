package yashbaliyanacademy.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import yashbaliyanacademy.TestComponents.BaseTest;
import yashbaliyanacademy.TestComponents.Retry;
import yashbaliyanacademy.pageObjects.CartPage;
import yashbaliyanacademy.pageObjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
	
	@Test (groups={"ErrorHandling"},retryAnalyzer=Retry.class)
	public void  LoginErrorValidation() {
		landingPage.loginApplication("yashbaliyan@gmail.com", "Yash@");//wrong password to get the text
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
		System.out.println("GitDemoX");
		System.out.println("GitDemoX2");
	}
	
	@Test
	public void ProductErrorValidation() throws InterruptedException {
		String productName = "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue = landingPage.loginApplication("yashbaliyan@gmail.com", "Yash@1234");
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();
  
        Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
	}

}
