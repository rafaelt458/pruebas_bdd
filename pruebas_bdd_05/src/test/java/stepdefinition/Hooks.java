package stepdefinition;

import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
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