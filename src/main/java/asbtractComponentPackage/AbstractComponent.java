package asbtractComponentPackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjectsPackage.LandingPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForWebElementToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public boolean isElementVisible(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void waitForElementToDissapear(WebElement element) throws InterruptedException {
		// there are more spinners in the backgroud, reason why it waits more than
		// expected
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// wait.until(ExpectedConditions.invisibilityOf(element));
		// For this case, a better solution is:
		Thread.sleep(2000);
	}

	// TODO: add method to manage the sliding pop-up

	// BasePage utilities
	@FindBy(linkText = "Home")
	protected WebElement btnHome;

	@FindBy(linkText = "Products")
	protected WebElement btnProducts;

	@FindBy(linkText = "Cart")
	protected WebElement btnCart;

	@FindBy(linkText = "Signup / Login")
	protected WebElement btnSignupLogin;

	@FindBy(linkText = "Logout")
	private WebElement btnLogout;

	@FindBy(css = "a[href='/delete_account']")
	private WebElement btnDeleteAccount;

	@FindBy(linkText = "Test Cases")
	protected WebElement btnTestCases;

	@FindBy(linkText = "API Testing")
	protected WebElement btnApiTesting;

	@FindBy(linkText = "Video Tutorials")
	protected WebElement btnVideoTutorials;

	@FindBy(linkText = "Contact us")
	protected WebElement btnContactUs;

	// common navigation actions
	public void goToCart() {
		btnCart.click();
	}

	public void goToHome() {
		btnHome.click();
	}

	public void goToProducts() {
		btnProducts.click();
	}

	public LandingPage deleteAccount() {
		btnDeleteAccount.click();
		//a confirmation page will be displayed with a 'continue' button
		driver.findElement(By.cssSelector("a[data-qa='continue-button']")).click();
		return new LandingPage(driver);
	}

	public LandingPage logout() {
		btnLogout.click();
		return new LandingPage(driver);
	}
	/**
	 * Clicks “Logout” if the user is logged in (i.e. logout link present),
	 * otherwise clicks “Signup / Login.”
	 */

	public void clickAuthButton() {
		if (isUserLoggedIn()) {
			btnLogout.click();
		} else {
			btnSignupLogin.click();
		}
	}

	public boolean isUserLoggedIn() {
		try {
			return btnLogout.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void dismissFloatingBanner() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));

			// Wait for the clickable SVG path to appear
			WebElement collapseControl = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[name()='path' and @fill='#FAFAFA']")));

			collapseControl.click(); // Collapse the banner

		} catch (TimeoutException | NoSuchElementException ignored) {
			// Banner didn’t appear or was already collapsed — safe to continue
		}
	}

}