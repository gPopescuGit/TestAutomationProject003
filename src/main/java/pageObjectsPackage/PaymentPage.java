package pageObjectsPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import asbtractComponentPackage.AbstractComponent;

public class PaymentPage extends AbstractComponent {
	WebDriver driver;

	@FindBy(css = "input[data-qa='name-on-card']")
	private WebElement nameOnCardInput;

	@FindBy(css = "input[data-qa='card-number']")
	private WebElement cardNumberInput;

	@FindBy(css = "input[data-qa='cvc']")
	private WebElement cvcInput;

	@FindBy(css = "input[data-qa='expiry-month']")
	private WebElement expiryMonthInput;

	@FindBy(css = "input[data-qa='expiry-year']")
	private WebElement expiryYearInput;

	@FindBy(css = "button[data-qa='pay-button']")
	private WebElement payButton;

	@FindBy(css = "#success_message .alert-success")
	private WebElement successMessage;

	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	 public void enterPaymentDetails(String name, String cardNumber, String cvc, String month, String year) {
	        nameOnCardInput.sendKeys(name);
	        cardNumberInput.sendKeys(cardNumber);
	        cvcInput.sendKeys(cvc);
	        expiryMonthInput.sendKeys(month);
	        expiryYearInput.sendKeys(year);
	    }
	
	    public boolean isOrderSuccessMessageDisplayed() {
	        return successMessage.isDisplayed();
	    }

	    public String getSuccessMessageText() {
	        return successMessage.getText().trim();
	    }
	 
	  public ConfirmationPage submitPayment() {
	        payButton.click();
	        return new ConfirmationPage(driver);
	    }
}
