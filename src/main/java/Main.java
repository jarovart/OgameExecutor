import Logic.OgameExec;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class Main {

    static OgameExec ogameExec;

    private static WebDriver initDriver() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        return driver;
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        ogameExec = new OgameExec();
    }
}
