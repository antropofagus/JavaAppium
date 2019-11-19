package lib.ui.ios;

import lib.ui.ReadingListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSReadingListsPageObject extends ReadingListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{TITLE}')]";
    }
    public IOSReadingListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
