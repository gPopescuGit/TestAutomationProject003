package manageUserAccount;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import pageObjectsPackage.LandingPage;
import pageObjectsPackage.LoginPage;
import testComponents.BaseTest;

public class Test_UserLogin extends BaseTest{

	@Test(dataProvider="getData", groups="ManageUserAccount")
	public void testUserLoginSuccess(HashMap<String, String> input) {
		// Login/Signup page
		LoginPage loginOrSignup = new LandingPage(driver).loginOrSignup();
		LandingPage login = loginOrSignup.login(input.get("email"), input.get("pass"));


	}
}
