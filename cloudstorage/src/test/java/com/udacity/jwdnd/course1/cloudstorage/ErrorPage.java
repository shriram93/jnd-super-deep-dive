package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ErrorPage {
    @FindBy(css="#home-redirect-btn")
    private WebElement homeRedirectBtn;

    public ErrorPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public WebElement getHomeRedirectBtn() {
        return homeRedirectBtn;
    }
}
