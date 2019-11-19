package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.ReadingListsPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class ReadingListTests extends CoreTestCase {
    private static final String folder_name = "Learning programming";

    @Test
    public void testSaveTwoArticlesToMyList() {
        /*костыль для mobile_web - reading lists доступны только авторизованным пользователям
        т.о. если PLATFORM=mobile_web, логинимся под существующим аккаунтом
         */
        String search_string = "Java";
        if(Platform.getInstance().isMW()) {
            this.loginToWikiMW();
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchString(search_string);
        SearchPageObject.waitForSearchResult(search_string);
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForArticleTitle();
        String first_article_title = ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToNewReadingList(folder_name);
        } else if(Platform.getInstance().isIOS()){
            ArticlePageObject.addArticlesToMySaved();
        } else {
            ArticlePageObject.addArticleToWatchedList();
        }
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchString(search_string);
        SearchPageObject.waitForSearchResult(search_string);
        SearchPageObject.clickByArticleWithSubstring("JavaScript");

        ArticlePageObject.waitForArticleTitle();
        String second_article_title = ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToExistingReadingList(folder_name);
        } else if(Platform.getInstance().isIOS()){
            ArticlePageObject.addArticlesToMySaved();
        } else {
            ArticlePageObject.addArticleToWatchedList();
        }

        ArticlePageObject.closeArticle();

        NavigationUI Navigation = NavigationUIFactory.get(driver);
        Navigation.openReadingLists();

        ReadingListsPageObject MyReadingList = ReadingListsPageObjectFactory.get(driver);
        if(Platform.getInstance().isAndroid()) {
            MyReadingList.openFolderByName(folder_name);}

        MyReadingList.waitForArticleToAppearByTitle(first_article_title);
        MyReadingList.waitForArticleToAppearByTitle(second_article_title);
        Assert.assertEquals(
                "not expected amount of saved articles",
                2,
                MyReadingList.getAmountOfSavedArticles()
        );
        MyReadingList.removeArticleFromReadingList(first_article_title);

        if(Platform.getInstance().isAndroid()) {
            MyReadingList.openArticleFromReadingList(second_article_title);
            ArticlePageObject.waitForArticleTitle();
            Assert.assertEquals("Article titles do not match",
                    second_article_title,
                    ArticlePageObject.getArticleTitle());
        } else if(Platform.getInstance().isIOS()){
            Assert.assertTrue(
                    "Can`t find article with title " + second_article_title + " in saved list",
                    MyReadingList.checkArticleIsExistInSavedList(second_article_title));
        } else {
            Assert.assertTrue(
                    "Article with title " + first_article_title + " still in watchlist",
                    MyReadingList.checkArticleMarkUnwatched(first_article_title));
            Assert.assertTrue(
                    "Can't find article with title " + second_article_title + " in watchlist",
                    MyReadingList.checkArticleIsExistInSavedList(second_article_title));
        }

    }

}
