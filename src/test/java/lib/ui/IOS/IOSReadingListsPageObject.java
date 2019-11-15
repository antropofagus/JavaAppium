package lib.ui.IOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.ReadingListsPageObject;

public class IOSReadingListsPageObject extends ReadingListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{TITLE}')]";
    }
    public IOSReadingListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
