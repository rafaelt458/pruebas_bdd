package stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.TestConfig;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class Login {
    public static WebDriver driver;

    private By usernameFieldLocator = By.id("user-name");
    // private By passwordFieldLocator = with(By.tagName("input")).below(usernameFieldLocator);
    private By passwordFieldLocator = By.id("password");
    private By loginButtonLocator = By.id("login-button");
    private By pageTittleLocator = By.cssSelector(".app_logo");
    private String pageTittleText = "Swag Labs";
    private By errorMessageLocator = By.tagName("h3");
    private String wrongCredentialsText = "Epic sadface: Username and password do not match any user in this service";
    private String emptyUsernameText = "Epic sadface: Username is required";
    private String emptyPasswordText = "Epic sadface: Password is required";

    public Login() {
        driver = Hooks.getDriver();
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
        WebElement webElement = driver.findElement(usernameFieldLocator);
        webElement.sendKeys(username);
    }

    @When("escribe la clave {string}")
    public void escribeLaClave(String password) {
        WebElement webElement = driver.findElement(passwordFieldLocator);
        webElement.sendKeys(password);
    }

    @When("pulsa el boton Login")
    public void pulsaElBotonLogin() {
        WebElement webElement = driver.findElement(loginButtonLocator);
        webElement.click();
    }

    @Then("el sistema muestra la pagina principal")
    public void elSistemaMuestraLaPaginaPrincipal() {
        WebElement webElement = driver.findElement(pageTittleLocator);
        String tittle = webElement.getText();
        assertEquals(pageTittleText, tittle);
    }

    @Then("el sistema muestra el error de credenciales incorrectas")
    public void elSistemaMuestraElErrorDeCredencialesIncorrectas() {
        WebElement webElement = driver.findElement(errorMessageLocator);
        String message = webElement.getText();
        assertEquals(wrongCredentialsText, message);
    }

    @Then("el sistema muestra el error de usuario en blanco")
    public void elSistemaMuestraElErrorDeUsuarioEnBlanco() {
        WebElement webElement = driver.findElement(errorMessageLocator);
        String message = webElement.getText();
        assertEquals(emptyUsernameText, message);
    }

    @Then("el sistema muestra el error de clave en blanco")
    public void elSistemaMuestraElErrorDeClaveEnBlanco() {
        WebElement webElement = driver.findElement(errorMessageLocator);
        String message = webElement.getText();
        assertEquals(emptyPasswordText, message);
    }
}