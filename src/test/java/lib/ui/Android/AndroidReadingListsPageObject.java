package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ReadingListsPageObject;

public class AndroidReadingListsPageObject extends ReadingListsPageObject {
    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{NAME}']";
        READING_LIST_ITEM = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{ARTICLE_TITLE}']";
    }

    public AndroidReadingListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
