package pages;

import io.qameta.allure.Step;
import lombok.Data;
import org.openqa.selenium.*;

import static base.Url.FORGOT_PASSWORD_PAGE;

@Data
public class ForgotPasswordPage {
    private final WebDriver driver;
    private final By loginButton = By.xpath(".//a[text() = 'Войти']");

    @Step("Click login button")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Open forgot password page")
    public void openForgotPasswordPage(){
        driver.get(FORGOT_PASSWORD_PAGE);
    }


}