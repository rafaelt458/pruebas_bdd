package util;

import io.cucumber.core.internal.com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonLocatorManager {
    private JsonLocatorManager() {
    }

    public static Map<String, Locator> getLocators(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(
                new File(filePath),
                new TypeReference<Map<String, Locator>>() {}
        );
    }

    public static By getLocator(Map<String, Locator> locators, String locatorKey) {
        Locator locator = locators.get(locatorKey);
        if (locator == null) {
            throw new RuntimeException("No se encontró el localizador: " + locatorKey);
        }

        switch (locator.getGetFieldBy()) {
            case "id" -> {
                return By.id(locator.getValueToFind());
            }
            case "name" -> {
                return By.name(locator.getValueToFind());
            }
            case "className" -> {
                return By.className(locator.getValueToFind());
            }
            case "tagName" -> {
                return By.tagName(locator.getValueToFind());
            }
            case "linkText" -> {
                return By.linkText(locator.getValueToFind());
            }
            case "partialLinkText" -> {
                return By.partialLinkText(locator.getValueToFind());
            }
            case "cssSelector" -> {
                return By.cssSelector(locator.getValueToFind());
            }
            case "xpath" -> {
                return By.xpath(locator.getValueToFind());
            }
            default -> {
                throw new RuntimeException("Método de localizador desconocido: " + locator.getValueToFind());
            }
        }
    }

}