package pageObjectsPackage.components;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BrandPanel {
	@FindBy(css = ".brands-name ul li a")
	private List<WebElement> brandLinks;

	public void clickBrand(String brandName) {
		for (WebElement link : brandLinks) {
			if (link.getText().trim().equalsIgnoreCase(brandName)) {
				link.click();
				return;
			}
		}
		throw new NoSuchElementException("Brand '" + brandName + "' not found");
	}
}
