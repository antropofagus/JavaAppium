package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class MWLoginPageObject extends MainPageObject {
    private static final String
        LOGIN_INPUT = "css:div.loginText div.mw-input > input#wpName1",
        PASSWORD_INPUT = "css:div.loginPassword div.mw-input > input#wpPassword1",
        SUBMIT_LOGOUT = "xpath://button//span[contains(text(), 'Submit')]",
        LOGOUT_PAGE_TITLE = "xpath://h1[contains(text(), 'Log out')]",
        LOGIN_BUTTON = "css:button#wpLoginAttempt";


    public MWLoginPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void loginToWiki(String login, String password) {
        this.waitForElementAndSendKeys(
                LOGIN_INPUT,
                "can`t find login input",
                login,
                10);
        this.waitForElementAndSendKeys(
                PASSWORD_INPUT,
                "can`t find password input",
                password,
                10);
        this.waitForElementAndClick(
                LOGIN_BUTTON,
                "can`t find login button",
                10);
    }

    public void logoutFromWiki() {
        this.waitForElementPresent(
                LOGOUT_PAGE_TITLE,
                "can`t find logout page title",
                15);
        this.waitForElementAndClick(
                SUBMIT_LOGOUT,
                "can`t find and click submit logout button",
                10);
    }
}
