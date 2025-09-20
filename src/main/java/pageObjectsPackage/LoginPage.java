package pageObjectsPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import asbtractComponentPackage.AbstractComponent;

public class LoginPage extends AbstractComponent {
	WebDriver driver;

	// Login form elements
	@FindBy(css = "input[data-qa='login-email']")
	private WebElement loginEmailInput;

	@FindBy(css = "input[data-qa='login-password']")
	private WebElement loginPasswordInput;

	@FindBy(css = "button[data-qa='login-button']")
	private WebElement loginButton;

	@FindBy(css = "form[action='/login'] p")
	private WebElement loginErrorMessage;

	// Signup form elements
	@FindBy(css = "input[data-qa='signup-name']")
	private WebElement signupNameInput;

	@FindBy(css = "input[data-qa='signup-email']")
	private WebElement signupEmailInput;

	@FindBy(css = "button[data-qa='signup-button']")
	private WebElement signupButton;

	@FindBy(css = "form[action='/signup'] p")
	private WebElement signupErrorMessage;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Login actions
	public LandingPage login(String email, String password) {
		loginEmailInput.sendKeys(email);
		loginPasswordInput.sendKeys(password);
		loginButton.click();
		return new LandingPage(driver);
	}

	public boolean isLoginErrorVisible() {
		return loginErrorMessage.isDisplayed();
	}

	public String getLoginErrorText() {
		return loginErrorMessage.getText().trim();
	}

	// Signup actions
	public SignupPage signup(String name, String email) {
		signupNameInput.sendKeys(name);
		signupEmailInput.sendKeys(email);
		signupButton.click();
		return new SignupPage(driver);
	}

	public boolean isSignupErrorVisible() {
		return signupErrorMessage.isDisplayed();
	}

	public String getSignupErrorText() {
		return signupErrorMessage.getText().trim();
	}
}
