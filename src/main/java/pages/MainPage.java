package pages;

import io.qameta.allure.Step;
import lombok.Data;
import org.openqa.selenium.*;

import static base.Url.BASE_URI;
import static base.Url.waitOfVisibleElement;

@Data
public class MainPage {
    private final WebDriver driver;
    private final By loginAccountButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private final By accountProfileButton = By.xpath(".//p[text() = 'Личный Кабинет']");
    private final By createOrderButton = By.xpath(".//button[text() = 'Оформить заказ']");
    private final By bunTab = By.xpath(".//span[text() = 'Булки']/parent::div");
    private final By sauceTab = By.xpath(".//span[text() = 'Соусы']/parent::div");
    private final By fillingTab = By.xpath(".//span[text() = 'Начинки']/parent::div");
    private final By activeTab = By.xpath(".//div[contains(@class, 'current')]");
    private final By mainTextConstructor = By.xpath(".//*[text() = 'Соберите бургер']");

    @Step("Login via loginAccountButton")
    public void clickLoginAccountButton() {
        driver.findElement(loginAccountButton).click();
    }

    @Step("Login via accountProfileButton")
    public void clickAccountProfileButton() {
        driver.findElement(accountProfileButton).click();
    }

    @Step("Get create order button text")
    public String getCreateOrderButtonText() {
        return driver.findElement(createOrderButton).getText();
    }

    @Step("Pick bun")
    public void clickBunTab() {
        driver.findElement(bunTab).click();
    }

    @Step("Get bun text")
    public String getBunText() {
        return driver.findElement(bunTab).getText();
    }

    @Step("Pick sauce")
    public void clickSauceTab() {
        driver.findElement(sauceTab).click();
    }

    @Step("Get sauce text")
    public String getSauceText() {
        return driver.findElement(sauceTab).getText();
    }

    @Step("Pick filling")
    public void clickFillingTab() {
        driver.findElement(fillingTab).click();
    }

    @Step("Get filling text")
    public String getFillingText() {
        return driver.findElement(fillingTab).getText();
    }

    @Step("Get active tab text")
    public String getActiveTabText() {
        return driver.findElement(activeTab).getText();
    }

    @Step("Is bun tab selected?")
    public boolean isBunTabSelected() {
        return getBunText().equals(getActiveTabText());
    }

    @Step("Is sauce tab selected?")
    public boolean isSauceTabSelected() {
        return getSauceText().equals(getActiveTabText());
    }

    @Step("Is filling tab selected?")
    public boolean isFillingTabSelected() {
        return getFillingText().equals(getActiveTabText());
    }

    @Step("Get main text")
    public String getMainTextConstructor() {
        return driver.findElement(mainTextConstructor).getText();
    }

    @Step("Wait for visible of createOrderButton")
    public void waitCreateOrderButton() {
        waitOfVisibleElement(driver, createOrderButton);
    }

    @Step("Open main page")
    public void openMainPage() {
        driver.get(BASE_URI);
    }


}