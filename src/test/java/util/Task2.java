package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertTrue;

public class Task2 {
    public static void assertion(WebDriverWait wait) {
        WebElement element = wait.until(presenceOfElementLocated(By.xpath("//a[@href='Logout.php']")));
        assertTrue(element.getText().contains("Log out"), "Checking that user still logged in");
    }
}
