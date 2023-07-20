package Controller;

import Presentation.LoginPresentation;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class LoginController{

    private LoginPresentation loginPresentation;

    public LoginController(WebDriver driver){
        loginPresentation = new LoginPresentation(driver);
    }

    public Map<String, String> loginWithCredentials() throws IOException {
        Map<String, String> data = loadCredentials();
        loginPresentation.loginWithCredentials(data.get("Login"), data.get("Password"), data.get("ServerName"));
        return data;
    }

    private Map<String, String> loadCredentials() throws IOException {
        Map<String, String> data = new HashMap<>();
        File file=new File("ogameExecData.txt");    //creates a new file instance
        FileReader fr=new FileReader(file);   //reads the file
        BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
        StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters
        String line;
        while((line=br.readLine())!=null)
        {
            String array[] = line.split(":");
            if(array.length > 1){
                data.put(array[0], array[1]);
            }

        }
        fr.close();    //closes the stream and release the resources
        return data;
    }

    public void clickOnJoinGame(){
        loginPresentation.clickOnJoinGame();
    }

    public boolean reJoinGame(){
        return loginPresentation.reJoinGame();
    }

    public void clickOnGeneral(String input){
        loginPresentation.clickOnGeneral(input);
    }

}
