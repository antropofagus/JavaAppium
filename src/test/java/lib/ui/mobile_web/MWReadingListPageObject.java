package lib.ui.mobile_web;

import lib.ui.ReadingListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWReadingListPageObject extends ReadingListsPageObject {
    static {
        READING_LIST_ITEM = "css:ul.watchlist>li";
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class,'watchlist')]/li[contains(@title, '{ARTICLE_TITLE}')]";
        UNWATCH_LINK_BY_TITLE_TPL = "css:ul.watchlist>li[title='{ARTICLE_TITLE}']>a.mw-ui-icon";
    }
    public MWReadingListPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
