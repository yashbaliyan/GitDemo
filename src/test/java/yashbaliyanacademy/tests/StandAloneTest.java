package yashbaliyanacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import yashbaliyanacademy.pageObjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		String productname = "ADIDAS ORIGINAL";
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage landingPage = new LandingPage(driver);

		driver.findElement(By.id("userEmail")).sendKeys("yashbaliyan@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Yash@1234");
		driver.findElement(By.id("login")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector(".mb-3 b")).getText().contains(productname))
				.findFirst().orElse(null);

		// yhapr, stream use karke, har product ko search kar rahe h product ke level pe
		// hi,not on the driver level
		// or fir us product ko variable prod me store kar rhe h

		prod.findElement(By.cssSelector(".mb-3 button:last-of-type")).click();// fir prod level pe search krke just
																				// click

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
		// waiting jabtk vo "Added to cart" message ni aat
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		// waiting jabtk loading screen hat ni jaati

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[routerlink*='cart']")));
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productname));

		System.out.println(match);
		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click();

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		driver.findElement(By.cssSelector("div[class='actions'] a")).click();
		
		String confirmMessage = driver.findElement(By.tagName("h1")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}

}
