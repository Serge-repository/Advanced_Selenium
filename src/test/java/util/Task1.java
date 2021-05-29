package util;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Task1 {
    public static void attachImageByRobot(Robot robot) {
        Path path = Paths.get(System.getProperty("user.home"));
        String fileSeparator = FileSystems.getDefault().getSeparator();
        String pathAsString = path.toString();
        StringSelection filePath = new StringSelection(pathAsString + fileSeparator + "orange.jpg");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);
        robot.setAutoDelay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.setAutoDelay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public static void unexpectedAlertHandler(WebDriverWait wait, WebDriver driver) {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
