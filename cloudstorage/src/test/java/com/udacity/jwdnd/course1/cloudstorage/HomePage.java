package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HomePage {

    @FindBy(css="#logout-btn")
    private WebElement logOutBtn;

    @FindBy(css="#fileUpload")
    private WebElement fileUploadInput;

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

    @FindBy(css="#no-notes-available-msg")
    private WebElement noNotesAvailableMsg;

    @FindBy(css="#credential-url")
    private WebElement credentialUrlInput;

    @FindBy(css="#credential-username")
    private WebElement credentialUsernameInput;

    @FindBy(css="#credential-password")
    private WebElement credentialPasswordInput;

    @FindBy(css="#credential-submit-btn")
    private WebElement credentialSubmitBtn;

    @FindBy(css="#credentials-table")
    private WebElement credentialsTable;

    @FindBy(css="#no-credentials-available-msg")
    private WebElement noCredentialsAvailableMsg;

    @FindBy(css=".credential")
    private List<WebElement> credentials;

    @FindBy(css="#files-table")
    private WebElement filesTable;

    @FindBy(css=".file")
    private List<WebElement> files;

    @FindBy(css="#no-files-available-msg")
    private WebElement noFilesAvailableMsg;

    @FindBy(css="#file-action-error-msg")
    private WebElement fileActionErrorMsg;

    @FindBy(css="#file-action-success-msg")
    private WebElement fileActionSuccessMsg;

    @FindBy(css="#note-action-success-msg")
    private WebElement noteActionSuccessMsg;

    @FindBy(css="#credential-action-success-msg")
    private WebElement credentialActionSuccessMsg;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void addNote(String title, String description) {
        noteTitleInput.clear();
        noteTitleInput.sendKeys(title);
        noteDescriptionInput.clear();
        noteDescriptionInput.sendKeys(description);
        noteSubmitBtn.click();
    }

    public void addCredential(String url, String username, String password) {
        credentialUrlInput.clear();
        credentialUrlInput.sendKeys(url);
        credentialUsernameInput.clear();
        credentialUsernameInput.sendKeys(username);
        credentialPasswordInput.clear();
        credentialPasswordInput.sendKeys(password);
        credentialSubmitBtn.click();
    }

    public String getFileName() {
        return "sample-image.jpeg";
    }

    private String getFilePath(String fileName) {
        String currPath = System.getProperty("user.dir") + "/src/test/java/com/udacity/jwdnd/course1/cloudstorage";
        return currPath +"/" + fileName;
    }

    public void uploadFile() {
        File imgFile = new File(getFilePath(getFileName()));
        fileUploadInput.clear();
        fileUploadInput.sendKeys(imgFile.getAbsolutePath());
        uploadBtn.click();
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

    public List<WebElement> getCredentials() {
        return credentials;
    }

    public WebElement getCredentialEditBtn(WebElement credential) {
        return credential.findElement(By.className("credential-edit-btn"));
    }

    public WebElement getCredentialDeleteBtn(WebElement credential) {
        return credential.findElement(By.className("credential-delete-btn"));
    }

    public WebElement getCredentialUrl(WebElement credential) {
        return credential.findElement(By.className("credential-url"));
    }

    public WebElement getCredentialUsername(WebElement credential) {
        return credential.findElement(By.className("credential-username"));
    }

    public WebElement getCredentialPassword(WebElement credential) {
        return credential.findElement(By.className("credential-password"));
    }

    public List<WebElement> getFiles() {
        return files;
    }

    public WebElement getFileViewBtn(WebElement file) {
        return file.findElement(By.className("file-view-btn"));
    }

    public WebElement getFileDeleteBtn(WebElement file) {
        return file.findElement(By.className("file-delete-btn"));
    }

    public WebElement getFileName(WebElement file) {
        return file.findElement(By.className("file-name"));
    }

    public WebElement getLogOutBtn() {
        return logOutBtn;
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

    public WebElement getCredentialsTable() {
        return credentialsTable;
    }

    public WebElement getNoNotesAvailableMsg() {
        return noNotesAvailableMsg;
    }

    public WebElement getCredentialUrlInput() {
        return credentialUrlInput;
    }

    public WebElement getCredentialPasswordInput() {
        return credentialPasswordInput;
    }

    public WebElement getNoCredentialsAvailableMsg() {
        return noCredentialsAvailableMsg;
    }

    public WebElement getNoFilesAvailableMsg() {
        return noFilesAvailableMsg;
    }

    public WebElement getFilesTable() {
        return filesTable;
    }

    public WebElement getFileActionErrorMsg() {
        return fileActionErrorMsg;
    }

    public WebElement getFileActionSuccessMsg() {
        return fileActionSuccessMsg;
    }

    public WebElement getNoteActionSuccessMsg() {
        return noteActionSuccessMsg;
    }

    public WebElement getCredentialActionSuccessMsg() {
        return credentialActionSuccessMsg;
    }
}
