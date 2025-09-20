package pageObjectsPackage.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

public class ProductCard {
	
    public ProductCard(WebElement root, ElementLocatorFactory factory) {
        PageFactory.initElements(factory, this);
    }

    public String getTitle() {
        return title.getText().trim();
    }

    public void clickAddToCart() {
        addToCartBtn.click();
    }

    public void clickViewProduct() {
        viewProductBtn.click();
    }
        
	@FindBy(css = ".productinfo p")
	private WebElement title;

	@FindBy(css = ".productinfo .add-to-cart")
	private WebElement addToCartBtn;

	@FindBy(css = ".product-overlay .view-product")
	private WebElement viewProductBtn;

}
