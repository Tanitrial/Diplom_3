import pages.*;
import step.*;
import base.UserLogin;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.*;
import static org.junit.Assert.*;

public class AccountUserTest {
    private AccountUserPage accountUserPage;
    private MainPage mainPage;
    private LoginPage loginPage;
    private UserLogin userLogin;
    private UserPreStep userPreStep;

    @Rule
    public DriverStep driverStep = new DriverStep();

    @Before
    public void setUp() {
        WebDriver driver = driverStep.getDriver();
        accountUserPage = new AccountUserPage(driver);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        mainPage.openMainPage();

        Faker faker = new Faker();
        UserLogin userForCreate = new UserLogin(faker.internet().emailAddress(), faker.internet().password(6, 12), faker.name().fullName());
        userLogin = new UserLogin(userForCreate.getEmail(), userForCreate.getPassword());
        userPreStep = new UserPreStep();
        userPreStep.setUser(userForCreate);
        userPreStep.createUser();

        mainPage.clickAccountProfileButton();
        loginPage.loginUser(userLogin.getEmail(), userLogin.getPassword());
        mainPage.clickAccountProfileButton();
    }

    @Test
    @DisplayName("Check account profile for authorized user")
    public void checkAccountProfile() {
        accountUserPage.waitProfileTab();

        String expected = "Профиль";
        String actual = accountUserPage.getProfileTabText();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Open constructor from account profile")
    public void openConstructorFromProfile() {
        accountUserPage.clickConstructorLink();

        String expected = "Соберите бургер";
        String actual = mainPage.getMainTextConstructor();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Open main page from account profile")
    public void openMainPageFromAccount() {
        accountUserPage.clickLogoLink();

        String expected = "Соберите бургер";
        String actual = mainPage.getMainTextConstructor();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Logout from account profile")
    public void logoutFromAccount() {
        accountUserPage.waitLogoutButton();
        accountUserPage.clickLogoutButton();
        loginPage.waitEntranceDisplayed();

        String expected = "Вход";
        String actual = loginPage.getEntranceText();

        assertEquals(expected, actual);
    }

    @After
    public void tearDown() {
        userPreStep.setUserLogin(userLogin);
        userPreStep.deleteUser();
    }
}