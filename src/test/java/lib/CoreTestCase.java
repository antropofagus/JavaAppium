package lib;
import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.MWLoginPageObject;
import lib.ui.NavigationUI;
import lib.ui.WelcomePageObject;
import lib.ui.factories.NavigationUIFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public class CoreTestCase extends TestCase {
    protected RemoteWebDriver driver;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
        this.openWikiWebPageForMW();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    public void loginToWikiMW() {
        String LOGIN = System.getProperty("WIKILOGIN");
        String PASSWORD = System.getProperty("WIKIPASSWORD");
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.openLoginPageFromMainMenu();

        MWLoginPageObject mwLoginPage = new MWLoginPageObject(driver);
        mwLoginPage.loginToWiki(LOGIN, PASSWORD);
    }

    public void logoutFromWiki() {
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.openLogoutFromMainMenu();

        MWLoginPageObject mwLoginPage = new MWLoginPageObject(driver);
        mwLoginPage.logoutFromWiki();
    }

    protected void rotateScreenPortrait() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreePortrait() does nothing for platforms: " + Platform.getInstance().getPlatformVar());
        }
    }


    protected void openWikiWebPageForMW() {
        if (Platform.getInstance().isMW()) {
            driver.get("https://en.m.wikipedia.org/wiki/Main_Page");
        } else {
            System.out.println("Method rotateScreePortrait() does nothing for platforms: " + Platform.getInstance().getPlatformVar());
        }
    }

    private void skipWelcomePageForIOSApp() {
        if(Platform.getInstance().isIOS()) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }
}
