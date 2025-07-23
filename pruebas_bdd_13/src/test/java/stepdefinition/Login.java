package stepdefinition;

import adapter.ElementInteraction;
import adapter.SeleniumElementInteraction;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.WebDriver;
import util.JsonLocatorManager;
import util.JsonLocator;
import util.TestConfig;

import java.io.IOException;
import java.util.Map;

public class Login {
    private static WebDriver driver;
    private static ElementInteraction elementInteraction;
    private static Map<String, JsonLocator> loginpageLocators;
    private static Map<String, JsonLocator> mainpageLocators;

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
        elementInteraction = new SeleniumElementInteraction(driver);
        loginpageLocators = JsonLocatorManager.getLocators("./config/loginpage.json");
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
    public void escribeElNombreDeUsuario(String username) throws Exception {
        JsonLocator jsonLocator = JsonLocatorManager.getLocator(loginpageLocators, usernameFieldKey);
        elementInteraction.sendKeys(jsonLocator, username);
    }

    @When("escribe la clave {string}")
    public void escribeLaClave(String password) throws Exception {
        JsonLocator jsonLocator = JsonLocatorManager.getLocator(loginpageLocators, passwordFieldKey);
        elementInteraction.sendKeys(jsonLocator, password);
    }

    @When("pulsa el boton Login")
    public void pulsaElBotonLogin() throws Exception {
        JsonLocator jsonLocator = JsonLocatorManager.getLocator(loginpageLocators, loginButtonKey);
        elementInteraction.click(jsonLocator);
    }

    @Then("el sistema muestra la pagina principal")
    public void elSistemaMuestraLaPaginaPrincipal() throws Exception {
        JsonLocator jsonLocator = JsonLocatorManager.getLocator(mainpageLocators, pageTittleKey);
        String tittle = elementInteraction.getText(jsonLocator);
        assertEquals(pageTittleText, tittle);
    }

    @Then("el sistema muestra el error de credenciales incorrectas")
    public void elSistemaMuestraElErrorDeCredencialesIncorrectas() throws Exception {
        JsonLocator jsonLocator = JsonLocatorManager.getLocator(loginpageLocators, errorMessageKey);
        String message = elementInteraction.getText(jsonLocator);
        assertEquals(wrongCredentialsText, message);
    }

    @Then("el sistema muestra el error de usuario en blanco")
    public void elSistemaMuestraElErrorDeUsuarioEnBlanco() throws Exception {
        JsonLocator jsonLocator = JsonLocatorManager.getLocator(loginpageLocators, errorMessageKey);
        String message = elementInteraction.getText(jsonLocator);
        assertEquals(emptyUsernameText, message);
    }

    @Then("el sistema muestra el error de clave en blanco")
    public void elSistemaMuestraElErrorDeClaveEnBlanco() throws Exception {
        JsonLocator jsonLocator = JsonLocatorManager.getLocator(loginpageLocators, errorMessageKey);
        String message = elementInteraction.getText(jsonLocator);
        assertEquals(emptyPasswordText, message);
    }
}