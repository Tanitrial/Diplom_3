import pages.*;
import step.*;
import base.UserLogin;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.*;


import static org.junit.Assert.*;

public class LoginTest {
    private LoginPage loginPage;
    private MainPage mainPage;
    private RegisterPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;
    private UserLogin userLogin;
    private UserPreStep userPreStep;

    @Rule
    public DriverStep driverStep = new DriverStep();

    @Before
    public void setUp() {
        WebDriver driver = driverStep.getDriver();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        registerPage = new RegisterPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);

        Faker faker = new Faker();
        UserLogin userForCreate = new UserLogin(faker.internet().emailAddress(), faker.internet().password(6, 12), faker.name().fullName());
        userLogin = new UserLogin(userForCreate.getEmail(), userForCreate.getPassword());
        userPreStep = new UserPreStep();
        userPreStep.setUser(userForCreate);
        userPreStep.createUser();
    }

    @Test
    @DisplayName("Login user via loginAccountButton")
    public void loginUserViaLoginAccountButton() {
        mainPage.openMainPage();
        mainPage.clickLoginAccountButton();
        loginPage.loginUser(userLogin.getEmail(), userLogin.getPassword());
        mainPage.waitCreateOrderButton();

        String expected = "Оформить заказ";
        String actual = mainPage.getCreateOrderButtonText();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Login user via accountProfileButton")
    public void loginUserViaAccountProfileButton() {
        mainPage.openMainPage();
        mainPage.clickAccountProfileButton();
        loginPage.loginUser(userLogin.getEmail(), userLogin.getPassword());
        mainPage.waitCreateOrderButton();

        String expected = "Оформить заказ";
        String actual = mainPage.getCreateOrderButtonText();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Login user via RegistrationPage")
    public void loginUserViaRegistrationPage() {
        registerPage.openRegistrationPage();
        registerPage.clickLoginButton();
        loginPage.loginUser(userLogin.getEmail(), userLogin.getPassword());
        mainPage.waitCreateOrderButton();

        String expected = "Оформить заказ";
        String actual = mainPage.getCreateOrderButtonText();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Login user via ForgotPasswordPage")
    public void loginViaForgotPasswordPage() {
        forgotPasswordPage.openForgotPasswordPage();
        forgotPasswordPage.clickLoginButton();
        loginPage.loginUser(userLogin.getEmail(), userLogin.getPassword());
        mainPage.waitCreateOrderButton();

        String expected = "Оформить заказ";
        String actual = mainPage.getCreateOrderButtonText();

        assertEquals(expected, actual);
    }

    @After
    public void tearDown() {
        userPreStep.setUserLogin(userLogin);
        userPreStep.deleteUser();
    }
}