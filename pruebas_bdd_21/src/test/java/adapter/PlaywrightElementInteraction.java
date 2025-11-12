package adapter;

import browserfactory.DriverType;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.JsonLocator;

import java.nio.file.Paths;
import java.util.List;

public class PlaywrightElementInteraction implements ElementInteraction {
    private static final Logger log = LogManager.getLogger(PlaywrightElementInteraction.class);

    private final Playwright playwright;
    private final Browser browser;
    private final Page page;

    public PlaywrightElementInteraction(DriverType driverType, String pagina) throws RuntimeException {
        String rutaNavegador;

        this.playwright = Playwright.create();
        switch (driverType) {
            case CHROME, EDGE -> browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false)
                    .setArgs(List.of("--start-maximized")));
            case FIREFOX -> browser = playwright.firefox().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false)
                    .setArgs(List.of("--start-maximized")));
            default -> {
                log.error("El browser seleccionado no existe: {}", driverType.name());
                throw new RuntimeException(String.format("El browser seleccionado no existe: %s", driverType.name()));
            }
        }

        BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(null));
        this.page = context.newPage();
        this.page.navigate(pagina);
    }

    @Override
    public void closeBrowser() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }

    }

    private Locator findElement(JsonLocator jsonLocator) throws RuntimeException {
        switch (jsonLocator.getGetFieldBy()) {
            case "id" -> {
                return this.page.locator("#" + jsonLocator.getValueToFind());
            }
            case "name" -> {
                return this.page.locator(String.format("[name='%s']", jsonLocator.getValueToFind()));
            }
            case "className" -> {
                return this.page.locator("." + jsonLocator.getValueToFind());
            }
            case "tagName", "cssSelector" -> {
                return this.page.locator(jsonLocator.getValueToFind());
            }
            case "xpath" -> {
                return this.page.locator("xpath=" + jsonLocator.getValueToFind());
            }
            case "linkText", "partialLinkText" -> {
                return this.page.locator("a").getByText(jsonLocator.getValueToFind());
            }
            default -> throw new RuntimeException("Método de localizador desconocido: " + jsonLocator.getGetFieldBy());
        }
    }

    @Override
    public void takeScreenshotFile(String filepath) {
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(filepath))
                .setFullPage(true));
    }

    @Override
    public void sendKeys(JsonLocator jsonLocator, String texto) throws Exception {
        Locator locator = this.findElement(jsonLocator);
        locator.fill(texto);
    }

    @Override
    public void click(JsonLocator jsonLocator) throws Exception {
        Locator locator = this.findElement(jsonLocator);
        locator.click();
    }

    @Override
    public String getText(JsonLocator jsonLocator) throws Exception {
        Locator locator = this.findElement(jsonLocator);
        return locator.innerText();
    }

    @Override
    public void selectByVisibleText(JsonLocator jsonLocator, String text) throws Exception {
        Locator locator = this.findElement(jsonLocator);
        locator.selectOption(new SelectOption().setLabel(text));
    }

    @Override
    public void selectByValue(JsonLocator jsonLocator, String value) throws Exception {
        Locator locator = this.findElement(jsonLocator);
        locator.selectOption(value);
    }

    @Override
    public void selectByIndex(JsonLocator jsonLocator, int index) throws Exception {
        Locator locator = this.findElement(jsonLocator);
        locator.selectOption(new SelectOption().setIndex(index));
    }
}