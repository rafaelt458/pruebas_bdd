package adapter;

import browserfactory.DriverType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.TestConfig;

public class TestContext {
    private static final Logger log = LogManager.getLogger(TestContext.class);

    private final ElementInteraction elementInteraction;

    public ElementInteraction getElementInteraction() {
        return elementInteraction;
    }

    public TestContext() {
        log.info("Creando el contexto de las pruebas");

        DriverType driverType = TestConfig.getDriverType();
        String pageToTest = TestConfig.getPageToTest();
        AdapterType adapterType = TestConfig.getAdapterType();

        switch (adapterType) {
            case SELENIUM -> {
                this.elementInteraction = new SeleniumElementInteraction(driverType, pageToTest);
                log.info("Se ha inicializado la librería Selenium");
            }
            default -> {
                log.error("No está definida la librería de pruebas");
                throw new RuntimeException("No está definida la librería de pruebas");
            }
        }
    }

    public void closeBrowser() {
        this.elementInteraction.closeBrowser();
    }

    public void saveScrensshotFile(String filepath) {
        this.elementInteraction.takeScreenshotFile(filepath);
    }
}