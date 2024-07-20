package step;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

@Getter
public class DriverStep extends ExternalResource {
    private WebDriver driver;

    @Override
    protected void before(){
        initDriver();
    }

    @Override
    protected void after(){
        driver.quit();
    }

    public void initDriver(){
        if("yabrowser".equals(System.getProperty("browser"))){
            initYaBrowser();
        } else {
            initChrome();
        }
    }

    private void initChrome(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    private void initYaBrowser(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary(System.getProperty("path"));
        driver = new ChromeDriver(chromeOptions);
    }
}
