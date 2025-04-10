package stepdefinition;

import datos.Combinacion;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Map;

public class CalculadoraTest {
    private static final Logger log = LogManager.getLogger(CalculadoraTest.class);
    private static int valor1;
    private static int valor2;
    private static int respuesta;
    private static List<Combinacion> combinaciones;
    private static int resultadoDivision;

    @Given("el usuario tiene abierta la aplicacion de calculadora")
    public void elUsuarioTieneAbiertaLaAplicacionDeCalculadora() {
        log.info("El usuario tiene abierta la aplicación de calculadora");
    }

    @When("introduce el primer numero {string}")
    public void introduceElPrimerNumero(String num1) {
        valor1 = Integer.parseInt(num1);
        log.info("El usuario introduce el número 1: {}", valor1);
    }

    @When("introduce el segundo numero {string}")
    public void introduceElSegundoNumero(String num2) {
        valor2 = Integer.parseInt(num2);
        log.info("El usuario introduce el número 2: {}", valor2);
    }

    @When("pulsa el boton sumar")
    public void pulsaElBotonSumar() {
        log.info("El usuario pulsa el botón Sumar");
        respuesta = valor1 + valor2;
    }

    @When("pulsa el boton restar")
    public void pulsaElBotonRestar() {
        log.info("El usuario pulsa el botón Restar");
        respuesta = valor1 - valor2;
    }

    @When("pulsa el boton multiplicar")
    public void pulsaElBotonMultiplicar() {
        log.info("El usuario pulsa el botón Multiplicar");
        respuesta = valor1 * valor2;
    }

    @Then("se muestra en pantalla el resultado {string}")
    public void seMuestraEnPantallaElResultado(String result) {
        int resultado = Integer.parseInt(result);
        log.info("La calculadora muestra el resultado: {}", result);
        Assertions.assertEquals(resultado, respuesta);
    }

    @DataTableType
    public Combinacion defineCombinacion(Map<String, String> row) {
        Combinacion combinacion =  new Combinacion();
        combinacion.setCombinacion(row.get("combinacion"));
        combinacion.setNumero1(Integer.parseInt(row.get("numero1")));
        combinacion.setNumero2(Integer.parseInt(row.get("numero2")));
        combinacion.setResultado(Integer.parseInt(row.get("resultado")));

        return combinacion;
    }

    @Given("las siguientes combinaciones de numeros estan disponibles")
    public void lasSiguientesCombinacionesDeNumerosEstanDisponibles(DataTable dataTable) {
        log.info("Se obtienen las combinaciones disponibles");
        combinaciones = dataTable.asList(Combinacion.class);
    }
    @When("escoge la combinacion {string}")
    public void escogeLaCombinacion(String escogencia) {
        Combinacion combinacion = combinaciones.stream()
                .filter(comb -> comb.getCombinacion().equals(escogencia))
                .findFirst().get();
        valor1 = combinacion.getNumero1();
        valor2 = combinacion.getNumero2();
        log.info("El usuario escoge la combinación: {} y {}", valor1, valor2);
        resultadoDivision = combinacion.getResultado();
    }
    @When("pulsa el boton dividir")
    public void pulsaElBotonDividir() {
        log.info("El usuario pulsa el botón Dividir");
        respuesta = valor1 / valor2;
    }
    @Then("se muestra en pantalla el resultado correspondiente")
    public void seMuestraEnPantallaElResultadoCorrespondiente() {
        log.info("La calculadora muestra el resultado: {}", resultadoDivision);
        Assertions.assertEquals(resultadoDivision, respuesta);
    }
}
