package Logic;

import net.thucydides.core.pages.PageObject;

public class OgameExecutor extends PageObject {
/**
 @Managed WebDriver driver;

 public static void main(String[] args){
 OgameExecutor oe = new OgameExecutor();
 oe.executeScript();
 System.out.println("Hello World");

 }

 public void executeScript(){
 loginToOgame();
 FleetController fleetController = new FleetController(driver);
 fleetController.executeFleet();
 }


 private void loginToOgame(){
 System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
 driver = new ChromeDriver();
 driver.manage().window().maximize();
 driver.get("https://lobby.ogame.gameforge.com/de_DE/hub");
 //driver.findElement(By.xpath("//ul[@class='tabsList']/li[1]")).click();
 driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div[2]/div[2]/div/ul/li[1]")).click();
 driver.findElement(By.xpath("//div[1]/div[1]/input[1]")).sendKeys("artem.jarovoj17@googlemail.com");
 driver.findElement(By.xpath("//div[2]/div[1]/input[1]")).sendKeys("andromeda1");
 //driver.findElement(By.xpath("//button[@class='button button-primary button-lg']")).click();
 driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div[2]/div[2]/div/button")).click();
 //driver.findElement(By.xpath("//button[@class='button button-primary button-md']")).click();
 //driver.findElement(By.xpath("//div[@class='rt-tr-group open']/div[@class='rt-tr -odd' and 1]/div[@class='rt-td action-cell' and 11]/button[@class='btn btn-primary' and 1 and @type='button']/span[1]")).click();
 }*/
}
