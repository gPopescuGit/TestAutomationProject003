package pageObjectsPackage;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import asbtractComponentPackage.AbstractComponent;
import pageObjectsPackage.components.BrandPanel;
import pageObjectsPackage.components.CategoryPanel;
import pageObjectsPackage.components.ProductCard;

public class LandingPage extends AbstractComponent {
	WebDriver driver;

	// List of product cards
	@FindBy(css = ".features_items .product-image-wrapper")
	private List<ProductCard> productCards;

	@FindBy(css = "#accordian")
	private CategoryPanel categoryPanel;

	@FindBy(css = ".brands_products")
	private BrandPanel brandPanel;


	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
//		dismissFloatingBanner();
	}

	public void goTo() {
		driver.get("https://automationexercise.com/");
		
		// Manage popup
		driver.findElement(By.xpath("//p[@class=\"fc-button-label\"]")).click();
//		dismissFloatingBanner();
	}

	private ProductCard findCardByName(String productName) {
		return productCards.stream().filter(card -> card.getTitle().equals(productName)).findFirst()
				.orElseThrow(() -> new NoSuchElementException("Product not found: " + productName));
	}

	public void addProductToCart(String productName) {
		findCardByName(productName).clickAddToCart();
	}

	public void addProductsToCart(List<String> shoppingList) {
		shoppingList.forEach(this::addProductToCart);
	}

	public CartPage viewCart() {
		goToCart();
		return new CartPage(driver);
	}

	public LoginPage loginOrSignup() {
		clickAuthButton();
		return new LoginPage(driver);
	}

	public ProductsPage viewProduct(String productName) {
		findCardByName(productName).clickViewProduct();
		return new ProductsPage(driver);
		// TODO: products page open but its different
	}

	public ProductsPage selectCategory(String category, String subcategory) {
		categoryPanel.clickSubcategory(category, subcategory);
		return new ProductsPage(driver);
	}

	public ProductsPage selectBrand(String brandName) {
		brandPanel.clickBrand(brandName);
		return new ProductsPage(driver);
	}

}
