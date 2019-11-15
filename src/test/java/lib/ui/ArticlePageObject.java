package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
            ARTICLE_TITLE,
            VIEW_ARTICLE_IN_BROWSER_LINK,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_READING_LIST,
            ADD_ARTICLE_TO_READING_LIST_OVERLAY,
            READING_LIST_OK_BUTTON,
            READING_LIST_NAME_INPUT,
            CLOSE_ARTICLE_BUTTON,
            CLOSE_OVERLAY_BUTTON,
            EXISTING_READING_LIST_ELEMENT_TPL;


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForArticleTitle() {
            return this.waitForElementPresent(
                    ARTICLE_TITLE,
                    "cant find article title element",
                    50);
    }

    public String getArticleTitle() {
        WebElement article_title = this.waitForArticleTitle();
        if(Platform.getInstance().isAndroid()) {
            return article_title.getAttribute("text");
        } else {
            return article_title.getAttribute("name");
        }
    }

    private static String getSavedReadingListElement(String folder_name) {
        return EXISTING_READING_LIST_ELEMENT_TPL.replace("{FOLDER}", folder_name);
    }

    public void swipeToFooter() {
        if(Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    VIEW_ARTICLE_IN_BROWSER_LINK,
                    "can`t find the end of article",
                    40);
        } else {
            this.swipeUpTillElementAppear(
                    VIEW_ARTICLE_IN_BROWSER_LINK,
                    "can`t find the end of article",
                    40);
        }
    }

    public void addArticleToNewReadingList(String folder_name) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "can`t find article options element",
                5);

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_READING_LIST,
                "can`t find option to add article to reading list",
                15);

        this.waitForElementAndClick(
                ADD_ARTICLE_TO_READING_LIST_OVERLAY,
                "Can`t find 'got it' tip overlay",
                15);

        this.waitForElementAndClear(
                READING_LIST_NAME_INPUT,
                "can`t find input to set reading list name",
                15);

        this.waitForElementAndSendKeys(
                READING_LIST_NAME_INPUT,
                "can`t find and set input and set reading list name",
                folder_name,
                5);

        this.waitForElementAndClick(
                READING_LIST_OK_BUTTON,
                "can`t find OK button",
                5);
    }

    public void addArticleToExistingReadingList(String folder_name) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "can`t find article options element",
                5);

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_READING_LIST,
                "can`t find option to add article to reading list",
                15);

        this.waitForElementAndClick(
                getSavedReadingListElement(folder_name),
                    "Can`t find folder in MyList with name: " + folder_name,
                    15);
    }

    public void closeArticle() {
        if (Platform.getInstance().isAndroid()){
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Cannot close article, cannot find X link",
                    5
            );
        } else {
            this.waitForElementAndClick(
                    CLOSE_OVERLAY_BUTTON,
                    "Cannot close iOS overlay 'Sync your saved articles'",
                    5
            );
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Cannot close article, cannot find X link",
                    5
            );
        }
    }

    public boolean assertArticleTitlePresent() {
        return this.assertElementPresent(
                ARTICLE_TITLE,
                "article title is not present on page");
    }

    public void addArticlesToMySaved() {
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_READING_LIST,
                "can`t find and click add to reading list option",
                10);
    }

    public void closeArticleNoPopup() {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find X link",
                5
        );

    }

}
