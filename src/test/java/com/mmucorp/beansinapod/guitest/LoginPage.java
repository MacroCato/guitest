package com.mmucorp.beansinapod.guitest;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;


class LoginPage {
	private WebDriver driver;
	private WebDriverWait waiter;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickLoginButton() {
		driver.findElement(By.id("login")).click();
	}

    public void setEmail(String email) {
        WebElement emailField = driver.findElement(By.id("email")); 
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void clearEmail() {
        WebElement emailField = driver.findElement(By.id("email")); 
        emailField.clear();
    }

    public void setPassword(String password) {
        WebElement passwordField = driver.findElement(By.id("password")); 
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clearPassword() {
        WebElement passwordField = driver.findElement(By.id("password")); 
        passwordField.clear();
    }
	
    public String getLoginError() {
        waiter = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds
        WebElement errorElement = waiter.until(ExpectedConditions.visibilityOfElementLocated(By.id("error-message")));
        return errorElement.getText();
    }

    public String getEmailValidationError() {
        WebElement emailField = driver.findElement(By.id("email")); 
        return emailField.getAttribute("validationMessage");
    }

    public String getPasswordValidationError() {
        WebElement passwordField = driver.findElement(By.id("password")); 
        return passwordField.getAttribute("validationMessage");
    }

    public String getPageRedirect(String webpage) {
        waiter = new WebDriverWait(driver, Duration.ofSeconds(10));
        waiter.until(ExpectedConditions.urlToBe(webpage));
        
        return driver.getCurrentUrl();
    }
}
