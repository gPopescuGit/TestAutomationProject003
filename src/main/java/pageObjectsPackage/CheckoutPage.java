package pageObjectsPackage;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import asbtractComponentPackage.AbstractComponent;
import pageObjectsPackage.components.CartItem;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;

	@FindBy(css = "#cart_info tbody tr[id^='product-']")
	private List<CartItem> checkoutItems;

	@FindBy(css = "textarea.form-control")
	private WebElement formTextField;

	@FindBy(css = "a.check_out")
	private WebElement checkOutBtn;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private CartItem findItemByName(String productName) {
		return checkoutItems.stream().filter(item -> item.getProductName().equalsIgnoreCase(productName)).findFirst()
				.orElseThrow(() -> new NoSuchElementException("Product not found in checkout: " + productName));
	}

	public String getProductPrice(String productName) {
		return findItemByName(productName).getPrice();
	}

	public String getProductQuantity(String productName) {
		return findItemByName(productName).getQuantity();
	}

	public String getProductTotal(String productName) {
		return findItemByName(productName).getTotalPrice();
	}

	public List<CartItem> getAllItems() {
		return checkoutItems;
	}
	
	public PaymentPage completeCheckout() {
		checkOutBtn.click();
		return new PaymentPage(driver);
	}

}
