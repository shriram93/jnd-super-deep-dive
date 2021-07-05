package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    @FindBy(css="#input-first-name")
    private WebElement firstNameField;

    @FindBy(css="#input-last-name")
    private WebElement lastNameField;

    @FindBy(css="#input-username")
    private WebElement usernameField;

    @FindBy(css="#input-password")
    private WebElement passwordField;

    @FindBy(css="#submit-btn")
    private WebElement submitButton;

    @FindBy(css="#signup-error-msg")
    private WebElement signupErrorMessage;

    @FindBy(css="#back-to-login-link")
    private WebElement backToLoginLink;

    public SignupPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void signup(String firstName, String lastName,String username, String password) {
        this.firstNameField.sendKeys(firstName);
        this.lastNameField.sendKeys(lastName);
        this.usernameField.sendKeys(username);
        this.passwordField.sendKeys(password);
        this.submitButton.click();
    }

    public WebElement getSignupErrorMessage() {
        return signupErrorMessage;
    }

    public WebElement getBackToLoginLink() {
        return backToLoginLink;
    }
}
