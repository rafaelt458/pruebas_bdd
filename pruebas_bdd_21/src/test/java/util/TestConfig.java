package util;

import adapter.AdapterType;
import browserfactory.DriverType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestConfig {
    private static final Logger log = LogManager.getLogger(TestConfig.class);

    private TestConfig() {
    }

    public static String readProperty(String key) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("./config/config.properties"));
        return properties.getProperty(key);
    }

    public static DriverType getDriverType() {
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

        log.info("Se recuperó el tipo de navegador en la configuración: {}", navegador);

        return DriverType.valueOf(navegador.toUpperCase());
    }

    public static String getPageToTest() {
        String url;

        try {
            url = TestConfig.readProperty("urlbase");
            if (url == null) {
                url = "https://www.saucedemo.com/";
            }
        } catch (Exception e) {
            url = "https://www.saucedemo.com/";
        }

        log.info("Se recuperó la url a probar en la configuración: {}", url);

        return url;
    }

    public static AdapterType getAdapterType() {
        String adaptador;

        try {
            adaptador = TestConfig.readProperty("adapter");
            if (adaptador == null) {
                adaptador = "selenium";
            }
        } catch (Exception e) {
            adaptador = "selenium";
        }

        log.info("Se recuperó la librería a usar en la configuración: {}", adaptador);

        return AdapterType.valueOf(adaptador.toUpperCase());
    }
}