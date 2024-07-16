package pages;

import io.qameta.allure.Step;
import lombok.Data;
import org.openqa.selenium.*;
import static base.Url.*;


@Data
public class AccountUserPage {
    private WebDriver driver;
    private static final By profileTab = By.xpath(".//*[text() = 'Профиль']");
    private final By logoutButton = By.xpath(".//button[text() = 'Выход']");
    private final By constructorLink = By.xpath(".//*[text() = 'Конструктор']");
    private final By logoLink = By.className("AppHeader_header__logo__2D0X2");

    public AccountUserPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Get profileTab text")
    public String getProfileTabText() {
        return driver.findElement(profileTab).getText();
    }

    @Step("Click logoutButton")
    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    @Step("Click constructorLink")
    public void clickConstructorLink() {
        driver.findElement(constructorLink).click();
    }

    @Step("Click logoLink")
    public void clickLogoLink() {
        driver.findElement(logoLink).click();
    }

    @Step("Wait for visible of profileTab")
    public void waitProfileTab() {
        waitOfVisibleElement(driver, profileTab);
    }

    @Step("Wait for visible logoutButton")
    public void waitLogoutButton() {
        waitOfVisibleElement(driver, logoutButton);
    }
}