package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT =  "css:form>input[type='search']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "css:.results-list-container ul li[title='{SUBSTRING}']";
        SEARCH_CANCEL_BTN = "css:button.cancel";
        SEARCH_RESULTS_CONTAINER = "css:.results-list-container";
        SEARCH_RESULT_ELEMENT = "css:.results-list-container ul li";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://p[contains(text(), 'No page with this title.')]";
        SEARCH_RESULTS_LIST = "css:.results-list-container ul";
        SEARCH_RESULTS_LIST_ITEM = "css:.results-list-container ul li";
        SEARCH_ARTICLE_TITLE = SEARCH_RESULTS_LIST_ITEM;

    }

    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
