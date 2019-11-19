package lib.ui;

import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ReadingListsPageObject extends MainPageObject{
    protected static String
        FOLDER_BY_NAME_TPL,
        READING_LIST_ITEM,
        ARTICLE_BY_TITLE_TPL,
        UNWATCH_LINK_BY_TITLE_TPL;
    private static final String
            ARTICLE_WATCHED_MARK = "Remove this page from your watchlist",
            ARTICLE_UNWATCHED_MARK = "Add this page to your watchlist";

    /*TEMPLATE METHODS*/
    private static String getFolderXpathByName(String folder_name) {
        return FOLDER_BY_NAME_TPL.replace("{NAME}", folder_name);
    }

    private static String getArticleLocatorByName(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{ARTICLE_TITLE}", article_title);
    }

    private static String getArticleUnwatchLinkLocatorByName(String article_title) {
        return UNWATCH_LINK_BY_TITLE_TPL.replace("{ARTICLE_TITLE}", article_title);
    }
    /*TEMPLATE METHODS*/

    public ReadingListsPageObject(RemoteWebDriver driver) {
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
        String article_xpath = getArticleLocatorByName(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Can`t find saved article by title " + article_title,
                15);
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getArticleLocatorByName(article_title);
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
        String article_locator = getArticleLocatorByName(article_title);
        this.swipeElementToLeft(
                article_locator,
                "Can`t find saved article with title "+ article_title);
        if(Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(
                    article_locator,
                    "can`t find saved article");
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }
    /* объединила методы для удаления статьи из reading list для всех платформ в один,
    чтобы избавиться от нагромождения условий в коде самого теста
    * */
    public void removeArticleFromReadingList(String article_title) {
        String article_locator = getArticleLocatorByName(article_title);
        if(Platform.getInstance().isMW()) {
            this.unwatchArticleMW(article_title);
        } else {
            this.swipeByArticleToDelete(article_locator);
        }

    }

    private void unwatchArticleMW(String article_title) {
        String article_unwatch_link_locator = getArticleUnwatchLinkLocatorByName(article_title);
        while (!this.checkArticleMarkUnwatched(article_title)) {
            this.waitForElementAndClick(
                    article_unwatch_link_locator,
                    "can`t find unwatch link for article " + article_title,
                    15);
        }
    }

    public void openArticleFromReadingList(String article_title) {
        String article_xpath = getArticleLocatorByName(article_title);
        this.waitForElementAndClick(
                article_xpath,
                "Can`t find and click article by title " + article_title,
                15);
    }

    public boolean checkArticleIsExistInSavedList(String article_title) {
        String article_xpath = getArticleLocatorByName(article_title);
        return this.assertElementPresent(
                article_xpath,
                "Can`t find article with title "+ article_title + " in saved list");
    }

    public boolean checkArticleMarkUnwatched(String article_title) {
        String watchlist_mark = this.getArticleWatchlistMark(article_title);
        System.out.println(watchlist_mark);
        return watchlist_mark.equals(ARTICLE_UNWATCHED_MARK);
    }


    private String getArticleWatchlistMark(String article_title) {
        String unwatch_link_locator = getArticleUnwatchLinkLocatorByName(article_title);
        WebElement element = this.waitForElementPresent(
                unwatch_link_locator,
                "",
                15);
        return element.getAttribute("title");
    }

}
