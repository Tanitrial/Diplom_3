import pages.*;
import step.*;
import base.UserLogin;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.*;

import static org.junit.Assert.*;


public class RegisterTest {
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private UserLogin userCreate;
    private UserLogin userLogin;
    private UserPreStep userPrestep;
    private String incorrectPassword;

    @Rule
    public DriverStep driverStep = new DriverStep();

    @Before
    public void setUp() {
        WebDriver driver = driverStep.getDriver();
        registerPage = new RegisterPage(driver);
        registerPage.openRegistrationPage();
        loginPage = new LoginPage(driver);

        Faker faker = new Faker();
        userCreate = new UserLogin(faker.internet().emailAddress(), faker.internet().password(6, 12), faker.name().fullName());
        userLogin = new UserLogin(userCreate.getEmail(), userCreate.getPassword());
        incorrectPassword = faker.internet().password(1, 5);
        userPrestep = new UserPreStep();
    }

    @Test
    @DisplayName("Register user happy path")
    public void registrationTest() {
        registerPage.registration(userCreate.getName(), userCreate.getEmail(), userCreate.getPassword());
        loginPage.waitEntranceDisplayed();

        String expected = "Вход";
        String actual = loginPage.getEntranceText();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Register with incorrect password")
    public void registrationNegative() {
        registerPage.registration(userCreate.getName(), userCreate.getEmail(), incorrectPassword);

        String expected = "Некорректный пароль";
        String actual = registerPage.getErrorMessage();

        assertEquals(expected, actual);
    }

    @After
    public void tearDown() {
        userPrestep.setUserLogin(userLogin);
        userPrestep.deleteUser();
    }
}