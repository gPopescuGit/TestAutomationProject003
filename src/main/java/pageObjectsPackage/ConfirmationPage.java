package pageObjectsPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import asbtractComponentPackage.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	WebDriver driver;

	@FindBy(css = "h2[data-qa='order-placed']")
	private WebElement orderPlacedHeader;

	@FindBy(css = "p")
	private WebElement confirmationMessage;

	@FindBy(css = "a.check_out[href^='/download_invoice']")
	private WebElement downloadInvoiceButton;

	@FindBy(css = "a[data-qa='continue-button']")
	private WebElement continueButton;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isOrderPlacedVisible() {
		return orderPlacedHeader.isDisplayed();
	}

	public String getConfirmationMessage() {
		return confirmationMessage.getText().trim();
	}

	public void downloadInvoice() {
		downloadInvoiceButton.click();
	}

	public LandingPage continueToHome() {
		continueButton.click();
		return new LandingPage(driver);
	}
}
