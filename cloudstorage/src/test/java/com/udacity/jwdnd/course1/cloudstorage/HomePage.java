package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {

    @FindBy(css="#logout-btn")
    private WebElement logOutBtn;

    @FindBy(css="#fileUpload")
    private WebElement fileUploadBtn;

    @FindBy(css="#upload-btn")
    private WebElement uploadBtn;

    @FindBy(css="#nav-files-tab")
    private WebElement navbarFilesTab;

    @FindBy(css="#nav-notes-tab")
    private WebElement navbarNotesTab;

    @FindBy(css="#nav-credentials-tab")
    private WebElement navbarCredentialsTab;

    @FindBy(css="#add-note-btn")
    private WebElement addNoteBtn;

    @FindBy(css="#add-credential-btn")
    private WebElement addCredentialBtn;

    @FindBy(css="#note-title")
    private WebElement noteTitleInput;

    @FindBy(css="#note-description")
    private WebElement noteDescriptionInput;

    @FindBy(css="#note-submit-btn")
    private WebElement noteSubmitBtn;

    @FindBy(css="#notes-table")
    private WebElement notesTable;

    @FindBy(css=".note")
    private List<WebElement> notes;

    @FindBy(css="#credential-url")
    private WebElement credentialUrlInput;

    @FindBy(css="#credential-username")
    private WebElement credentialUsernameInput;

    @FindBy(css="#credential-password")
    private WebElement credentialPasswordInput;

    @FindBy(css="#credential-submit-btn")
    private WebElement credentialSubmitBtn;

    @FindBy(css="#credentials-table")
    private WebElement crdentialsTable;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void addNote(String title, String description) {
        noteTitleInput.sendKeys(title);
        noteDescriptionInput.sendKeys(description);
        noteSubmitBtn.click();
    }

    public List<WebElement> getNotes() {
        return notes;
    }

    public WebElement getNoteEditBtn(WebElement note) {
        return note.findElement(By.className("note-edit-btn"));
    }

    public WebElement getNoteDeleteBtn(WebElement note) {
        return note.findElement(By.className("note-delete-btn"));
    }

    public WebElement getNoteTitle(WebElement note) {
        return note.findElement(By.className("note-title"));
    }

    public WebElement getNoteDescription(WebElement note) {
        return note.findElement(By.className("note-description"));
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

    public WebElement getNavbarFilesTab() {
        return navbarFilesTab;
    }

    public WebElement getNavbarNotesTab() {
        return navbarNotesTab;
    }

    public WebElement getNavbarCredentialsTab() {
        return navbarCredentialsTab;
    }

    public WebElement getAddNoteBtn() {
        return addNoteBtn;
    }

    public WebElement getAddCredentialBtn() {
        return addCredentialBtn;
    }

    public WebElement getNoteTitleInput() {
         return noteTitleInput;
    }

    public WebElement getNotesTable() {
        return notesTable;
    }

    public WebElement getCrdentialsTable() {
        return crdentialsTable;
    }
}
