package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.Android.AndroidReadingListsPageObject;
import lib.ui.IOS.IOSReadingListsPageObject;
import lib.ui.ReadingListsPageObject;

public class ReadingListsPageObjectFactory {
    public static ReadingListsPageObject get(AppiumDriver driver) {
        if(Platform.getInstance().isAndroid()) {
            return new AndroidReadingListsPageObject(driver);
        } else {
            return new IOSReadingListsPageObject(driver);
        }
    }
}
