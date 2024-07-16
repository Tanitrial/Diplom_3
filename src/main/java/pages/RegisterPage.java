package pages;

import io.qameta.allure.Step;
import lombok.Data;
import org.openqa.selenium.*;

import static base.Url.REGISTER_PAGE;

@Data
public class RegisterPage {
    private final WebDriver driver;
    private final By name = By.xpath(".//label[text()='Имя']/parent::div//input");
    private final By email = By.xpath(".//label[text()='Email']/parent::div//input");
    private final By password = By.xpath(".//label[text()='Пароль']/parent::div//input");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By errorMessage = By.xpath(".//p[text()='Некорректный пароль']");
    private final By loginButton = By.xpath(".//a[text() = 'Войти']");

    @Step("Input user name")
    public void setName(String userName) {
        driver.findElement(name).sendKeys(userName);
    }

    @Step("Input user email")
    public void setEmail(String userEmail) {
        driver.findElement(email).sendKeys(userEmail);
    }

    @Step("Input user password")
    public void setPassword(String userPassword) {
        driver.findElement(password).sendKeys(userPassword);
    }

    @Step("Click register button")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Registration user")
    public void registration(String userName, String userEmail, String userPassword) {
        setName(userName);
        setEmail(userEmail);
        setPassword(userPassword);
        clickRegisterButton();
    }

    @Step("Get error message")
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    @Step("Open registration page")
    public void openRegistrationPage() {
        driver.get(REGISTER_PAGE);
    }

    @Step("Click login button")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}