package util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestConfig {
    private TestConfig() {
    }

    public static String readProperty(String key) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("./config/config.properties"));
        return properties.getProperty(key);
    }
}