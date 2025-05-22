package stepdefinition;

import io.cucumber.java.an.E;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.TestConfig;

public class BusquedaDuckDuckGo {
    private static WebDriver driver;

    public BusquedaDuckDuckGo() {
        driver = Hooks.getDriver();
    }

    @Given("el usuario esta en la pagina principal de DuckDuckGo")
    public void elUsuarioEstaEnLaPaginaPrincipalDeDuckDuckGo() {
        String url;
        try {
            url = TestConfig.readProperty("urlbase");
            if (url == null) {
                url = "https://duckduckgo.com/";
            }
        } catch (Exception e) {
            url = "https://duckduckgo.com/";
        }
        driver.get(url);
    }

    @When("escribe la espresion de busqueda {string}")
    public void escribeLaEspresionDeBusqueda(String expresion) throws InterruptedException {
        String nuevaBusqueda = null;
        try {
            nuevaBusqueda = TestConfig.readProperty("expresion");
        } catch (Exception e) {
        }

        if (nuevaBusqueda != null) {
            expresion = nuevaBusqueda;
        }

        Thread.sleep(1000);
        By textoBusquedaLocator = By.id("searchbox_input");
        WebElement webElement = driver.findElement(textoBusquedaLocator);
        webElement.sendKeys(expresion);
    }

    @When("pulsa el boton Buscar")
    public void pulsaElBotonBuscar() throws InterruptedException {
        Thread.sleep(1000);
        By botonBusquedaLocator = By.cssSelector(".iconButton_size-20__Ql3lL");
        WebElement webElement = driver.findElement(botonBusquedaLocator);
        webElement.click();
    }

    @Then("el sistema muestra una lista con los resultados encontrados")
    public void elSistemaMuestraUnaListaConLosResultadosEncontrados() throws InterruptedException {
        Thread.sleep(1000);
        By resultadoLocator = By.xpath("/html/body/div[2]/div[6]/div[4]/div/div/div/div[2]/section[1]/ol/li[1]/article/div[3]/h2/a/span");
        WebElement webElement = driver.findElement(resultadoLocator);
        String texto = webElement.getText().toLowerCase();
        Assertions.assertTrue(texto.contains("el laboratorio de rafa"));
    }
}