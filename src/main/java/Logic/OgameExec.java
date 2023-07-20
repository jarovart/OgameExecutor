package Logic;

import Controller.FleetController;
import Controller.LoginController;
import Controller.MenuController;
import Controller.ResourcesController;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class OgameExec {


    @Managed
    WebDriver driver;

    private LoginController loginController;
    private ResourcesController resourcesController;
    private FleetController fleetController;
    private MenuController menuController;


    public OgameExec() throws IOException {
        this.driver = initDriver();
        loginController = new LoginController(driver);
        Map<String, String> userData = loginController.loginWithCredentials();
        menuController = new MenuController(driver);
        fleetController = new FleetController(driver, userData.get("ExpoPlanetNumber"), userData.get("OnlyCivilShips"), userData.get("maxKTransporter"), userData.get("maxGTransporter"));
        //resourcesController = new ResourcesController(driver);

        int exceptionCount = 0;
        while(exceptionCount < 6) {
            long waiting = 60000;
            boolean isOk = true;
            try {
                waiting = fleetController.checkAndExecuteFleet();
                exceptionCount = 0;
            } catch (Throwable t) {
                isOk = false;
                exceptionCount++;
                t.printStackTrace();
                if (!loginController.reJoinGame()) {
                    throw t;
                }
            }
            if (isOk){
                try {
                    long seconds = (waiting/1000)%60;
                    long minutes = waiting/60000;
                    System.out.println("waiting: "+minutes+" minutes and "+ seconds+ " seconds");
                    Thread.sleep(waiting);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        //menuController = new MenuController(driver);
    }



    public void startSimulation(){
        while(true){
            try{
                fleetController.fleetHasToBeExecuted();
                wait(30000);
            }catch(Exception e){
                e.printStackTrace();
                driver.navigate().refresh();
                wait(10000);
                menuController.clickOnOverviewMenu();
            }
        }
    }

    public void startApplication() throws IOException {
        driver.get("https://lobby.ogame.gameforge.com/de_DE/hub");
        loginController.loginWithCredentials();
    }
    public static boolean start = false;
    public void clickOnGeneral(int number) throws InterruptedException {
        start = true;
        boolean gt = true;
        Thread.sleep(2000);
        long startTime = System.currentTimeMillis();
        while(start){
            System.out.println(System.currentTimeMillis()-startTime);
            if(System.currentTimeMillis() - startTime > 60000){
                loginController.clickOnGeneral("0");
                startTime = System.currentTimeMillis();
            }else{
                loginController.clickOnGeneral("8");
            }
            Thread.sleep(number);
        }

    }


    private WebDriver initDriver() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--window-size=1920,1080", "disable-gpu", "--no-sandbox");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://ogame.de/");
        return driver;
    }

    private void switchToSecondTabAndCloseFirst(WebDriver driver) {
        ArrayList<String> tabs2 = new ArrayList(driver.getWindowHandles());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.switchTo().window(tabs2.get(0)).close();
        driver.switchTo().window(tabs2.get(tabs2.size()-1));
    }

    public void wait(int milliSeconds){
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void closeAllWindowsExceptOne() {
        String originalHandle = driver.getWindowHandle();
        for(String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(originalHandle);
    }

    public void restartApplication() {
        loginController.clickOnJoinGame();
        switchToSecondTabAndCloseFirst(driver);
    }
}

