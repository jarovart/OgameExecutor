import Logic.OgameExec;
import lombok.SneakyThrows;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {


    public static void main1(String[] args) throws InterruptedException, IOException {
        OgameExec ogameExec = new OgameExec();
         /**ogameExec.startApplication();
        while(true){
            try{
                ogameExec.restartApplication();
                ogameExec.startSimulation();
            }catch (Exception e){
                e.printStackTrace();
                ogameExec.wait(60000);
                ogameExec.closeAllWindowsExceptOne();
            }
        }*/
        ogameExec.clickOnGeneral(1);


    }

    private static WebDriver initDriver() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        return driver;
    }
    static JButton button;
    static JButton buttonstop;
    static JTextField field;
    static OgameExec ogameExec;

    public static void main(String[] args) throws InterruptedException, IOException {
       /* JFrame frame = new JFrame("JFrame Example");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel("JFrame By Example");
        button = new JButton();
        buttonstop = new JButton();
        field = new JTextField("1000");
        button.setText("Start");
        buttonstop.setText("Stop");*/
        ogameExec = new OgameExec();
        /*panel.add(label);
        panel.add(button);
        panel.add(buttonstop);
        panel.add(field);
        frame.add(panel);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Thread start = new Thread(){
            @SneakyThrows
            @Override
            public void start() {
                ogameExec.clickOnGeneral(Integer.parseInt(field.getText()));
            }
        };
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start.start();
            }
        });
        buttonstop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start.stop();
            }
        });*/
    }
}
