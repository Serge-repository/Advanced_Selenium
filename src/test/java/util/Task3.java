package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Task3 {
    public static void mouseMove(WebDriver driver, WebElement element) {
        Actions action1 = new Actions(driver);
        action1.moveToElement(element).build().perform();
    }
}
