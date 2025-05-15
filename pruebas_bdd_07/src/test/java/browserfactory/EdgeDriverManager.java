package browserfactory;

import org.openqa.selenium.edge.EdgeDriver;

public class EdgeDriverManager extends DriverManager {
    @Override
    protected void createDriver() {
        System.setProperty("webdriver.msedge.driver", "./src/test/resources/drivers/msedgedriver.exe");
        driver = new EdgeDriver();
    }
}