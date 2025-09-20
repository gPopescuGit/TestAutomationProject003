package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjectsPackage.LandingPage;

public class BaseTest {
	// initialize driver
	public WebDriver driver;
	public LandingPage landingPage;

	// create landing page
	public WebDriver initializeDriver() throws IOException {
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//resources//globalData.properties");
		properties.load(fis);
		String browserName = properties.getProperty("browser");
		String headlessMode = properties.getProperty("headless");
		if (browserName.equals("chrome")) {
			if (headlessMode.equals("yes")) {
				/*
				 * TODO: risk of false positive. Add assertions within the test to make sure it
				 * really passes.
				 */
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--headless");
				chromeOptions.addArguments("--disable-gpu");
				chromeOptions.addArguments("--window-size=1920,1080");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(chromeOptions);
			} else {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}

		} else {
			System.out.println("nothing for now");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();

		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws InterruptedException {
		driver.close();
		Thread.sleep(500);
	}

	public List<HashMap<String, String>> getJSonDataToMap(String filePath) throws IOException {

		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		// string to hashmap: required dependency Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJSonDataToMap(
				System.getProperty("user.dir") + "//src//test//resources//credentials.json");
		Object[][] o = new Object[data.size()][1];
		for(int i=0;i<data.size();i++) {
			o[i][0] = data.get(i);
		}
		return o;
	}
}
