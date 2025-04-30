package com.mmucorp.beansinapod.guitest.UI;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.mmucorp.beansinapod.guitest.PageObject.LoginPage;

public class GuitestApplicationTests {
	private static final String baseURL = "https://localhost:3000/login";

	private WebDriver driver;
		
	public GuitestApplicationTests() {
		driver = new FirefoxDriver();
	}
	
	@Before
	public void openDriver() {
		driver.get(baseURL);
	}
	
	@After
	public void closeDriver() {
		driver.close();
	}
	
	@Test
	public void WhenLoginWithIncorrectUserNameThenErrorDisplayed() {
		LoginPage page = new LoginPage(driver);
		page.setEmail("test@example.com");
		page.setPassword("wrongpassword");
		page.clickLoginButton();
		Assert.assertEquals("Login failed. Invalid email or password.", page.getLoginError());
	}

	@Test
	public void WhenLoginWithIncorrectPasswordThenErrorDisplayed() {
		LoginPage page = new LoginPage(driver);
		page.setEmail("sonic@sega.com");
		page.setPassword("wrongpassword");
		page.clickLoginButton();
		Assert.assertEquals("Login failed. Invalid email or password.", page.getLoginError());
	}

	@Test
	public void WhenLoginWithNoEmailEnteredThenValidationErrorDisplayed() {
		LoginPage page = new LoginPage(driver);
		page.clearEmail();
		page.setPassword("password");
		page.clickLoginButton();
		Assert.assertEquals("Please fill out this field.", page.getEmailValidationError());
	}

	@Test
	public void WhenLoginWithNoPasswordEnteredThenValidationErrorDisplayed() {
		LoginPage page = new LoginPage(driver);
		page.clearPassword();
		page.setEmail("test@example.com");
		page.clickLoginButton();
		Assert.assertEquals("Please fill out this field.", page.getPasswordValidationError());
	}

	@Test
	public void WhenLoginWithCorrectCredentialRedirectToHomePage() {
		LoginPage page = new LoginPage(driver);
		String redirectURL = "https://localhost:3000/home";
		
		page.setEmail("sonic@sega.com");
		page.setPassword("password");
		page.clickLoginButton();

		Assert.assertEquals(redirectURL, page.getPageRedirect(redirectURL));
	}
	
}
