package pageObjectsPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import asbtractComponentPackage.AbstractComponent;

public class ProductsPage extends AbstractComponent {
	WebDriver driver;

	public ProductsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
