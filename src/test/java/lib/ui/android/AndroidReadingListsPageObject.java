package lib.ui.android;

import lib.ui.ReadingListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidReadingListsPageObject extends ReadingListsPageObject {
    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{NAME}']";
        READING_LIST_ITEM = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{ARTICLE_TITLE}']";
    }

    public AndroidReadingListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
