package lib.ui.ios;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSArticlePageObject extends ArticlePageObject {
    static {
        ARTICLE_TITLE = "xpath://*[@text='Java (programming language)']";
        VIEW_ARTICLE_IN_BROWSER_LINK = "id:View article in browser";
        OPTIONS_ADD_TO_READING_LIST = "xpath://XCUIElementTypeButton[@name='Save for later']";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        CLOSE_OVERLAY_BUTTON = "xpath://XCUIElementTypeButton[@name='places auth close']";
    }
    public IOSArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

}
