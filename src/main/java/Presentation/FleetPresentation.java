package Presentation;

import Data.FleetAttackType;
import Data.FleetType;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.*;

import javax.print.attribute.standard.MediaSizeName;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FleetPresentation extends PageObject {

    @FindBy(xpath = "//*[@id=\"menuTable\"]/li[9]/a/span")
    private WebElement clickOnFleetMenu;
    @FindBy(xpath = "//*[@id=\"menuTable\"]/li[9]/span/a/div")
    private WebElement clickOnCurrentFleetList;
    @FindBy(xpath = "//*[@id=\"inhalt\"]/div[3]/span[3]/span[1]")
    private WebElement showCurrentExpo;
    @FindBy(xpath = "//*[@id=\"inhalt\"]/div[3]/span[3]/span[2]")
    private WebElement showMaxExpo;
    @FindBy(xpath = "//*[@id=\"slots\"]/div[2]/span")
    private WebElement showExpoSlots;
    //Errors
    @FindBy(xpath = "/html/body/div[6]/div[3]/div[2]/div[1]/div/div[3]/div/div[1]/span")
    private WebElement showFleet;
    @FindBy(xpath = "/html/body/div[6]/div[3]/div[2]/div[1]/div/div[3]/div/div[2]/span")
    private WebElement showExpos;
    @FindBy(xpath = "/html/body/div[6]/div[3]/div[2]/div[1]/div/div[7]/h3")
    private WebElement noFleetMessage;

    //Show Fleet attack
    @FindBy(xpath = "//*[@id=\"military\"]/li[1]/span/span/span[1]")
    private WebElement showLeichterJaegerCount;
    @FindBy(xpath = "//*[@id=\"military\"]/li[2]/span/span/span[1]")
    private WebElement showSchwererJaegerCount;
    @FindBy(xpath = "//*[@id=\"military\"]/li[3]/span/span/span[1]")
    private WebElement showKreuzerCount;
    @FindBy(xpath = "//*[@id=\"military\"]/li[4]/span/span/span[1]")
    private WebElement showSchlachtSchiffCount;
    @FindBy(xpath = "//*[@id=\"military\"]/li[5]/span/span/span[1]")
    private WebElement showSchlachtKreuzerCount;
    @FindBy(xpath = "//*[@id=\"military\"]/li[6]/span/span/span[1]")
    private WebElement showBomberCount;
    @FindBy(xpath = "//*[@id=\"military\"]/li[7]/span/span/span[1]")
    private WebElement showZerstoererCount;
    @FindBy(xpath = "//*[@id=\"military\"]/li[8]/span/span/span[1]")
    private WebElement showTodesSternCount;
    @FindBy(xpath = "//*[@id=\"military\"]/li[9]/span/span/span[1]")
    private WebElement showReaperCount;
    @FindBy(xpath = "//*[@id=\"military\"]/li[10]/span/span/span[1]")
    private WebElement showPathFinderCount;
    //Show Fleet friendly
    @FindBy(xpath = "//*[@id=\"civil\"]/li[1]/span/span/span[1]")
    private WebElement showKleinerTransporterCount;
    @FindBy(xpath = "//*[@id=\"civil\"]/li[2]/span/span/span[1]")
    private WebElement showGrosserTransporterCount;
    @FindBy(xpath = "//*[@id=\"civil\"]/li[3]/span/span/span[1]")
    private WebElement showKolonieSchiffCount;
    @FindBy(xpath = "//*[@id=\"civil\"]/li[4]/span/span/span[1]")
    private WebElement showRecyclerCount;
    @FindBy(xpath = "//*[@id=\"civil\"]/li[5]/span/span/span[1]")
    private WebElement showSpionageSondeCount;

    //Set Fleet attack
    @FindBy(xpath = "//*[@id=\"military\"]/li[1]/input")
    private WebElement setLeichterJaegerCount;
    @FindBy(xpath = "//*[@id=\"military\"]/li[2]/input")
    private WebElement setSchwererJaegerCount;
    @FindBy(xpath = "//*[@id=\"military\"]/li[3]/input")
    private WebElement setKreuzerCount;
    @FindBy(xpath = "//*[@id=\"military\"]/li[4]/input")
    private WebElement setSchlachtSchiffCount;
    @FindBy(xpath = "//*[@id=\"military\"]/li[5]/input")
    private WebElement setSchlachtKreuzerCount;
    @FindBy(xpath = "//*[@id=\"military\"]/li[6]/input")
    private WebElement setBomberCount;
    @FindBy(xpath = "//*[@id=\"military\"]/li[7]/input")
    private WebElement setZerstoererCount;
    @FindBy(xpath = "//*[@id=\"military\"]/li[8]/input")
    private WebElement setTodesSternCount;
    @FindBy(xpath = "//*[@id=\"military\"]/li[9]/input")
    private WebElement setReaperCount;
    @FindBy(xpath = "//*[@id=\"military\"]/li[10]/input")
    private WebElement setPathFinderCount;
    //Set Fleet friendly
    @FindBy(xpath = "//*[@id=\"civil\"]/li[1]/input")
    private WebElement setKleinerTransporterCount;
    @FindBy(xpath = "//*[@id=\"civil\"]/li[2]/input")
    private WebElement setGrosserTransporterCount;
    @FindBy(xpath = "//*[@id=\"civil\"]/li[3]/input")
    private WebElement setKolonieSchiffCount;
    @FindBy(xpath = "//*[@id=\"civil\"]/li[4]/input")
    private WebElement setRecyclerCount;
    @FindBy(xpath = "//*[@id=\"civil\"]/li[5]/input")
    private WebElement setSpionageSondeCount;

    //Execute
    @FindBy(xpath = "//*[@id=\"continueToFleet2\"]/span")
    private WebElement pressWeiter;
    @FindBy(xpath = "//*[@id=\"sendFleet\"]/span")
    private WebElement pressWeiterPage2;

    //Page 2
    @FindBy(xpath = "//*[@id=\"galaxy\"]")
    private WebElement setGalaxyInput;
    @FindBy(xpath = "//*[@id=\"system\"]")
    private WebElement setSystemInput;
    @FindBy(xpath = "//*[@id=\"position\"]")
    private WebElement setPositionInput;

    //Mission
    @FindBy(xpath = "//*[@id=\"missionButton15\"]")
    private WebElement setExpedition;
    @FindBy(xpath = "//*[@id=\"missionButton7\"]")
    private WebElement setKolonisieren;
    @FindBy(xpath = "//*[@id=\"missionButton8\"]")
    private WebElement setTruemmerfeld;
    @FindBy(xpath = "//*[@id=\"missionButton3\"]")
    private WebElement setTransport;
    @FindBy(xpath = "//*[@id=\"missionButton4\"]")
    private WebElement setStationieren;
    @FindBy(xpath = "//*[@id=\"missionButton6\"]")
    private WebElement setSpionage;
    @FindBy(xpath = "//*[@id=\"missionButton5\"]")
    private WebElement setHalten;
    @FindBy(xpath = "//*[@id=\"missionButton1\"]")
    private WebElement setAngreifen;
    @FindBy(xpath = "//*[@id=\"missionButton2\"]")
    private WebElement setVerbandsangriff;
    @FindBy(xpath = "//*[@id=\"missionButton9\"]")
    private WebElement setZerstoeren;

    //Resources
    @FindBy(xpath = "//*[@id=\"metal\"]")
    private WebElement setMetal;
    @FindBy(xpath = "//*[@id=\"crystal\"]")
    private WebElement setCrystal;
    @FindBy(xpath = "//*[@id=\"deuterium\"]")
    private WebElement setDeuterium;

    //TimeSchedule
    @FindBy(xpath = "//*[@id=\"arrivalTime\"]")
    private WebElement getArrivalTime;
    @FindBy(xpath = "//*[@id=\"returnTime\"]")
    private WebElement getReturnTime;
    @FindBy(xpath = "//*[@class=\"fleetDetails detailsOpened\"]/*[@class=\"mission neutral textBeefy\"]")
    private WebElement showAllFleetTypes;

    private final String showCurrentExpoDestinationByIndex = "//*[@class=\"fleetDetails detailsOpened\"][{0}]/*[@class=\"absTime\"]";
    private String clickOnXthPlanet = "//*[@id=\"planetList\"]/div[{0}]/a";
    private final String timeOfDayPattern = "HH:mm:ss";
    private final String datePattern = "dd.MM.yyyy HH:mm:ss";


    public FleetPresentation(WebDriver driver) {
        super(driver);
    }

    public String showFleetTypeCount(FleetType fleetType) {
        switch (fleetType) {
            case LEICHTER_JAEGER:
                return element(showLeichterJaegerCount).waitUntilPresent().getText().replaceAll("\\.", "");
            case SCHWERER_JAEGER:
                return element(showSchwererJaegerCount).waitUntilPresent().getText().replaceAll("\\.", "");
            case KREUZER:
                return element(showKreuzerCount).waitUntilPresent().getText().replaceAll("\\.", "");
            case SCHLACHTSCHIFF:
                return element(showSchlachtSchiffCount).waitUntilPresent().getText().replaceAll("\\.", "");
            case SCHLACHTKREUZER:
                return element(showSchlachtKreuzerCount).waitUntilPresent().getText().replaceAll("\\.", "");
            case BOMBER:
                return element(showBomberCount).waitUntilPresent().getText().replaceAll("\\.", "");
            case ZERSTOERER:
                return element(showZerstoererCount).waitUntilPresent().getText().replaceAll("\\.", "");
            case TODESSTERN:
                return element(showTodesSternCount).waitUntilPresent().getText().replaceAll("\\.", "");
            case REAPER:
                return element(showReaperCount).waitUntilPresent().getText().replaceAll("\\.", "");
            case PATHFINDER:
                return element(showPathFinderCount).waitUntilPresent().getText().replaceAll("\\.", "");
            case KLEINER_TRANSPORTER:
                return element(showKleinerTransporterCount).waitUntilPresent().getText().replaceAll("\\.", "");
            case GROSSER_TRANSPORTER:
                return element(showGrosserTransporterCount).waitUntilPresent().getText().replaceAll("\\.", "");
            case KOLONIESCHIFF:
                return element(showKolonieSchiffCount).waitUntilPresent().getText().replaceAll("\\.", "");
            case RECYCLER:
                return element(showRecyclerCount).waitUntilPresent().getText().replaceAll("\\.", "");
            case SPIONAGESONDE:
                return element(showSpionageSondeCount).waitUntilPresent().getText().replaceAll("\\.", "");
            default:
                throw new IllegalArgumentException("No case exists");
        }
    }

    public void setFleetTypeCount(FleetType fleetType, Integer count) {
        switch (fleetType) {
            case LEICHTER_JAEGER:
                element(setLeichterJaegerCount).waitUntilClickable().sendKeys(count.toString());
                break;
            case SCHWERER_JAEGER:
                element(setSchwererJaegerCount).waitUntilClickable().sendKeys(count.toString());
                break;
            case KREUZER:
                element(setKreuzerCount).waitUntilClickable().sendKeys(count.toString());
                break;
            case SCHLACHTSCHIFF:
                element(setSchlachtSchiffCount).waitUntilClickable().sendKeys(count.toString());
                break;
            case SCHLACHTKREUZER:
                element(setSchlachtKreuzerCount).waitUntilClickable().sendKeys(count.toString());
                break;
            case BOMBER:
                element(setBomberCount).waitUntilClickable().sendKeys(count.toString());
                break;
            case ZERSTOERER:
                element(setZerstoererCount).waitUntilClickable().sendKeys(count.toString());
                break;
            case TODESSTERN:
                element(setTodesSternCount).waitUntilClickable().sendKeys(count.toString());
                break;
            case REAPER:
                element(setReaperCount).waitUntilClickable().sendKeys(count.toString());
                break;
            case PATHFINDER:
                element(setPathFinderCount).waitUntilClickable().sendKeys(count.toString());
                break;
            case KLEINER_TRANSPORTER:
                element(setKleinerTransporterCount).waitUntilClickable().sendKeys(count.toString());
                break;
            case GROSSER_TRANSPORTER:
                element(setGrosserTransporterCount).waitUntilClickable().sendKeys(count.toString());
                break;
            case KOLONIESCHIFF:
                element(setKolonieSchiffCount).waitUntilClickable().sendKeys(count.toString());
                break;
            case RECYCLER:
                element(setRecyclerCount).waitUntilClickable().sendKeys(count.toString());
                break;
            case SPIONAGESONDE:
                element(setSpionageSondeCount).waitUntilClickable().sendKeys(count.toString());
                break;
            default:
                throw new IllegalArgumentException("No case exists");
        }
    }

    public void setFleetAttackType(FleetAttackType fleetAttackType) {
        switch (fleetAttackType) {
            case EXPEDITION:
                element(setExpedition).waitUntilClickable().click();
                break;
            case KOLONISIEREN:
                element(setKolonisieren).waitUntilClickable().click();
                break;
            case TRUEMMERFELD:
                element(setTruemmerfeld).waitUntilClickable().click();
                break;
            case TRANSPORT:
                element(setTransport).waitUntilClickable().click();
                break;
            case STATIONIEREN:
                element(setStationieren).waitUntilClickable().click();
                break;
            case SPIONAGE:
                element(setSpionage).waitUntilClickable().click();
                break;
            case HALTEN:
                element(setHalten).waitUntilClickable().click();
                break;
            case ANGREIFEN:
                element(setAngreifen).waitUntilClickable().click();
                break;
            case VERBANDSANGRIFF:
                element(setVerbandsangriff).waitUntilClickable().click();
                break;
            case ZERSTOEREN:
                element(setZerstoeren).waitUntilClickable().click();
                break;
            default:
                throw new IllegalArgumentException("No case exists - setFleetAttackType");
        }
    }

    public String showFleet() {
        return element(showFleet).waitUntilPresent().getText();
    }

    public boolean isFleetAvailable() {
        return element(showSchlachtSchiffCount).isPresent();
    }

    public String showExpos() {
        return element(showExpos).waitUntilPresent().getText();
    }

    public String showNoFleetMessage() {
        return element(noFleetMessage).waitUntilPresent().getText();
    }

    public void executeWeiter() {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("window.scrollBy(0,250)");
        element(pressWeiter).waitUntilClickable().click();
    }

    public void executeWeiterPage2() {
        element(pressWeiterPage2).waitUntilClickable().click();
    }

    public void setDeuterium(long deuterium) {
        element(setDeuterium).waitUntilClickable().sendKeys(Long.toString(deuterium));
    }

    public void setCrystal(long crystal) {
        element(setCrystal).waitUntilClickable().sendKeys(Long.toString(crystal));
    }

    public void setMetal(long metal) {
        element(setMetal).waitUntilClickable().sendKeys(Long.toString(metal));
    }

    public void setGalaxyInput(long galaxyInput) {
        element(setGalaxyInput).waitUntilClickable().sendKeys(Long.toString(galaxyInput));
    }

    public void setSystemInput(long systemInput) {
        element(setSystemInput).waitUntilClickable().sendKeys(Long.toString(systemInput));
    }

    public void setPositionInput(long positionInput) {
        element(setPositionInput).waitUntilClickable().sendKeys(Long.toString(positionInput));
    }

    public Timestamp getArrivalTime() {
        return convertStringToTimeStamp(element(getArrivalTime).waitUntilPresent().getText());
    }

    public Timestamp getReturnTime() {
        return convertStringToTimeStamp(element(getReturnTime).waitUntilPresent().getText());
    }

    public void clickOnFleetMenu() {
        element(clickOnFleetMenu).waitUntilClickable().click();
    }

    private Timestamp convertStringToTimeStamp(String time) {
        Date date = null;
        Exception exception = null;
        List<String> patternList = new ArrayList<>(Arrays.asList("dd.MM.yy H:mm:ss", "dd.MM.yy HH:mm:ss",
                "d.MM.yy HH:mm:ss", "d.MM.yy H:mm:ss"));

        for (String pattern : patternList) {
            try {
                date = new SimpleDateFormat(pattern).parse(time);
            } catch (ParseException e) {
                exception = e;
                continue;
            }
            break;
        }
        if (date == null) {
            throw new IllegalArgumentException(exception);
        }
        return new Timestamp(date.getTime());
    }

    public void clickOnCurrentFleetList() {
        element(clickOnCurrentFleetList).waitUntilClickable().click();
    }

    public void clickOnPlanet(Integer planetNumber){
        String xPathPlanet = MessageFormat.format(clickOnXthPlanet, planetNumber);
        Optional<WebElement> planet = getDriver().findElements(By.xpath(xPathPlanet)).stream().filter(Objects::nonNull).findFirst();
        planet.ifPresent(WebElement::click);
    }
    public String showCurrentExpo() {
        return element(showCurrentExpo).waitUntilPresent().getText();
    }

    public String showMaxExpo() {
        return element(showMaxExpo).waitUntilPresent().getText();
    }

    public boolean isMaxExpoAvailable() {
        return element(showMaxExpo).isEnabled();
    }

    public String showExpoSlots() {
        return element(showExpoSlots).waitUntilPresent().getText();
    }

    public List<WebElement> showCurrentFleetList() {
        return getDriver().findElements(By.xpath("//*[@class=\"fleetDetails detailsOpened\"]/*[@class=\"mission neutral textBeefy\"]"));
    }

    public List<Integer> showCurrentExpoFleetList() {
        List<Integer> expoFleet = new ArrayList<>();
        List<WebElement> elements =  getDriver().findElements(By.xpath("//*[@class=\"fleetDetails detailsOpened\"]/*[@class=\"mission neutral textBeefy\"]"));
        for(int i = 0; i < elements.size(); i++){
            String type = elements.get(i).getText();
            if(type.startsWith("Expedition")){
                expoFleet.add(i);
            }
        }
        return expoFleet;
    }

    public Timestamp getReturnTimeOfFleet(WebElement element, int number) {
        try {
            String timeString;
            if(!element(element).isPresent()){
                return new Timestamp(0);
            }
            String index = "["+number+"]";
            String prefix= "//*[@class=\"fleetDetails detailsOpened\"]";
            List<WebElement> list = element(element).findElements(By.xpath(prefix+index+"//*[@class=\"nextabsTime\"]"));
            if(!element(element).findElements(By.xpath(prefix+index+"//*[@class=\"nextabsTime\"]")).isEmpty()){
                timeString = element(element).findElement(By.xpath(prefix+index+"//*[@class=\"nextabsTime\"]")).getText();
            }else{
                timeString = element(element).findElement(By.xpath(prefix+index+"//*[@class=\"absTime\"]")).getText();
            }
            timeString = timeString.replaceAll(" Uhr","");
            LocalTime time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm:ss"));
            long returnTime = (time.getSecond() + time.getMinute() * 60 + time.getHour() * 3600) * 1000;
            Date currentDate = new Date(System.currentTimeMillis());
            LocalDate currentLocalDate = LocalDate.now();
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            String formattedCurrentTime = formatter.format(currentDate);
            LocalTime currentLocalTime = LocalTime.parse(formattedCurrentTime, DateTimeFormatter.ofPattern("HH:mm:ss"));
            long currentTime = (currentLocalTime.getSecond() + currentLocalTime.getMinute() * 60 + currentLocalTime.getHour() * 3600) * 1000;
            boolean returnIsOnNextDay = currentTime > returnTime;

            long returnMillis = -1;
            String date;
            if (returnIsOnNextDay) {
                currentLocalDate.plusDays(1);
            }
            String stringDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(currentDate);
            date = stringDate.substring(0, stringDate.indexOf(' ')+1) + timeString;

            SimpleDateFormat inputFormatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            Date inputDateTime = inputFormatter.parse(date);
            return new Timestamp(inputDateTime.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Timestamp getDestinationTimeOfFleet(int index) {
        Date currentDate = new Date(System.currentTimeMillis());
        String currentFleetDestinationXPath = MessageFormat.format(showCurrentExpoDestinationByIndex, String.valueOf(index+1));
        WebElement currentFleetDestination = getDriver().findElement(By.xpath(currentFleetDestinationXPath));
        String timeString = currentFleetDestination.getText().replaceAll("Uhr","").trim();

        long returnTime = getTimeInMilliSecondsForThisDay(timeString, timeOfDayPattern);
        String formattedCurrentTime = formatDateToString(currentDate, timeOfDayPattern);
        long currentTime = getTimeInMilliSecondsForThisDay (formattedCurrentTime, timeOfDayPattern);
        boolean returnIsOnNextDay = (currentTime-60000) > returnTime;

        if (returnIsOnNextDay) {
            currentDate = addOneDayToDate(currentDate);
        }
        String stringDate = formatDateToString(currentDate, datePattern);
        String date = stringDate.substring(0, stringDate.indexOf(' ')+1) + timeString;
        return new Timestamp(getCurrentTimeMillis(date, datePattern));
    }

    private Date addOneDayToDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return  calendar.getTime();
    }

    private long getCurrentTimeMillis(String date, String pattern) {
        try {
            SimpleDateFormat inputFormatter = new SimpleDateFormat(pattern);
            Date inputDateTime = inputFormatter.parse(date);
            return inputDateTime.getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    private String formatDateToString(Date date, String pattern){
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }
    private long getTimeInMilliSecondsForThisDay(String timeValue, String timePatter){
        LocalTime time = LocalTime.parse(timeValue, DateTimeFormatter.ofPattern(timePatter));
        return (time.getSecond() + time.getMinute() * 60 + time.getHour() * 3600) * 1000;
    }
}