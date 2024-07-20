import pages.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.*;
import step.DriverStep;

import static org.junit.Assert.*;

public class MainTest {
    private MainPage mainPage;

    @Rule
    public DriverStep driverStep = new DriverStep();

    @Before
    public void setUp() {
        WebDriver driver = driverStep.getDriver();
        mainPage = new MainPage(driver);
        mainPage.openMainPage();
    }

    @Test
    @DisplayName("Bun tab should be selected")
    public void bunTabShouldBeSelected() {
        mainPage.clickSauceTab();
        mainPage.clickBunTab();
        assertTrue(mainPage.isBunTabSelected());
    }

    @Test
    @DisplayName("Sauce tab should be selected")
    public void sauceTabShouldBeSelected() {
        mainPage.clickSauceTab();
        assertTrue(mainPage.isSauceTabSelected());
    }

    @Test
    @DisplayName("Filling tab should be selected")
    public void fillingTabShouldBeSelected() {
        mainPage.clickFillingTab();
        assertTrue(mainPage.isFillingTabSelected());
    }
}
