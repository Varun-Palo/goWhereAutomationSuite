package com.pages;

import com.qa.factory.DriverFactory;
import com.qa.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class BaseClass {

	Properties configProperties = new PropertiesReader().getProperties("./src/test/resources/config/Config.properties");

	public WebDriver driver;

	public BaseClass() {
		this.driver = DriverFactory.getDriver();
	}

	public WebDriverWait getWebDriverWait() {
		return new WebDriverWait(driver, Duration.ofSeconds(Integer.valueOf(configProperties.getProperty("maxTimeout"))));
	}

	public WebElement waitForElementToBeClickable(By locator) {
		return getWebDriverWait().until(ExpectedConditions.elementToBeClickable(locator));
	}

	public WebElement waitForElementToBeVisible(By locator) {
		return getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void doClick(By locator){
		driver.findElement(locator).click();
	}

}
