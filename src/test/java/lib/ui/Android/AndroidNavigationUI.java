package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {
    static {
        READING_LISTS_BUTTON = "xpath://android.widget.FrameLayout[@content-desc='My lists']";
    }


    public AndroidNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
