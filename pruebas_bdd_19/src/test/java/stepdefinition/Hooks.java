package stepdefinition;

import adapter.TestContext;
import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class Hooks {
    private static final Logger log = LogManager.getLogger(Hooks.class);

    private static int paso;
    private final TestContext testContext;

    public Hooks(TestContext testContext) {
        log.info("Iniciando el Hooks");
        this.testContext = testContext;
    }

    @Before
    public void setUp(Scenario scenario) {
        log.info("Inicialización para el escenario: {}", scenario.getName());
        paso = 0;
    }

    @After
    public void tearDown(Scenario scenario) {
        String filepath = "./screenshots/" + UUID.randomUUID() + ".png";
        this.testContext.saveScrensshotFile(filepath);

        try {
            scenario.attach(Files.readAllBytes(Path.of(filepath)), "image/png", "Pantallazo");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        log.info("Se libera el WebDriver para el escenario: {}", scenario.getName());
        this.testContext.closeBrowser();
    }

    @BeforeStep
    public void beforeStep(Scenario scenario) {
        paso++;
        log.info("Se va a ejecutar el paso {} del escenario: {}", paso, scenario.getName());
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        log.info("Se ha ejecutado el paso {} del escenario: {}", paso, scenario.getName());
    }
}