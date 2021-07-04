package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(css="#input-username")
    private WebElement usernameField;

    @FindBy(css="#input-password")
    private WebElement passwordField;

    @FindBy(css="#submit-btn")
    private WebElement submitButton;

    @FindBy(css="#invalid-username-or-password-msg")
    private WebElement invalidUsernameOrPasswordMessage;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void login(String username, String password) {
        this.usernameField.sendKeys(username);
        this.passwordField.sendKeys(password);
        this.submitButton.click();
    }

    public WebElement getInvalidUsernameOrPasswordMessage() {
        return invalidUsernameOrPasswordMessage;
    }
}
