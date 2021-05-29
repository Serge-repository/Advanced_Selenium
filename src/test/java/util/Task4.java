package util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Task4 {
    public static void dragAndDropAction(WebDriver driver, WebElement origin, WebElement destination) {
        Actions action = new Actions(driver);
        action.dragAndDrop(origin, destination).build().perform();
    }

    public static void scrollAction(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
