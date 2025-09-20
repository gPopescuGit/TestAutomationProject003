package pageObjectsPackage.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartItem {
	@FindBy(css = ".cart_description h4 a")
	private WebElement productName;

	@FindBy(css = ".cart_description p")
	private WebElement categoryPath;

	@FindBy(css = ".cart_price p")
	private WebElement price;

	@FindBy(css = ".cart_quantity button")
	private WebElement quantity;

	@FindBy(css = ".cart_total_price")
	private WebElement totalPrice;

	@FindBy(css = ".cart_delete a")
	private WebElement deleteBtn;

	public String getProductName() {
		return productName.getText().trim();
	}

	public String getCategoryPath() {
		return categoryPath.getText().trim();
	}

	public String getPrice() {
		return price.getText().trim();
	}

	public String getQuantity() {
		return quantity.getText().trim();
	}

	public String getTotalPrice() {
		return totalPrice.getText().trim();
	}

	public void deleteItem() {
		if (deleteBtn != null && deleteBtn.isDisplayed()) {
			deleteBtn.click();
		}
	}
}
