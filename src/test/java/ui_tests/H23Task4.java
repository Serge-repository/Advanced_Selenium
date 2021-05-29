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
import util.Task4;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class H23Task4 extends Task4 {
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
        driver.get("http://demo.guru99.com/test/drag_drop.html");
    }

    @Test
    public void dragAndDrop() {
        wait.until(presenceOfElementLocated(By.tagName("h2")));
        element = driver.findElement(By.tagName("h2"));
        scrollAction(driver, element);

        WebElement origin = driver.findElement(By.cssSelector("#fourth:nth-child(2)"));
        WebElement destination = driver.findElement(By.id("amt7"));
        dragAndDropAction(driver, origin, destination);
        assertTrue(driver.findElement(By.xpath("//ol[@id='amt7']/li[@data-id='2']")).isDisplayed());

        WebElement originSecondElement = driver.findElement(By.cssSelector("#fourth:nth-child(4)"));
        WebElement destinationSecondElement = driver.findElement(By.id("amt8"));
        dragAndDropAction(driver, originSecondElement, destinationSecondElement);
        assertTrue(driver.findElement(By.xpath("//ol[@id='amt8']/li[@data-id='2']")).isDisplayed());

        WebElement originBankElement = driver.findElement(By.id("credit2"));
        WebElement destinationBankElement = driver.findElement(By.id("bank"));
        dragAndDropAction(driver, originBankElement, destinationBankElement);
        assertTrue(driver.findElement(By.xpath("//ol[@id='bank']/li[@data-id='5']")).isDisplayed());

        WebElement originSalesElement = driver.findElement(By.id("credit1"));
        WebElement destinationSalesElement = driver.findElement(By.id("loan"));
        dragAndDropAction(driver, originSalesElement, destinationSalesElement);
        assertTrue(driver.findElement(By.xpath("//ol[@id='loan']/li[@data-id='6']")).isDisplayed());

        assertEquals(driver.findElement(By.xpath("(//a[@class='button button-green'])[1]")).getText(), "Perfect!", "Checking if Perfect! message appears");
    }
}
