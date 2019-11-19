package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
            ARTICLE_TITLE,
            VIEW_ARTICLE_IN_BROWSER_LINK,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_READING_LIST,
            ADD_ARTICLE_TO_READING_LIST_OVERLAY,
            READING_LIST_OK_BUTTON,
            READING_LIST_NAME_INPUT,
            CLOSE_ARTICLE_BUTTON,
            CLOSE_OVERLAY_BUTTON,
            ARTICLE_WATCHLIST_ACTION_TPL,
            EXISTING_READING_LIST_ELEMENT_TPL;


    public ArticlePageObject(RemoteWebDriver driver) {
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
        } else if(Platform.getInstance().isIOS()){
            return article_title.getAttribute("name");
        } else {
            return article_title.getText();
        }
    }

    private static String getSavedReadingListElement(String folder_name) {
        return EXISTING_READING_LIST_ELEMENT_TPL.replace("{FOLDER}", folder_name);
    }

    private String getArticleWatchlistAction() {
        WebElement element = this.waitForElementPresent(
            OPTIONS_ADD_TO_READING_LIST,
            "",
            5);
        return element.getAttribute("title");
    }

    public boolean checkArticleAddedToWatchlist() {
        String ACTION_REMOVE = "Remove this page from your watchlist";
        return this.getArticleWatchlistAction().equals(ACTION_REMOVE);
    }

    public void swipeToFooter() {
        if(Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    VIEW_ARTICLE_IN_BROWSER_LINK,
                    "can`t find the end of article",
                    40);
        } else if(Platform.getInstance().isIOS()){
            this.swipeUpTillElementAppear(
                    VIEW_ARTICLE_IN_BROWSER_LINK,
                    "can`t find the end of article",
                    40);
        } else {
            this.swipeUpTillElementAppear(
                FOOTER_ELEMENT,
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
                    5);
        } else if (Platform.getInstance().isIOS()){
            this.waitForElementAndClick(
                    CLOSE_OVERLAY_BUTTON,
                    "Cannot close iOS overlay 'Sync your saved articles'",
                    5);
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Cannot close article, cannot find X link",
                    5);
        } else {
            this.driver.navigate().back();
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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addArticleToWatchedList() {
        while (!this.checkArticleAddedToWatchlist()) {
            System.out.println("Before click " + this.getArticleWatchlistAction());
            this.addArticlesToMySaved();
            System.out.println("After click " + this.getArticleWatchlistAction());
        }
    }

    public void closeArticleNoPopup() {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find X link",
                5
        );

    }

}
