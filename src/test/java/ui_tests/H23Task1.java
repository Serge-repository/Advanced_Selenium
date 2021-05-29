package ui_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.Task1;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;

public class H23Task1 extends Task1 {
    WebDriver driver;
    WebDriverWait wait;
    WebElement element;
    Robot robot;


    @BeforeClass
    public void actionsBeforeTestClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void actionsAfterTestClass() {
        driver.quit();
    }

    @BeforeMethod
    public void actionsBeforeTestMethod() {
        driver.get("https://login.yahoo.com");
    }

    @Test
    public void emailVerifier() {
        By usernameTextbox = By.cssSelector("input[name='username']");
        By passwordTextbox = By.id("login-passwd");
        By confirmSignIn = By.id("ybarMailLink");
        By attachImageButton = By.name("attach-from-computer");

        wait.until(presenceOfElementLocated(usernameTextbox));
        element = driver.findElement(usernameTextbox);
        element.click();

        driver.findElement(By.id("login-username")).sendKeys("testuser5186@yahoo.com");
        driver.findElement(By.cssSelector("input[name='signin']")).click();

        wait.until(presenceOfElementLocated(passwordTextbox));
        driver.findElement(passwordTextbox).sendKeys("admin1admin2");
        driver.findElement(By.id("login-signin")).click();

        wait.until(presenceOfElementLocated(confirmSignIn));
        driver.findElement(confirmSignIn).click();

        wait.until(presenceOfElementLocated(By.cssSelector("input[role='combobox']")));
        driver.findElement(By.cssSelector("a[data-test-id='compose-button']")).click();

        element = driver.findElement(By.id("message-to-field"));
        element.click();
        element.sendKeys("testuser5186@yahoo.com");

        element = driver.findElement(By.cssSelector("input[data-test-id='compose-subject']"));
        element.click();
        element.sendKeys("Test email");

        element = driver.findElement(By.xpath("(//div[@id='editor-container']/div)[1]"));
        element.click();
        element.sendKeys("Hello!");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("button[data-test-id='icon-btn-attach']")).click();
        driver.findElement(attachImageButton).click();

        attachImageByRobot(robot);
        driver.findElement(By.xpath("//button[@data-test-id='compose-send-button']/span")).click();

        unexpectedAlertHandler(wait, driver);

        try {
            Thread.sleep(180000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().refresh();
        wait.until(presenceOfElementLocated(By.cssSelector("input[role='combobox']")));
        assertEquals(driver.findElement(By.cssSelector("span[title='Test email']")).getText(), "Test email", "Checking that email was sent");
        driver.findElement(By.xpath("(//div[@data-test-id='attachment-icon-wrapper'])[1]")).click();

        element = driver.findElement(By.cssSelector("span[data-test-id='message-group-subject-text']"));
        assertEquals(element.getText(), "Test email", "Verifying email subject");

        element = driver.findElement(By.xpath("//div[@data-test-id='message-view-body-content']/div/div/div/div/div/div"));
        assertEquals(element.getText(), "Hello!", "Verifying email content");

        element = driver.findElement(By.cssSelector("span[title='orange.jpg']>div>span>span"));
        assertEquals(element.getText(), "orange", "Verifying file is attached");
    }
}