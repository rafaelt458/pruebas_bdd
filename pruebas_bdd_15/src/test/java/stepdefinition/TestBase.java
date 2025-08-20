package stepdefinition;

import adapter.ElementInteraction;
import adapter.SeleniumElementInteraction;
import org.openqa.selenium.WebDriver;
import pom.DetailPage;
import pom.LoginPage;
import pom.MainPage;
import util.TestConfig;

import java.io.IOException;

public class TestBase {
    protected LoginPage loginPage;
    protected MainPage mainPage;
    protected DetailPage detailPage;

    public TestBase() throws IOException {
        WebDriver driver = Hooks.getDriver();
        ElementInteraction elementInteraction = new SeleniumElementInteraction(driver);

        this.loginPage = new LoginPage("./config/loginpage.json", elementInteraction);
        this.mainPage = new MainPage("./config/mainpage.json", elementInteraction);
        this.detailPage = new DetailPage("./config/detailpage.json", elementInteraction);

        String url;
        try {
            url = TestConfig.readProperty("urlbase");
            if (url == null) {
                url = "https://www.saucedemo.com/";
            }
        } catch (Exception e) {
            url = "https://www.saucedemo.com/";
        }
        driver.get(url);
    }
}
