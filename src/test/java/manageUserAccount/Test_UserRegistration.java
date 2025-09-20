package manageUserAccount;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjectsPackage.AccountCreatedPage;
import pageObjectsPackage.LandingPage;
import pageObjectsPackage.LoginPage;
import pageObjectsPackage.SignupPage;
import testComponents.BaseTest;

public class Test_UserRegistration extends BaseTest{

	@Test(dataProvider="getData", groups="ManageUserAccount")
	public void testRegistrationSuccess(HashMap<String, String> input) {	
		// Login/Signup page
		LoginPage loginOrSignup = new LandingPage(driver).loginOrSignup();
		SignupPage signup = loginOrSignup.signup(input.get("name"), input.get("email"));
		
		// Fill signup data
		AccountCreatedPage accountCreated = signup.fastSignUp(input.get("pass"));
		LandingPage finishSignUp = accountCreated.finishSignUp();

	}
	//

	
	/*-
	 * test user already exists
	 * test field not filled
	 */
	
}
