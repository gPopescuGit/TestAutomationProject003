package pageObjectsPackage.components;

import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CategoryPanel {

	@FindBy(css = ".panel-title")
	private List<WebElement> categoryTitles;

	@FindBy(css = ".panel-body ul li a")
	private List<WebElement> subcategoryLinks;

	public void clickSubcategory(String category, String subcategory) {
		for (WebElement title : categoryTitles) {
			if (title.getText().trim().equalsIgnoreCase(category)) {
				for (WebElement link : subcategoryLinks) {
					if (link.getText().trim().equalsIgnoreCase(subcategory)) {
						link.click();
						return;
					}
				}
				throw new NoSuchElementException(
						"Subcategory '" + subcategory + "' not found under '" + category + "'");
			}
		}
		throw new NoSuchElementException("Category '" + category + "' not found");
	}
}