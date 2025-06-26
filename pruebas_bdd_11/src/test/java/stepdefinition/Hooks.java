package stepdefinition;

import browserfactory.DriverManager;
import browserfactory.DriverManagerFactory;
import browserfactory.DriverType;
import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import util.TestConfig;

import java.time.Duration;

public class Hooks {
    private static final Logger log = LogManager.getLogger(Hooks.class);

    private static WebDriver driver = null;
    private static int paso;

    public static WebDriver getDriver() {
        return driver;
    }

    @Before
    public void setUp(Scenario scenario) {
        log.info("Inicialización del WebDriver para el escenario: {}", scenario.getName());

        String navegador;
        try {
            navegador = TestConfig.readProperty("navegador");
            if (navegador == null) {
                log.error("Hay un problema con la configuración. Usando la configuración por defecto.");
                navegador = "Chrome";
            }
        } catch (Exception e) {
            log.error("Ha ocurrido un error recuperando la configuración. Usando la configuración por defecto.");
            navegador = "Chrome";
        }

        DriverType driverType = DriverType.valueOf(navegador.toUpperCase());
        DriverManager driverManager = DriverManagerFactory.getManager(driverType);
        driver = driverManager.getDriver();

        driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        paso = 0;
    }

    @After
    public void tearDown(Scenario scenario) {
        log.info("Se libera el WebDriver para el escenario: {}", scenario.getName());
        if (driver != null) {
            driver.quit();
        }
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