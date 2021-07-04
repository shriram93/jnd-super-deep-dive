package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	public String baseURL;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		baseURL = "http://localhost:" + port;
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
//		if (this.driver != null) {
//			driver.quit();
//		}
	}

	private void signupAndLogin() {
		String firstName = "testFirstName";
		String lastName = "testLastName";
		String username = "testUsername";
		String password = "testPassword";
		driver.get(baseURL + "/signup");

		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password);
		driver.get(baseURL + "/login");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
	}

	@Test
	public void loginInvalid() {
		String username = "testUsername";
		String password = "testPassword";
		driver.get(baseURL + "/login");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		assertEquals("Invalid username or password",
				loginPage.getInvalidUsernameOrPasswordMessage().getText());
	}

	@Test
	public void loginSuccess() {
		signupAndLogin();

		assertEquals(baseURL + "/home/files", driver.getCurrentUrl());
	}

	@Test
	public void signupSuccess() {
		String firstName = "testFirstName";
		String lastName = "testLastName";
		String username = "testUsername";
		String password = "testPassword";
		driver.get(baseURL + "/signup");

		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password);

		assertEquals("You successfully signed up! Please continue to the login page.",
				signupPage.getSignupSuccessMessage().getText());
	}

	@Test
	public void signupUserAlreadyExistsSignup() {
		String firstName = "testFirstName";
		String lastName = "testLastName";
		String username = "testUsername";
		String password = "testPassword";
		driver.get(baseURL + "/signup");

		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password);
		signupPage.signup(firstName, lastName, username, password);

		assertEquals("The username already exists.",
				signupPage.getSignupErrorMessage().getText());
	}

	@Test
	public void homeLogoutSuccess() {
		signupAndLogin();

		HomePage homePage = new HomePage(driver);
		homePage.getLogOutBtn().click();
		assertEquals(baseURL + "/login", driver.getCurrentUrl());
	}

}
