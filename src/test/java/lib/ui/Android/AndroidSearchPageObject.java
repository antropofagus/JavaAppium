package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_container']";
        SEARCH_INPUT = "xpath://*[@resource-id='org.wikipedia:id/search_src_text']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text, '{SUBSTRING}')]";
        SEARCH_CANCEL_BTN = "xpath://*[@resource-id='org.wikipedia:id/search_close_btn']";
        SEARCH_RESULTS_CONTAINER = "xpath://*[@resource-id='org.wikipedia:id/fragment_search_results']";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
        SEARCH_RESULTS_LIST = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']";
        SEARCH_RESULTS_LIST_ITEM = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']";
        SEARCH_ARTICLE_TITLE = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";
    }

    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }


}
