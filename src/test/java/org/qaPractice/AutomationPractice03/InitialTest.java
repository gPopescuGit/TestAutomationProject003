package org.qaPractice.AutomationPractice03;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

//TODO: create suite to be triggered from cmd command

public class InitialTest {
	//popups make test unstable. Gotta find way to deal with those
	List<WebElement> list;

	@Test
	public void initialTest() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://automationexercise.com/");

		// Manage popup
		driver.findElement(By.xpath("//p[@class=\"fc-button-label\"]")).click();

//		driver.findElement(By.cssSelector("a[href='/']")).click();
//		// Login
		driver.findElement(By.xpath("//a[contains(text(), 'Signup / Login')]")).click();
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("user@mail");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("userpass");
		driver.findElement(By.cssSelector("button[type='submit'][data-qa='login-button']")).click();
		//avoid home bottom popup
		try {
			Thread.sleep(6);
			driver.findElement(By.cssSelector("a[href='/']")).click();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//directly add product from home
		driver.findElement(By.xpath("//a[@data-product-id='1']")).click();
		// after 'product added' popup appears, open cart
		driver.findElement(By.cssSelector("a[href='/view_cart']")).click();

		// checkout
		driver.findElement(By.xpath("//a[contains(text(), 'Proceed To Checkout')]")).click();

		// place order
		driver.findElement(By.cssSelector("a[href='/payment']")).click();

		// payment fields
		driver.findElement(By.cssSelector("input[name='name_on_card']")).sendKeys("name");
		driver.findElement(By.cssSelector("input[name='card_number']")).sendKeys("12345");
		driver.findElement(By.cssSelector("input[name='cvc']")).sendKeys("123");
		driver.findElement(By.cssSelector("input[name='expiry_month']")).sendKeys("11");
		driver.findElement(By.cssSelector("input[name='expiry_year']")).sendKeys("2028");
		driver.findElement(By.id("submit")).click();

		// download invoice
		//! download not finished :))
//		driver.findElement(By.xpath("//a[contains(@href, '/download_invoice/')]")).click();
		try {
			Thread.sleep(8);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.close();
	}
}
