package com.globant.pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected Wait<WebDriver> wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    protected void clickOn(WebElement element) {
        try {
            waitToBeClickable(element);
            element.click();
        } catch (StaleElementReferenceException e) {
            PageFactory.initElements(driver, this);
            clickOn(element);
        }
    }

    protected void writeIn(WebElement element, String text) {
        try {
            waitVisibilityOf(element);
            element.sendKeys(text);
        } catch (StaleElementReferenceException e) {
            PageFactory.initElements(driver, this);
            writeIn(element, text);
        }
    }

    protected void waitToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitVisibilityOf(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void ignorePopup(WebElement element) {
        try {
            waitVisibilityOf(element);
        } catch (TimeoutException e) {
            driver.switchTo().defaultContent();
            ignorePopup(element);
        }
    }
}
