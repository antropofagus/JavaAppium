package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject {

    /* временно заменила id на xpath, т.к. поиск по id благополучно сломался с обновлением appium до версии 1.15.0
    * */
    private static final String
        LEARN_MORE_LINK = "xpath://XCUIElementTypeButton[@name='Learn more about Wikipedia']",
        NEXT_BUTTON = "xpath://XCUIElementTypeButton[@name='Next']",
        ADD_OR_EDIT_PREFERRED_LANG_TEXT = "xpath://XCUIElementTypeButton[@name='Add or edit preferred languages']",
        LEARN_MORE_ABOUT_DATA_COLLECTED = "xpath://XCUIElementTypeButton[@name='Learn more about data collected']",
        NEW_WAY_TO_EXPLORE = "xpath://XCUIElementTypeStaticText[@name='New ways to explore']",
        GET_STARTED_BUTTON = "xpath://XCUIElementTypeButton[@name='Get started']",
        SKIP_LINK = "xpath://XCUIElementTypeButton[@name='Skip']";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        this.waitForElementPresent(
                LEARN_MORE_LINK,
                "Can`t find '" + LEARN_MORE_LINK + "' link",
                20);
    }

    public void waitForNewWayToExploreText() {
        this.waitForElementPresent(
                NEW_WAY_TO_EXPLORE,
                "Can`t find '" + NEW_WAY_TO_EXPLORE + "' link",
                20);
    }

    public void waitForAddOrEditPreferredLangText() {
        this.waitForElementPresent(
                ADD_OR_EDIT_PREFERRED_LANG_TEXT,
                "Can`t find '" + ADD_OR_EDIT_PREFERRED_LANG_TEXT + "' link",
                20);
    }

    public void waitForLearnMoreAboutDataCollectedText() {
        this.waitForElementPresent(
                LEARN_MORE_ABOUT_DATA_COLLECTED,
                "Can`t find '" + LEARN_MORE_ABOUT_DATA_COLLECTED + "' link",
                20);
    }


    public void clickNextButton() {
        this.waitForElementAndClick(
                NEXT_BUTTON,
                "Can`t find and click '" + NEXT_BUTTON + "' button",
                20);
    }

    public void clickGetStartedButton() {
        this.waitForElementAndClick(
                GET_STARTED_BUTTON,
                "Can`t find and click '" + GET_STARTED_BUTTON + "' button",
                20);
    }

    public void clickSkip() {
        this.waitForElementAndClick(
                SKIP_LINK,
                "Can`t find and click 'Skip' link",
                20);
    }
}
