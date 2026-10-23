
package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SliderProgressBarTest {

    private WebDriver driver;
    private WebDriverWait wait;

    // Launch browser before executing tests
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(20)
        );
    }

    // Test 1: Verify slider minimum value
    @Test
    public void testSliderMinimum() {
        driver.get("https://demoqa.com/slider");

        WebElement slider = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[type='range']")
            )
        );

        slider.sendKeys(Keys.HOME);

        Assert.assertEquals(
            slider.getAttribute("value"),
            "0",
            "Slider should reach minimum value 0"
        );

        System.out.println("Test 1 passed: Slider minimum is 0");
    }

    // Test 2: Verify slider maximum value
    @Test
    public void testSliderMaximum() {
        driver.get("https://demoqa.com/slider");

        WebElement slider = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[type='range']")
            )
        );

        slider.sendKeys(Keys.END);

        Assert.assertEquals(
            slider.getAttribute("value"),
            "100",
            "Slider should reach maximum value 100"
        );

        System.out.println("Test 2 passed: Slider maximum is 100");
    }

    // Test 3: Verify slider can move to a middle value
    @Test
    public void testSliderMiddleValue() {
        driver.get("https://demoqa.com/slider");

        WebElement slider = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[type='range']")
            )
        );

        slider.click();

        // Move from minimum to the middle using arrow keys
        slider.sendKeys(Keys.HOME);

        for (int i = 0; i < 50; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
        }

        String actualValue = slider.getAttribute("value");

        Assert.assertEquals(
            actualValue,
            "50",
            "Slider should reach middle value 50"
        );

        System.out.println("Test 3 passed: Slider middle value is 50");
    }

    // Test 4: Verify progress bar reaches 100 percent
    @Test
    public void testProgressBarCompletes() {
        driver.get("https://demoqa.com/progress-bar");

        WebElement startButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.id("startStopButton")
            )
        );

        startButton.click();

        // Wait until the progress bar reaches 100%
        wait.until(
            ExpectedConditions.attributeToBe(
                By.cssSelector("[role='progressbar']"),
                "aria-valuenow",
                "100"
            )
        );

        WebElement progressBar = driver.findElement(
            By.cssSelector("[role='progressbar']")
        );

        Assert.assertEquals(
            progressBar.getAttribute("aria-valuenow"),
            "100",
            "Progress bar should reach 100 percent"
        );

        System.out.println("Test 4 passed: Progress bar reached 100%");
    }

    // Test 5: Verify progress bar can be stopped
    @Test(dependsOnMethods = "testProgressBarCompletes")
    public void testProgressBarStopButton() {
        driver.get("https://demoqa.com/progress-bar");

        WebElement startButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.id("startStopButton")
            )
        );

        startButton.click();

        WebElement progressBar = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[role='progressbar']")
            )
        );

        // Wait for progress to begin
        wait.until(driver ->
            Integer.parseInt(
                progressBar.getAttribute("aria-valuenow")
            ) > 0
        );

        startButton.click();

        String buttonText = startButton.getText();

        Assert.assertEquals(
            buttonText,
            "Start",
            "Button should display Start after stopping progress"
        );

        System.out.println("Test 5 passed: Progress bar can be stopped");
    }

    // Close browser after all tests
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
