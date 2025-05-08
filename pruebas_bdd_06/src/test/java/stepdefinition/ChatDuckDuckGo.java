package stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ChatDuckDuckGo {
    private static WebDriver driver;

    public ChatDuckDuckGo() {
        driver = Hooks.getDriver();
    }

    @Given("el usuario esta en la pagina principal")
    public void elUsuarioEstaEnLaPaginaPrincipal() {
        driver.get("https://duckduckgo.com/");
    }

    @When("pulsa el boton Chat")
    public void pulsaElBotonChat() throws InterruptedException {
        Thread.sleep(1000);
        By botonChatLocator = By.linkText("Chat");
        WebElement webElement = driver.findElement(botonChatLocator);
        webElement.click();
    }

    @Then("se muestra la pagina principal del chat con la AI")
    public void seMuestraLaPaginaPrincipalDelChatConLaAI() throws InterruptedException {
        Thread.sleep(1000);
        By tituloLocator = By.tagName("h3");
        WebElement webElement = driver.findElement(tituloLocator);
        String titulo = webElement.getText();
        Assertions.assertEquals("Saluda a Duck.ai", titulo);
    }
}