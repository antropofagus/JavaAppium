package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.regex.Pattern;

abstract public class NavigationUI extends MainPageObject {
    protected static String
        READING_LISTS_BUTTON,
        MENU_LOGIN_LINK,
        MENU_LOGOUT_LINK,
        MAIN_MENU,
        MAIN_MENU_BUTTON;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void openReadingLists() {
        if(Platform.getInstance().isMW()) {
            this.openMainMenu();
            this.waitForElementPresent(
                MAIN_MENU,
                "main menu is not visible",
                15);
        }
        this.waitForElementAndClick(
                READING_LISTS_BUTTON,
                "Can`t find navigation button to My lists",
                15);
    }

    public void openLoginPageFromMainMenu() {
        this.openMainMenu();
        this.waitForElementAndClick(
                MENU_LOGIN_LINK,
                "Can`t find and click Login link in Main menu",
                10);
    }

    public void openLogoutFromMainMenu() {
        this.openMainMenu();
        this.waitForElementAndClick(
                MENU_LOGOUT_LINK,
                "can`t find and click logout button",
                10);
    }

    public void openMainMenu() {
        this.waitForElementAndClick(
                MAIN_MENU_BUTTON,
                "can`t open Main menu",
                10);
    }

}
