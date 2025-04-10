package stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;

public class Calculadora2Test {
    private static final Logger log = LogManager.getLogger(Calculadora2Test.class);
    private static int valor1;
    private static int valor2;
    private static int respuesta;

    @Given("el usuario tiene abierta la aplicacion de calculadora2")
    public void elUsuarioTieneAbiertaLaAplicacionDeCalculadora2() {
        log.info("El usuario tiene abierta la aplicación de calculadora 2");
    }

    @When("introduce el primer numero {int}")
    public void introduceElPrimerNumero(Integer num1) {
        valor1 = num1;
        log.info("El usuario introduce el valor 1: {}", valor1);
    }

    @When("introduce el segundo numero {int}")
    public void introduceElSegundoNumero(Integer num2) {
        valor2 = num2;
        log.info("El usuario introduce el valor 2: {}", valor2);
    }

    @When("pulsa el boton {string}")
    public void pulsaElBoton(String operacion) {
        log.info("El usuario pulsa el botón: {}", operacion);
        switch (operacion) {
            case "suma" -> {
                respuesta = valor1 + valor2;
            }
            case "resta" -> {
                respuesta = valor1 - valor2;
            }
            case "multiplicacion" -> {
                respuesta = valor1 * valor2;
            }
        }
    }

    @Then("se muestra en pantalla el resultado {int}")
    public void seMuestraEnPantallaElResultado(Integer resultado) {
        log.info("La calculadora muestra el resultado: {}", resultado);
        Assertions.assertEquals(resultado, respuesta);
    }
}