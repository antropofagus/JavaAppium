package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
    static {
        MAIN_MENU_BUTTON = "css:a#mw-mf-main-menu-button";
        MENU_LOGIN_LINK = "css:#p-personal .mw-ui-icon-minerva-login";
        MENU_LOGOUT_LINK = "css:#p-personal .mw-ui-icon-minerva-logout";
        MAIN_MENU = "css:nav .menu";
        READING_LISTS_BUTTON = "css:ul#p-personal>li>a.mw-ui-icon-minerva-watchlist";
    }
    public MWNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
