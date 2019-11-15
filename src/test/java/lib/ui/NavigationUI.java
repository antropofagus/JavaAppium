package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject {
    protected static String
        READING_LISTS_BUTTON;

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void openReadingLists() {
        this.waitForElementAndClick(
                READING_LISTS_BUTTON,
                "Can`t find navigation button to My lists",
                15);
    }
}
