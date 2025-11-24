package Presentation;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MenuPresentation extends PageObject {

    @FindBy(xpath = "/html/body/div[6]/div[2]/div[3]/div/ul/li[1]/a")
    private WebElement clickOnMenu;

    public MenuPresentation(WebDriver driver) {
        super(driver);
    }


    public void clickOnOverviewMenu() {
        element(clickOnMenu).waitUntilClickable().click();
    }
}
