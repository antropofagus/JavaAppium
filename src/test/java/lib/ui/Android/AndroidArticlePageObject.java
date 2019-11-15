package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        ARTICLE_TITLE = "xpath://*[@resource-id='org.wikipedia:id/view_page_title_text']";
        VIEW_ARTICLE_IN_BROWSER_LINK = "xpath://*[@text='View page in browser']";
        OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
        OPTIONS_ADD_TO_READING_LIST = "xpath://*[@text='Add to reading list']";
        ADD_ARTICLE_TO_READING_LIST_OVERLAY = "xpath://*[@resource-id='org.wikipedia:id/onboarding_button']";
        READING_LIST_OK_BUTTON = "xpath://*[@text='OK']";
        READING_LIST_NAME_INPUT = "xpath://*[@resource-id='org.wikipedia:id/text_input']";
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        EXISTING_READING_LIST_ELEMENT_TPL = "xpath://*[@text='{FOLDER}']";

    }

    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
