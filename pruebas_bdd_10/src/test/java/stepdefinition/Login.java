package stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.JsonLocatorManager;
import util.Locator;
import util.TestConfig;

import java.io.IOException;
import java.util.Map;

public class Login {
    public static WebDriver driver;
    private static Map<String, Locator> homepageLocators;
    private static Map<String, Locator> mainpageLocators;

    private final String usernameFieldKey = "usernameField";
    private final String passwordFieldKey = "passwordField";
    private final String loginButtonKey = "loginButton";
    private final String pageTittleKey = "pageTittle";
    private final String pageTittleText = "Swag Labs";
    private final String errorMessageKey = "errorMessage";
    private final String wrongCredentialsText = "Epic sadface: Username and password do not match any user in this service";
    private final String emptyUsernameText = "Epic sadface: Username is required";
    private final String emptyPasswordText = "Epic sadface: Password is required";

    public Login() throws IOException {
        driver = Hooks.getDriver();
        homepageLocators = JsonLocatorManager.getLocators("./config/homepage.json");
        mainpageLocators = JsonLocatorManager.getLocators("./config/mainpage.json");
    }

    @Given("el usuario esta en la pagina de login")
    public void elUsuarioEstaEnLaPaginaDeLogin() {
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

    @When("escribe el nombre de usuario {string}")
    public void escribeElNombreDeUsuario(String username) {
        WebElement webElement = driver.findElement(JsonLocatorManager.getLocator(homepageLocators, usernameFieldKey));
        webElement.sendKeys(username);
    }

    @When("escribe la clave {string}")
    public void escribeLaClave(String password) {
        WebElement webElement = driver.findElement(JsonLocatorManager.getLocator(homepageLocators, passwordFieldKey));
        webElement.sendKeys(password);
    }

    @When("pulsa el boton Login")
    public void pulsaElBotonLogin() {
        WebElement webElement = driver.findElement(JsonLocatorManager.getLocator(homepageLocators, loginButtonKey));
        webElement.click();
    }

    @Then("el sistema muestra la pagina principal")
    public void elSistemaMuestraLaPaginaPrincipal() {
        WebElement webElement = driver.findElement(JsonLocatorManager.getLocator(mainpageLocators, pageTittleKey));
        String tittle = webElement.getText();
        assertEquals(pageTittleText, tittle);
    }

    @Then("el sistema muestra el error de credenciales incorrectas")
    public void elSistemaMuestraElErrorDeCredencialesIncorrectas() {
        WebElement webElement = driver.findElement(JsonLocatorManager.getLocator(homepageLocators, errorMessageKey));
        String message = webElement.getText();
        assertEquals(wrongCredentialsText, message);
    }

    @Then("el sistema muestra el error de usuario en blanco")
    public void elSistemaMuestraElErrorDeUsuarioEnBlanco() {
        WebElement webElement = driver.findElement(JsonLocatorManager.getLocator(homepageLocators, errorMessageKey));
        String message = webElement.getText();
        assertEquals(emptyUsernameText, message);
    }

    @Then("el sistema muestra el error de clave en blanco")
    public void elSistemaMuestraElErrorDeClaveEnBlanco() {
        WebElement webElement = driver.findElement(JsonLocatorManager.getLocator(homepageLocators, errorMessageKey));
        String message = webElement.getText();
        assertEquals(emptyPasswordText, message);
    }
}