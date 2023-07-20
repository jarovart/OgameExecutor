package Controller;

import Presentation.ResourcesPresentation;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;

public class ResourcesController extends PageObject {


    private ResourcesPresentation resourcesPresentation;
    private Long metalCapacity = 0L;
    private Long crystalCapacity = 0L;
    private Long deuteriumCapacity = 0L;
    private final static double LIMIT_CAPACITY = 0.9;

    public ResourcesController(WebDriver driver){
        super(driver);
        resourcesPresentation = new ResourcesPresentation(driver);
        resourcesPresentation.switchToResources();
        resourcesPresentation.clickMetalCapacity();
        System.out.println(resourcesPresentation.getMetalResources());
        System.out.println(resourcesPresentation.getCrystalResources());

        main();
    }

    public void main(){

        //TIME
        if(metalCapacity == 0){
            getResourcesCapacity();
        }
        updateEnergy();
        updateMines();
    }

    private void updateEnergy(){
        if(resourcesPresentation.getEnergyResources() < 0){
            resourcesPresentation.clickSolarpowerplant();
            if(checkMetalCrystalRequirements()){
                resourcesPresentation.clickAusbauenButton();
                //Zeit
            }
        }
    }

    private void updateMines(){
        resourcesPresentation.clickMetalmine();
        String mine = "Metall";
        Double value = calculateMineAmortisation();

        resourcesPresentation.clickCrystalmine();
        Double current = calculateMineAmortisation();
        if(value > current){
            mine = "Kristall";
            value = current;
        }

        resourcesPresentation.clickDeutmine();
        current = calculateMineAmortisation();
        if(value > current){
            mine = "Deuterium";
            value = current;
        }

        if(mine.equals("Metall")){
            resourcesPresentation.clickMetalmine();
        }else if(mine.equals("Kristall")){
            resourcesPresentation.clickCrystalmine();
        }else{
            resourcesPresentation.clickDeutmine();
        }
        if(checkMetalCrystalRequirements()){
            resourcesPresentation.clickAusbauenButton();
            //Zeit
        }
    }

    private Double calculateMineAmortisation(){
        return resourcesPresentation.getMetalRequirement()+
                (resourcesPresentation.getCrystalRequirement()*1.5);
    }

    private void getResourcesCapacity(){
        resourcesPresentation.switchToResourcesSettings();
        metalCapacity = resourcesPresentation.getMetalCapacity();
        crystalCapacity = resourcesPresentation.getCrystalCapacity();
        deuteriumCapacity = resourcesPresentation.getDeuteriumCapacity();

        updateResourceCapacity();
    }

    private void updateResourceCapacity(){
        resourcesPresentation.switchToResources();
        if(metalCapacity < (resourcesPresentation.getMetalResources() * LIMIT_CAPACITY)){
            resourcesPresentation.clickMetalCapacity();
            if(checkMetalRequirements()){
                resourcesPresentation.clickAusbauenButton();
                //Zeit
                return;
            }
        }
        if(crystalCapacity > (resourcesPresentation.getCrystalResources() * LIMIT_CAPACITY)){
            resourcesPresentation.clickCrystalCapacity();
        }
        if(deuteriumCapacity > (resourcesPresentation.getDeuteriumResources() * LIMIT_CAPACITY)) {
            resourcesPresentation.clickDeutCapacity();
        }
        if(checkMetalCrystalRequirements()) {
            resourcesPresentation.clickAusbauenButton();
            //Zeit
        }
    }

    private boolean checkMetalRequirements(){
        return resourcesPresentation.getOnlyMetalRequirement() < resourcesPresentation.getMetalResources();
    }

    private boolean checkMetalCrystalRequirements(){
        return resourcesPresentation.getMetalRequirement() < resourcesPresentation.getMetalResources() &&
                resourcesPresentation.getCrystalRequirement() < resourcesPresentation.getCrystalResources();
    }

    private boolean checkMetalCrystalDeuteriumRequirements(){
        return resourcesPresentation.getMetalRequirement() < resourcesPresentation.getMetalResources() &&
                resourcesPresentation.getCrystalRequirement() < resourcesPresentation.getCrystalResources() &&
                resourcesPresentation.getDeutRequirement() < resourcesPresentation.getDeuteriumResources();
    }//*[@id="technologydetails"]/div[2]/div/ul/li/time
    //*[@id="technologydetails"]/div[2]/div/div[1]/ul/li[2]
    //*[@id="technologydetails"]/div[2]/div/div[1]/ul/li[2]/text()
    //html/body/div[5]/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[1]/ul/li[2]/text()
}
