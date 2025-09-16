package yashbaliyanacademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import yashbaliyanacademy.TestComponents.BaseTest;
import yashbaliyanacademy.pageObjects.CartPage;
import yashbaliyanacademy.pageObjects.CheckoutPage;
import yashbaliyanacademy.pageObjects.ConfirmationPage;
import yashbaliyanacademy.pageObjects.OrderPage;
import yashbaliyanacademy.pageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = "submitOrder")
	public void OrderHistoryTest() {

		ProductCatalogue productCatalogue = landingPage.loginApplication("prachibaliyan@gmail.com", "Prachi@1234");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				"C:\\Users\\91812\\eclipse-workspace-fresh-2\\SeleniumFrameworkDesign\\src\\test\\java\\yashbaliyanacademy\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
	
	
//	@DataProvider
//	public Object[][] getData() throws IOException {
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "yashbaliyan@gmail.com");
//		map.put("password", "Yash@1234");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "prachibaliyan@gmail.com");
//		map1.put("password", "Prachi@1234");
//		map1.put("product", "ADIDAS ORIGINAL");
//	}
	
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] { { "yashbaliyan@gmail.com", "Yash@1234", "ADIDAS ORIGINAL" },
//				{ "prachibaliyan@gmail.com", "Prachi@1234", "ZARA COAT 3" } };
//	}
}
