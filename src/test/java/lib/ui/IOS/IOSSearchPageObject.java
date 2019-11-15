package lib.ui.IOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT =  "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{SUBSTRING}')]";
        SEARCH_CANCEL_BTN = "xpath://XCUIElementTypeButton[@name='Close']";
        SEARCH_RESULTS_CONTAINER = "";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_RESULTS_LIST = "xpath://XCUIElementTypeCollectionView";
        SEARCH_RESULTS_LIST_ITEM = "xpath://XCUIElementTypeLink";
        SEARCH_ARTICLE_TITLE = SEARCH_RESULTS_LIST_ITEM;

    }

    public IOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
