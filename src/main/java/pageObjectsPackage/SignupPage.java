package pageObjectsPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import asbtractComponentPackage.AbstractComponent;
import pageObjectsPackage.components.BrandPanel;

public class SignupPage extends AbstractComponent {
	WebDriver driver;

	@FindBy(css = "#password")
	private WebElement passwordField;

	@FindBy(css = "#first_name")
	private WebElement fName;

	@FindBy(css = "#last_name")
	private WebElement lName;

	@FindBy(css = "#address1")
	private WebElement address;

	@FindBy(css = "select[data-qa='country']")
	private WebElement countryList;
	

	
	@FindBy(css = "#state")
	private WebElement state;

	@FindBy(css = "#city")
	private WebElement city;

	@FindBy(css = "#zipcode")
	private WebElement zipcode;

	@FindBy(css = "#mobile_number")
	private WebElement mobileNumber;
	
	@FindBy(css = "button[data-qa='create-account']")
	private WebElement createAccountBtn;


	public SignupPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public AccountCreatedPage fastSignUp(String password) {
		Select selectCountry = new Select(countryList);
		
		passwordField.sendKeys(password);
		fName.sendKeys("firstName");
		lName.sendKeys("firstName");
		address.sendKeys("firstName");
		selectCountry.selectByVisibleText("Canada");
		state.sendKeys("firstName");
		city.sendKeys("firstName");
		zipcode.sendKeys("firstName");
		mobileNumber.sendKeys("firstName");
		createAccountBtn.click();
		return new AccountCreatedPage(driver);

	}

}
