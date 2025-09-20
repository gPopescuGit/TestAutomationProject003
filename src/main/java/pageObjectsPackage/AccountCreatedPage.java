package pageObjectsPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import asbtractComponentPackage.AbstractComponent;

public class AccountCreatedPage extends AbstractComponent {

	WebDriver driver;
	
	@FindBy(css="a[data-qa='continue-button']")
	WebElement continueBtn;
	
	public AccountCreatedPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public LandingPage finishSignUp() {
		continueBtn.click();
		return new LandingPage(driver);
	}
}
