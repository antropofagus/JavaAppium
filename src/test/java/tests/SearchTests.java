package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import java.util.List;


public class SearchTests extends CoreTestCase {

        @Test
        public void testSearch() {
            String search_string = "Java";
            SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchString(search_string);
            SearchPageObject.waitForSearchResult(search_string);
        }

        @Test
        public void testSearchCancel() {
            String search_string = "Java";
            SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchString(search_string);
            SearchPageObject.waitForSearchResult(search_string);
            SearchPageObject.waitForCancelButtonToAppear();
            SearchPageObject.clickCancelSearch();
            SearchPageObject.assertSearchResultIsNotPresent();
        }

        @Test
        public void testSearchResultsAreRelevant() {
            String search_string = "Java";
            SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchString(search_string);
            SearchPageObject.waitForSearchResult(search_string);
            List<WebElement> search_results = SearchPageObject.getSearchResults();
            SearchPageObject.assertSearchResultsContainsSearchString(search_results, search_string);
        }
}
