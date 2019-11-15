package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

import java.util.List;

abstract public class ReadingListsPageObject extends MainPageObject{
    protected static String
        FOLDER_BY_NAME_TPL,
        READING_LIST_ITEM,
        ARTICLE_BY_TITLE_TPL;

    /*TEMPLATE METHODS*/
    private static String getFolderXpathByName(String folder_name) {
        return FOLDER_BY_NAME_TPL.replace("{NAME}", folder_name);
    }

    private static String getArticleXpathByName(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{ARTICLE_TITLE}", article_title);
    }
    /*TEMPLATE METHODS*/

    public ReadingListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String folder_name) {
        String folder_name_xpath = getFolderXpathByName(folder_name);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cant`t find created folder by name "+ folder_name,
                5);
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getArticleXpathByName(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Can`t find saved article by title " + article_title,
                15);
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getArticleXpathByName(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title " + article_title,
                15);
    }

    public int getAmountOfSavedArticles() {
        return this.getAmountOfElements(READING_LIST_ITEM);
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getArticleXpathByName(article_title);
        this.swipeElementToLeft(
                article_xpath,
                "Can`t find saved article with title "+ article_title);
        if(Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(
                    article_xpath,
                    "can`t find saved article");
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void openArticleFromReadingList(String article_title) {
        String article_xpath = getArticleXpathByName(article_title);
        this.waitForElementAndClick(
                article_xpath,
                "Can`t find and click article by title " + article_title,
                15);
    }

    public boolean checkArticleIsExistInSavedList(String article_title) {
        String article_xpath = getArticleXpathByName(article_title);
        return this.assertElementPresent(
                article_xpath,
                "Can`t find article with title "+ article_title + " in saved list");
    }

}
