package Presentation;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResourcesPresentation extends PageObject {

    //Text
    @FindBy(xpath="//*[@id=\"resources_metal\"]") private WebElement showMetal;
    @FindBy(xpath="//*[@id=\"resources_crystal\"]") private WebElement showCrystal;
    @FindBy(xpath="//*[@id=\"resources_deuterium\"]") private WebElement showDeuterium;
    @FindBy(xpath="//*[@id=\"resources_energy\"]") private WebElement showEnergy;
    @FindBy(xpath="/html/body/div[2]/div[2]/div[2]/div[2]/div[2]/div[2]/div[2]/form/table/tbody/tr[18]/td[2]/span") private WebElement showMetalCapacity;
    @FindBy(xpath="//*[@id=\"inhalt\"]/div[2]/div[2]/form/table/tbody/tr[18]/td[2]/span") private WebElement showCrystalCapacity;
    @FindBy(xpath="/html/body/div[2]/div[2]/div[2]/div[2]/div[2]/div[2]/div[2]/form/table/tbody/tr[18]/td[4]/span") private WebElement showDeuteriumCapacity;
    @FindBy(xpath="/html/body/div[4]/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[1]/ul/li[1]") private WebElement showOnlyMetalRequirement;
    @FindBy(xpath="//*[@id=\"technologydetails\"]/div[2]/div/div[1]/ul/li[1]/text()") private WebElement showMetalRequirement;
    @FindBy(xpath="//*[@id=\"technologydetails\"]/div[2]/div/div[1]/ul/li[2]/text()") private WebElement showCrystalRequirement;
    @FindBy(xpath="//*[@id=\"technologydetails\"]/div[2]/div/div[1]/ul/li[3]/text()") private WebElement showDeuteriumRequirement;

    //Tab
    @FindBy(xpath="//*[@id=\"menuTable\"]/li[2]/a/span") private WebElement clickVersorgungsTab;
    @FindBy(xpath="//*[@id=\"menuTable\"]/li[2]/span/a") private WebElement clickVersorgungsEinstellungTab;

    //Click in Versorgung
    @FindBy(xpath="/html/body/div[5]/div[3]/div[2]/div/div[2]/ul/li[1]/span") private WebElement clickMetalmine;
    @FindBy(xpath="/html/body/div[5]/div[3]/div[2]/div/div[2]/ul/li[2]/span") private WebElement clickCrystalmine;
    @FindBy(xpath="/html/body/div[5]/div[3]/div[2]/div/div[2]/ul/li[3]/span") private WebElement clickDeutmine;
    @FindBy(xpath="/html/body/div[5]/div[3]/div[2]/div/div[2]/ul/li[4]/span") private WebElement clickSolarpowerplant;
    @FindBy(xpath="/html/body/div[5]/div[3]/div[2]/div/div[2]/ul/li[8]/span") private WebElement clickMetalCapacity;
    @FindBy(xpath="/html/body/div[5]/div[3]/div[2]/div/div[2]/ul/li[9]/span") private WebElement clickCrystalCapacity;
    @FindBy(xpath="/html/body/div[5]/div[3]/div[2]/div/div[2]/ul/li[10]/span") private WebElement clickDeutCapacity;
    @FindBy(xpath="/html/body/div[5]/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[2]/button/span") private WebElement clickAusbauenButton;


    public ResourcesPresentation(WebDriver driver){
        super(driver);
    }

    /**
     * Tab Switching
     */
    public void switchToResources(){

        element(clickVersorgungsTab).waitUntilClickable().click();
    }

    public void switchToResourcesSettings(){
        element(clickVersorgungsEinstellungTab).waitUntilClickable().click();
    }

    /**
     *  Click Button
     */
    public void clickMetalmine(){
        element(clickMetalmine).waitUntilClickable().click();
    }

    public void clickCrystalmine(){
        element(clickCrystalmine).waitUntilClickable().click();
    }

    public void clickDeutmine(){
        element(clickDeutmine).waitUntilClickable().click();
    }

    public void clickSolarpowerplant(){
        element(clickSolarpowerplant).waitUntilClickable().click();
    }

    public void clickMetalCapacity(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        element(clickMetalCapacity).waitUntilPresent().click();
    }

    public void clickCrystalCapacity(){
        element(clickCrystalCapacity).waitUntilClickable().click();
    }

    public void clickDeutCapacity(){
        element(clickDeutCapacity).waitUntilClickable().click();
    }

    public void clickAusbauenButton(){
        element(clickAusbauenButton).waitUntilClickable().click();
    }


    /**
     * Show Text
     */
    public Long getMetalResources(){
        return Long.parseLong(element(showMetal).waitUntilPresent().getText().replaceAll("\\.", ""));
    }

    public Long getCrystalResources(){
        return Long.parseLong(element(showCrystal).waitUntilPresent().getText().replaceAll("\\.", ""));
    }

    public Long getDeuteriumResources(){
        return Long.parseLong(element(showDeuterium).waitUntilPresent().getText().replaceAll("\\.", ""));
    }

    public Long getEnergyResources(){
        return Long.parseLong(element(showEnergy).waitUntilPresent().getText().replaceAll("\\.", ""));
    }

    public Long getMetalCapacity(){
        return Long.parseLong(element(showMetalCapacity).waitUntilPresent().getText().replaceAll("\\.", ""));
    }

    public Long getCrystalCapacity(){
        return Long.parseLong(element(showCrystalCapacity).waitUntilPresent().getText().replaceAll("\\.", ""));
    }

    public Long getDeuteriumCapacity(){
        return Long.parseLong(element(showDeuteriumCapacity).waitUntilPresent().getText().replaceAll("\\.", ""));
    }

    public Long getOnlyMetalRequirement(){
        return Long.parseLong(element(showOnlyMetalRequirement).waitUntilPresent().getText().replaceAll("\\.", ""));
    }

    public Long getMetalRequirement(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Long.parseLong(element(showMetalRequirement).waitUntilPresent().getText().replaceAll("\\.", ""));
    }

    public Long getCrystalRequirement(){
        return Long.parseLong(element(showCrystalRequirement).waitUntilPresent().getText().replaceAll("\\.", ""));
    }

    public Long getDeutRequirement(){
        return Long.parseLong(element(showDeuteriumRequirement).waitUntilPresent().getText().replaceAll("\\.", ""));
    }
}
