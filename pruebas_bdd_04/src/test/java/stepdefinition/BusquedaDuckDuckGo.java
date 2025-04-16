package stepdefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BusquedaDuckDuckGo {
    private static final Logger log = LogManager.getLogger(BusquedaDuckDuckGo.class);

    private static WebDriver driver;

    @Before
    public void initDriver() {
        log.info("Inicialización del WebDriver");
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void quitDriver() {
        log.info("Se libera el WebDriver");
        driver.quit();
    }

    @Given("el usuario esta en la pagina principal de DuckDuckGo")
    public void elUsuarioEstaEnLaPaginaPrincipalDeDuckDuckGo() {
        log.info("El usuario se posiciona en la página principal de DuckDuckGo");
        driver.get("https://duckduckgo.com/");
    }

    @When("escribe la espresion de busqueda {string}")
    public void escribeLaEspresionDeBusqueda(String expresion) throws InterruptedException {
        log.info("El usuario introduce la expresión de búsqueda");
        Thread.sleep(1000);
        By textoBusquedaLocator = By.id("searchbox_input");
        WebElement webElement = driver.findElement(textoBusquedaLocator);
        webElement.sendKeys(expresion);
    }

    @When("pulsa el boton Buscar")
    public void pulsaElBotonBuscar() throws InterruptedException {
        log.info("El usuario pulsa el botón Buscar");
        Thread.sleep(1000);
        By botonBusquedaLocator = By.cssSelector(".iconButton_size-20__Ql3lL");
        WebElement webElement = driver.findElement(botonBusquedaLocator);
        webElement.click();
    }

    @Then("el sistema muestra una lista con los resultados encontrados")
    public void elSistemaMuestraUnaListaConLosResultadosEncontrados() throws InterruptedException {
        log.info("EL sistema muestra una lista de los resultados encontrados");
        Thread.sleep(1000);
        By resultadoLocator = By.xpath("/html/body/div[2]/div[6]/div[4]/div/div/div/div[2]/section[1]/ol/li[1]/article/div[3]/h2/a/span");
        WebElement webElement = driver.findElement(resultadoLocator);
        String texto = webElement.getText().toLowerCase();
        Assertions.assertTrue(texto.contains("el laboratorio de rafa"));
    }
}
