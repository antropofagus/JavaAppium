package lib.ui.factories;

import lib.Platform;
import lib.ui.android.AndroidReadingListsPageObject;
import lib.ui.ios.IOSReadingListsPageObject;
import lib.ui.ReadingListsPageObject;
import lib.ui.mobile_web.MWReadingListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ReadingListsPageObjectFactory {
    public static ReadingListsPageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidReadingListsPageObject(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new IOSReadingListsPageObject(driver);
        } else {
            return new MWReadingListPageObject(driver);
        }
    }
}
