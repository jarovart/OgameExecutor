package Controller;

import Presentation.LoginPresentation;
import org.openqa.selenium.WebDriver;

import java.io.IOException;


public class LoginController {

    private LoginPresentation loginPresentation;

    public LoginController(WebDriver driver) {
        loginPresentation = new LoginPresentation(driver);
    }

    public void loginWithCredentials(String email, String password, String gameName) throws IOException {
        loginPresentation.loginWithCredentials(email, password, gameName);
    }

    public void clickOnJoinGame() {
        loginPresentation.clickOnJoinGame();
    }

    public boolean reJoinGame() {
        return loginPresentation.reJoinGame();
    }

    public void clickOnGeneral(String input) {
        loginPresentation.clickOnGeneral(input);
    }

}
