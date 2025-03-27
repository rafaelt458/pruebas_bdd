package stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BusquedaInternet {
    private static final Logger log = LogManager.getLogger(BusquedaInternet.class);

    @Given("el usuario esta en la pagina de busqueda")
    public void elUsuarioEstaEnLaPaginaDeBusqueda() {
        log.info("El usuario está en la página de búsqueda");
    }
    @When("el usuario introduce el texto {string}")
    public void elUsuarioIntroduceElTexto(String textoABuscar) {
        log.info("El usuario introduce el texto: {}", textoABuscar);
    }
    @When("pulsa el boton Buscar")
    public void pulsaElBotonBuscar() {
        log.info("El usuario pulsa el botón Buscar");
    }
    @Then("el sistema muestra los resultados")
    public void elSistemaMuestraLosResultados() {
        log.info("El sistema muestra los resultados encontrados");
    }
}
