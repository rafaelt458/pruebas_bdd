package stepdefinition;

import adapter.ElementInteraction;
import adapter.SeleniumElementInteraction;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import util.JsonLocator;
import util.JsonLocatorManager;
import util.TestConfig;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerDetalle {
    private static WebDriver driver;
    private static ElementInteraction elementInteraction;

    private static Map<String, JsonLocator> loginpageLocators;
    private static Map<String, JsonLocator> mainpageLocators;
    private static Map<String, JsonLocator> detailPageLocators;

    private final String usernameFieldKey = "usernameField";
    private final String passwordFieldKey = "passwordField";
    private final String loginButtonKey = "loginButton";
    private final String pageTittleKey = "pageTittle";
    private final String pageTittleText = "Swag Labs";
    private final String productNameKey = "productName";

    public VerDetalle() throws IOException {
        driver = Hooks.getDriver();
        elementInteraction = new SeleniumElementInteraction(driver);
        loginpageLocators = JsonLocatorManager.getLocators("./config/loginpage.json");
        mainpageLocators = JsonLocatorManager.getLocators("./config/mainpage.json");
        detailPageLocators = JsonLocatorManager.getLocators("./config/detailpage.json");
    }

    @Given("el usuario {string} con password {string} esta en la pagina principal\"")
    public void el_usuario_con_password_esta_en_la_pagina_principal(String username, String password) throws Exception {
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

        JsonLocator jsonLocator1 = JsonLocatorManager.getLocator(loginpageLocators, usernameFieldKey);
        elementInteraction.sendKeys(jsonLocator1, username);
        JsonLocator jsonLocator2 = JsonLocatorManager.getLocator(loginpageLocators, passwordFieldKey);
        elementInteraction.sendKeys(jsonLocator2, password);
        JsonLocator jsonLocator3 = JsonLocatorManager.getLocator(loginpageLocators, loginButtonKey);
        elementInteraction.click(jsonLocator3);
        JsonLocator jsonLocator4 = JsonLocatorManager.getLocator(mainpageLocators, pageTittleKey);
        String tittle = elementInteraction.getText(jsonLocator4);
        assertEquals(pageTittleText, tittle);
    }
    @When("hace click sobre producto {string}")
    public void hace_click_sobre_producto(String localizador) throws Exception {
        JsonLocator jsonLocator = JsonLocatorManager.getLocator(mainpageLocators, localizador);
        elementInteraction.click(jsonLocator);
    }
    @Then("el sistema muestra el detalle del producto {string}")
    public void el_sistema_muestra_el_detalle_del_producto(String nombreProducto) throws Exception {
        JsonLocator jsonLocator = JsonLocatorManager.getLocator(detailPageLocators, productNameKey);
        String productName = elementInteraction.getText(jsonLocator);
        assertEquals(nombreProducto, productName);
    }
}