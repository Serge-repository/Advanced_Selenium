package ui_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.Task2;

import java.util.Set;

public class H23Task2 extends Task2 {
    WebDriver driver;
    WebDriverWait wait;
    String correctLogin = "1303";
    String correctPassword = "Guru99";

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
        driver.get("http://demo.guru99.com/Agile_Project/Agi_V1/index.php");
    }

    @Test
    public void manageCookies() {
        driver.findElement(By.name("uid")).sendKeys(correctLogin);
        driver.findElement(By.name("password")).sendKeys(correctPassword);
        driver.findElement(By.name("btnLogin")).click();
        assertion(wait);

        Set<Cookie> cookies = driver.manage().getCookies();
        cookies.forEach(System.out::println);

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        assertion(wait);
    }
}