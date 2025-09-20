package pageObjectsPackage;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import asbtractComponentPackage.AbstractComponent;
import pageObjectsPackage.components.CartItem;

public class CartPage extends AbstractComponent {
	WebDriver driver;

	@FindBy(css = "#cart_items .cart_product")
	private List<CartItem> cartItems;

	@FindBy(css = "a.check_out")
	private WebElement checkoutBtn;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private CartItem findItemByName(String productName) {
		return cartItems.stream().filter(item -> item.getProductName().equalsIgnoreCase(productName)).findFirst()
				.orElseThrow(() -> new NoSuchElementException("Product not found in cart: " + productName));

	}

	public void deleteProduct(String productName) {
		findItemByName(productName).deleteItem();
	}

	public String getProductPrice(String productName) {
		return findItemByName(productName).getPrice();
	}

	public String getProductQuantity(String productName) {
		return findItemByName(productName).getQuantity();
	}

	public List<CartItem> getAllItems() {
		return cartItems;
	}

	public CheckoutPage proceedToCheckout() {
		checkoutBtn.click();
		return new CheckoutPage(driver);

	}

}
