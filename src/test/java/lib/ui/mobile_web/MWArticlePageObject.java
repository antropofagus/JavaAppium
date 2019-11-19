package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        ARTICLE_TITLE = "css:h1";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_ADD_TO_READING_LIST = "css:#page-actions li #ca-watch";
        ARTICLE_WATCHLIST_ACTION_TPL = "css:#page-actions li #ca-watch[title='{ACTION}']";

    }
    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
