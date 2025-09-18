package stepdefinition;

import adapter.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Ordenar extends TestBase {
    private final String pageTittleText = "Swag Labs";

    public Ordenar(TestContext testContext) throws IOException {
        super(testContext);
    }

    @Given("el usuario {string} esta en la pagina principal usando el password {string}")
    public void el_usuario_esta_en_la_pagina_principal_usando_el_password(String usuario, String password) throws Exception {
        this.loginPage.doLogin(usuario, password);
        String tittle = this.mainPage.getPageTitle();
        assertEquals(this.pageTittleText, tittle);
    }

    @When("selecciona el orden {string}")
    public void selecciona_el_orden(String orden) throws Exception {
        this.mainPage.orderProductoByVisibleText(orden);
    }

    @Then("el primer elemento de la pagina es el producto {string}")
    public void el_primer_elemento_de_la_pagina_es_el_producto(String producto) throws Exception {
        String firstElementName = this.mainPage.getFirstElementName();
        assertEquals(producto, firstElementName);
    }
}