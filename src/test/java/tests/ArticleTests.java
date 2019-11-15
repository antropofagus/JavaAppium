package tests;

import lib.CoreTestCase;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import lib.ui.ArticlePageObject;
import lib.ui.NavigationUI;
import lib.ui.ReadingListsPageObject;
import lib.ui.SearchPageObject;

import java.util.List;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testArticleTitleIsPresent() {
        String search_string = "Java";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchString(search_string);
        SearchPageObject.waitForSearchResult(search_string);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.assertArticleTitlePresent();
    }


}
