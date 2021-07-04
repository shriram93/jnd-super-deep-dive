package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	public String baseURL;

	public WebDriverWait wait;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		baseURL = "http://localhost:" + port;
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 5);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	private void loginUser() {
		String firstName = "firstName";
		String lastName = "lastName";
		String username = "userName";
		String password = "password";
		driver.get(baseURL + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password);
		driver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
	}

	@Order(1)
	@Test
	public void testLoginAndSignupPage() {
		String firstName = "firstName";
		String lastName = "lastName";
		String username = "userName";
		String password = "password";

		// Invalid user
		driver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
		assertEquals("Invalid username or password",
				loginPage.getInvalidUsernameOrPasswordMessage().getText());
		loginPage.getSignupLink().click();
		assertEquals(baseURL + "/signup", driver.getCurrentUrl());

		// Signup
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password);
		assertEquals("You successfully signed up! Please continue to the login page.",
				signupPage.getSignupSuccessMessage().getText());

		// Signup user already exists
		signupPage.signup(firstName, lastName, username, password);
		assertEquals("The username already exists.",
				signupPage.getSignupErrorMessage().getText());
		signupPage.getBackToLoginLink().click();
		assertEquals(baseURL + "/login", driver.getCurrentUrl());

		// Login
		loginPage = new LoginPage(driver);
		loginPage.login(username, password);
		assertEquals(baseURL + "/home/files", driver.getCurrentUrl());
	}

	@Order(2)
	@Test
	public void testHome() {
		loginUser();

		// Logout
		HomePage homePage = new HomePage(driver);
		homePage.getLogOutBtn().click();
		assertEquals(baseURL + "/login", driver.getCurrentUrl());

		// Nav tabs
		loginUser();
		assertEquals(baseURL + "/home/files", driver.getCurrentUrl());

		homePage.getNavbarNotesTab().click();
		assertEquals(baseURL + "/home/notes", driver.getCurrentUrl());

		homePage.getNavbarCredentialsTab().click();
		assertEquals(baseURL + "/home/credentials", driver.getCurrentUrl());

		homePage.getNavbarFilesTab().click();
		assertEquals(baseURL + "/home/files", driver.getCurrentUrl());
	}

	@Order(3)
	@Test
	public void testFiles() {
		loginUser();
		HomePage homePage = new HomePage(driver);
		homePage.getNavbarFilesTab().click();
		wait.until(ExpectedConditions.visibilityOf(homePage.getNoFilesAvailableMsg()));

		// Add file
		homePage.uploadFile();
		wait.until(ExpectedConditions.visibilityOf(homePage.getFilesTable()));
		List<WebElement> files = homePage.getFiles();
		assertEquals(1, files.size());
		WebElement file = files.get(0);
		assertEquals(homePage.getFileName(), homePage.getFileName(file).getText());

		// View file
		homePage.getFileViewBtn(file).click();
		List<String> tabs = new ArrayList(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		assertEquals(baseURL + "/home/files/view/1", driver.getCurrentUrl());
		driver.close();
		driver.switchTo().window(tabs.get(0));

		// File with same name can't be upload twice
		homePage.uploadFile();
		wait.until(ExpectedConditions.visibilityOf(homePage.getFilesTable()));
		assertEquals("File with same name already exists.", homePage.getFileActionErrorMsg().getText());

		// Delete file
		files = homePage.getFiles();
		file = files.get(0);
		homePage.getFileDeleteBtn(file).click();
		wait.until(ExpectedConditions.visibilityOf(homePage.getNoFilesAvailableMsg()));
		files = homePage.getFiles();
		assertEquals(0, files.size());
		assertEquals("No files available", homePage.getNoFilesAvailableMsg().getText());
	}

	@Order(4)
	@Test
	public void testNotes() {
		loginUser();
		HomePage homePage = new HomePage(driver);
		homePage.getNavbarNotesTab().click();
		wait.until(ExpectedConditions.visibilityOf(homePage.getNoNotesAvailableMsg()));

		// Add note
		homePage.getAddNoteBtn().click();
		wait.until(ExpectedConditions.visibilityOf(homePage.getNoteTitleInput()));
		String noteTitle = "note title";
		String noteDescription = "note desc";
		homePage.addNote(noteTitle, noteDescription);
		wait.until(ExpectedConditions.visibilityOf(homePage.getNotesTable()));
		List<WebElement> notes = homePage.getNotes();
		WebElement note = notes.get(0);
		assertEquals(1, notes.size());
		assertEquals(noteTitle, homePage.getNoteTitle(note).getText());
		assertEquals(noteDescription, homePage.getNoteDescription(note).getText());

		// Edit note
		homePage.getNoteEditBtn(note).click();
		wait.until(ExpectedConditions.visibilityOf(homePage.getNoteTitleInput()));
		noteTitle = "note title modified";
		noteDescription = "note desc modified";
		homePage.addNote(noteTitle, noteDescription);
		wait.until(ExpectedConditions.visibilityOf(homePage.getNotesTable()));
		notes = homePage.getNotes();
		note = notes.get(0);
		assertEquals(noteTitle, homePage.getNoteTitle(note).getText());
		assertEquals(noteDescription, homePage.getNoteDescription(note).getText());

		// Delete note
		homePage.getNoteDeleteBtn(note).click();
		wait.until(ExpectedConditions.visibilityOf(homePage.getNoNotesAvailableMsg()));
		notes = homePage.getNotes();
		assertEquals(0, notes.size());
		assertEquals("No notes available", homePage.getNoNotesAvailableMsg().getText());
	}

	@Order(5)
	@Test
	public void testCredentials() {
		loginUser();
		HomePage homePage = new HomePage(driver);
		homePage.getNavbarCredentialsTab().click();
		wait.until(ExpectedConditions.visibilityOf(homePage.getNoCredentialsAvailableMsg()));

		// Add credentials
		homePage.getAddCredentialBtn().click();
		wait.until(ExpectedConditions.visibilityOf(homePage.getCredentialUrlInput()));
		String credentialUrl = "cred url";
		String credentialUsername = "cred username";
		String credentialPassword = "cred password";
		homePage.addCredential(credentialUrl, credentialUsername, credentialPassword);
		wait.until(ExpectedConditions.visibilityOf(homePage.getCredentialsTable()));
		List<WebElement> credentials = homePage.getCredentials();
		assertEquals(1, credentials.size());
		WebElement credential = credentials.get(0);
		assertEquals(credentialUrl, homePage.getCredentialUrl(credential).getText());
		assertEquals(credentialUsername, homePage.getCredentialUsername(credential).getText());
		// Password is encrypted
		assertNotEquals(credentialPassword, homePage.getCredentialPassword(credential).getText());

		// Edit credentials
		homePage.getCredentialEditBtn(credential).click();
		wait.until(ExpectedConditions.visibilityOf(homePage.getCredentialUrlInput()));
		// On edit, decrypted password will be shown
		assertEquals(credentialPassword, homePage.getCredentialPasswordInput().getAttribute("value"));
		credentialUrl = "modified cred url";
		credentialUsername = "modified cred username";
		credentialPassword = "modified cred password";
		homePage.addCredential(credentialUrl, credentialUsername, credentialPassword);
		wait.until(ExpectedConditions.visibilityOf(homePage.getCredentialsTable()));
		credentials = homePage.getCredentials();
		credential = credentials.get(0);
		assertEquals(credentialUrl, homePage.getCredentialUrl(credential).getText());
		assertEquals(credentialUsername, homePage.getCredentialUsername(credential).getText());
		// Password is encrypted
		assertNotEquals(credentialPassword, homePage.getCredentialPassword(credential).getText());

		// Delete credentials
		homePage.getCredentialDeleteBtn(credential).click();
		wait.until(ExpectedConditions.visibilityOf(homePage.getNoCredentialsAvailableMsg()));
		credentials = homePage.getCredentials();
		assertEquals(0, credentials.size());
		assertEquals("No credentials available", homePage.getNoCredentialsAvailableMsg().getText());
	}

	@Order(6)
	@Test
	public void testAccessRestrictions() {
		// Without login, used should have access only to login and signup page
		driver.get(baseURL + "/login");
		assertEquals(baseURL + "/login", driver.getCurrentUrl());
		driver.get(baseURL + "/signup");
		assertEquals(baseURL + "/signup", driver.getCurrentUrl());
		driver.get(baseURL + "/home");
		assertEquals(baseURL + "/login", driver.getCurrentUrl());
		driver.get(baseURL + "/home/files");
		assertEquals(baseURL + "/login", driver.getCurrentUrl());
		driver.get(baseURL + "/home/files/view/1");
		assertEquals(baseURL + "/login", driver.getCurrentUrl());
		driver.get(baseURL + "/home/notes");
		assertEquals(baseURL + "/login", driver.getCurrentUrl());
		driver.get(baseURL + "/home/credentials");
		assertEquals(baseURL + "/login", driver.getCurrentUrl());

		// Check if home page is accessible after logout
		loginUser();
		assertEquals(baseURL + "/home/files", driver.getCurrentUrl());
		HomePage homePage = new HomePage(driver);
		homePage.getLogOutBtn().click();
		assertEquals(baseURL + "/login", driver.getCurrentUrl());
		driver.get(baseURL + "/home");
		assertEquals(baseURL + "/login", driver.getCurrentUrl());
	}
}
