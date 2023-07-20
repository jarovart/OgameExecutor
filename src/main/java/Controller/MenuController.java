package Controller;

import Presentation.MenuPresentation;
import org.openqa.selenium.WebDriver;

public class MenuController {

    private MenuPresentation menuPresentation;

    public MenuController(WebDriver driver) {
        menuPresentation = new MenuPresentation(driver);
    }

    public void clickOnOverviewMenu(){
        menuPresentation.clickOnOverviewMenu();
    }
}
