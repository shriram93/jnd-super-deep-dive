package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(css="#logout-btn")
    private WebElement logOutBtn;

    @FindBy(css="#fileUpload")
    private WebElement fileUploadBtn;

    @FindBy(css="#upload-btn")
    private WebElement uploadBtn;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public WebElement getLogOutBtn() {
        return logOutBtn;
    }

    public WebElement getUploadBtn() {
        return uploadBtn;
    }

    public WebElement getFileUploadBtn() {
        return fileUploadBtn;
    }
}
