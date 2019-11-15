package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.NavigationUI;
import lib.ui.ReadingListsPageObject;
import lib.ui.SearchPageObject;
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
        String search_string = "Java";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchString(search_string);
        SearchPageObject.waitForSearchResult(search_string);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForArticleTitle();
        String first_article_title = ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToNewReadingList(folder_name);
        } else {
            ArticlePageObject.addArticlesToMySaved();
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
        } else {
            ArticlePageObject.addArticlesToMySaved();
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
        MyReadingList.swipeByArticleToDelete(first_article_title);

        if(Platform.getInstance().isAndroid()) {
            MyReadingList.openArticleFromReadingList(second_article_title);
            ArticlePageObject.waitForArticleTitle();
            Assert.assertEquals("Article titles do not match",
                    second_article_title,
                    ArticlePageObject.getArticleTitle());
        } else {
            Assert.assertTrue(
                    "Can`t find article with title " + second_article_title + " in saved list",
                    MyReadingList.checkArticleIsExistInSavedList(second_article_title));
        }
    }

}
