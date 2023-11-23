package com.globant.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArticlePage extends BasePage {

    @FindBy(css = ".mw-page-title-main")
    private WebElement pageTitle;
    @FindBy(css = "#mw-content-subtitle a")
    private WebElement redirectLabel;
    @FindBy(css = "#ca-edit a")
    private WebElement editBtn;
    @FindBy(css = "#ca-history")
    private WebElement historyBtn;

    public ArticlePage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        waitVisibilityOf(pageTitle);
        return pageTitle.getText();
    }

    public String getRedirectLabel() {
        waitVisibilityOf(redirectLabel);
        return redirectLabel.getText();
    }

    public EditPage goToEditPage() {
        clickOn(editBtn);
        return new EditPage(driver);
    }

    public HistoryPage goToHistoryPage() {
        clickOn(historyBtn);
        return new HistoryPage(driver);
    }
}
