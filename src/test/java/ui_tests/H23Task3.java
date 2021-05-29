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
import util.Task3;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertTrue;

public class H23Task3 extends Task3 {
    WebDriver driver;
    WebDriverWait wait;
    WebElement element;

    @BeforeClass
    public void actionsBeforeTestClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterClass
    public void actionsAfterTestClass() {
        driver.quit();
    }

    @BeforeMethod
    public void actionsBeforeTestMethod() {
        driver.get("http://demo.guru99.com/test/guru99home/");
    }

    @Test
    public void videoPlayerCheck() {
        By playButton = By.xpath("//button[@class='ytp-large-play-button ytp-button']");
        By pauseButton = By.xpath("//button[@aria-label='Пауза (k)']");
        By playerInnerFrame = By.xpath("(//iframe)[1]");

        element = driver.findElement(playerInnerFrame);
        driver.switchTo().frame(element);
        wait.until(presenceOfElementLocated(playButton));
        driver.findElement(playButton).click();

        wait.until(presenceOfElementLocated(pauseButton));
        element = driver.findElement(pauseButton);
        mouseMove(driver, element);
        assertTrue(driver.findElement(pauseButton).isDisplayed(), "Pause button is visible");

        driver.switchTo().parentFrame();
        element = driver.findElement(By.xpath("(//div[@class='rt-grid-6 rt-omega'])[1]"));
        mouseMove(driver, element);

        element = driver.findElement(playerInnerFrame);
        driver.switchTo().frame(element);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertTrue(driver.findElement(By.xpath("//button[@aria-label='Пауза (k)'][@title='Пауза (k)']")).isEnabled(), "Pause button is hidden");
    }
}