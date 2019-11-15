package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public void clickElementToTheRightUpperCorner(String locator, String error_message) {
        WebElement element = this.waitForElementPresent(locator + "/..", error_message, 10);
        int right_x = element.getLocation().getX();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y)/2;
        int width = element.getSize().getWidth();

        int point_to_click_x = (right_x + width) - 3;
        int point_to_click_y = middle_y;

        TouchAction action = new TouchAction(driver);
        PointOption pointOption = new PointOption();
        action.tap(pointOption.withCoordinates(point_to_click_x, point_to_click_y));
        //action.tap(point_to_click_x, point_to_click_y);
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeout) {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }
    public boolean waitForElementNotPresent(String locator, String error_message, long timeout) {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeout) {
        WebElement element = waitForElementPresent(locator, error_message, timeout);
        element.click();
        return element;
    }
    public WebElement waitForElementAndSendKeys(String locator, String error_message, String value, long timeout) {
        WebElement element = waitForElementPresent(locator, error_message, timeout);
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeout) {
        WebElement element = waitForElementPresent(locator, error_message, timeout);
        element.clear();
        return element;
    }

    public void assertElementNotPresent(String locator, String error_message) {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements > 0){
            String default_message = "An element '" + locator + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public boolean isElementLocatedOnScreen(String locator) {
        int element_location_by_y = this.waitForElementPresent(locator, "Can`t find element by locator", 10).getLocation().getY();
        int screen_syze_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_syze_by_y;
    }

    public void swipeUpTillElementAppear(String locator, String error_message, int max_swipes) {
        int already_swiped = 0;
        while (!this.isElementLocatedOnScreen(locator)) {
            if(already_swiped > max_swipes) {
                Assert.assertTrue(error_message, this.isElementLocatedOnScreen(locator));
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width /2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        PointOption pointOption = new PointOption();
        WaitOptions waitOptions = new WaitOptions();
        action.press(pointOption
                .withCoordinates(x, start_y))
                .waitAction(waitOptions.withDuration(Duration.ofSeconds(timeOfSwipe)))
                .moveTo(pointOption.withCoordinates(x, end_y)).release().perform();
        //action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(String locator, String error_message, int max_swipes) {
        By by = getLocatorByString(locator);
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped > max_swipes) {
                waitForElementPresent(locator, "can`t find element by swiping up \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    protected void swipeElementToLeft(String locator, String error_message) {
        WebElement element = waitForElementPresent(
                locator,
                error_message,
                10);
        Dimension size = driver.manage().window().getSize();
        int left_x = (int) (size.width * 0.2);
        int right_x = (int) (size.width * 0.8);
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        PointOption pointOption = new PointOption();
        WaitOptions waitOptions = new WaitOptions();
        action.press(pointOption.withCoordinates(right_x, middle_y));
        action.waitAction(waitOptions.withDuration(Duration.ofSeconds(30)));
        if(Platform.getInstance().isAndroid()) {
            action.moveTo(pointOption.withCoordinates(left_x, middle_y));
        } else {
            int offset_x = (-1 * element.getSize().getWidth());
            action.moveTo(pointOption.withCoordinates(offset_x, 0));
        }
//        action.press(right_x, middle_y);
//        action.waitAction(300);
//        if(Platform.getInstance().isAndroid()) {
//            action.moveTo(left_x, middle_y);
//        } else {
//            int offset_x = (-1 * element.getSize().getWidth());
//            action.moveTo(offset_x, 0);
//        }
        action.release();
        action.perform();
    }

    public int getAmountOfElements(String locator) {
        By by = getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public List<WebElement> getListOfElements(String locator) {
        By by = getLocatorByString(locator);
        List<WebElement> elements = driver.findElements(by);
        return elements;
    }

    protected boolean assertElementPresent(String locator, String error_message) {
        int amount_of_elements = this.getAmountOfElements(locator);
        return amount_of_elements > 0;
    }

    private String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    private By getLocatorByString(String locator_with_type) {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if(by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if(by_type.equals("id")) {
            return By.id(locator);
        } else {
            throw new IllegalArgumentException("can`t get type of locator. locator: " + locator_with_type);
        }
    }


}
