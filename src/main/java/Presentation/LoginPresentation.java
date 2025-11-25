package Presentation;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class LoginPresentation extends PageObject {

    @FindBy(xpath = "//*[@id=\"canvas\"]")
    private WebElement general;
    @FindBy(xpath = "//*[@id=\"email\"]")
    private WebElement email;
    @FindBy(xpath = "//button[@class='cookiebanner5']")
    private WebElement clickOnAcceptCookies;
    @FindBy(xpath = "//ul[@class='tabsList']/li[1]")
    private WebElement clickOnEinloggenTab;
    @FindBy(xpath = "//*[@id=\"loginForm\"]/div[2]/div/input")
    private WebElement emailInput;
    @FindBy(xpath = "//*[@id=\"loginForm\"]/div[3]/div/input")
    private WebElement passwordInput;
    @FindBy(xpath = "//button[@class='button button-primary button-lg']")
    private WebElement clickOnEinloggen;
    @FindBy(xpath = "//button[@class='button button-primary button-md']")
    private WebElement clickOnSpielen;
    @FindBy(xpath = "//*[@id=\"joinGame\"]/button")
    private WebElement clickOnLastGame;
    @FindBy(xpath = "//*[@id=\"accountlist\"]/div/div[1]/div[2]/div[1]/div/div[11]/button")
    private WebElement clickOnFirstListSpielen;
    @FindBy(xpath = "//*[@id=\"menuTable\"]/li[1]/a/span")
    private WebElement clickOnOverviewMenu;
    @FindBy(xpath = "//*[@id=\"joinGame\"]/button/span")
    private WebElement getLastServerName;
    @FindBy(xpath = "//*[@id=\"joinGame\"]/a/button")
    private WebElement clickOnPlay;
    private String listAllServerNames = "//*[@id=\"accountlist\"]/div/div[1]/div[2]/div/div/div[4]/div";
    private String playButtonViaServerName = "//*[@id=\"accountlist\"]/div/div[1]/div[2]/div[{0}]/div/div[11]/button";
    private String gameName;

    public LoginPresentation(WebDriver driver) {
        super(driver);
    }

    public void loginWithCredentials(String email, String password, String gameName) {
        this.gameName = gameName;
        if (element(clickOnAcceptCookies).isEnabled()) {
            element(clickOnAcceptCookies).waitUntilClickable().click();
        }
        element(clickOnEinloggenTab).waitUntilEnabled().waitUntilClickable().click();
        element(emailInput).waitUntilEnabled().waitUntilClickable().sendKeys(email);
        element(passwordInput).waitUntilEnabled().waitUntilClickable().sendKeys(password);
        element(clickOnEinloggen).waitUntilEnabled().waitUntilClickable().click();

        reJoinGame();
    }

    public boolean reJoinGame() {
        if (element(clickOnOverviewMenu).isPresent()) {
            element(clickOnOverviewMenu).waitUntilEnabled().waitUntilClickable().click();
        }
        if (element(clickOnOverviewMenu).isPresent()) {
            return true;
        }
        if ((gameName == null || gameName.isEmpty() || gameName.equals("Last")) && element(clickOnLastGame).isPresent()) {
            element(clickOnLastGame).waitUntilEnabled().waitUntilClickable().click();
        } else if (gameName != null && !gameName.isEmpty()) {
            if (element(clickOnLastGame).isPresent() && element(getLastServerName).getText().contains(gameName)) {
                element(clickOnLastGame).waitUntilEnabled().waitUntilClickable().click();
            } else {
                element(clickOnPlay).waitUntilEnabled().waitUntilClickable().click();
                List<WebElement> serverNames = getDriver().findElements(By.xpath(listAllServerNames));
                for (int i = 0; i < serverNames.size(); i++) {
                    if (serverNames.get(i).getText().trim().equals(gameName)) {
                        String playButtonXPath = MessageFormat.format(playButtonViaServerName, i + 1);
                        getDriver().findElement(By.xpath(playButtonXPath)).click();
                        break;
                    }
                }
            }
        } else {
            return false;
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        getDriver().close();
        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(0));
        return true;
    }

    private void takeScreenshot(String name) {
        File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            System.out.println(new File(".").getAbsolutePath());
            FileUtils.copyFile(scrFile, new File("./ErrorPictures/" + name + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickOnJoinGame() {
        element(clickOnSpielen).waitUntilClickable().click();
        element(clickOnFirstListSpielen).waitUntilClickable().click();
    }

    public void clickOnGeneral(String input) {
        element(general).waitUntilClickable().sendKeys(input);
    }
}
