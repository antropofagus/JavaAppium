package lib;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class Platform {
    public static final String
            PLATFORM_IOS = "ios",
            PLATFORM_ANDROID = "android",
            PLATFORM_MOBILE_WEB = "mobile_web",
            CHROMEDRIVERPATH = System.getProperty("user.dir") + "/chromedriver/chromedriver",
            APPIUM_URL = "http://0.0.0.0:4723/wd/hub";

    private static Platform instance;
    private Platform() {}

    public static Platform getInstance() {
        if (instance ==null) {
            instance = new Platform();
        }
        return instance;
    }


    public RemoteWebDriver getDriver() throws Exception {
        URL URL = new URL(APPIUM_URL);
        if (this.isAndroid()) {
            return new AndroidDriver(URL, this.getAndroidCapabilities());
        } else if (this.isIOS()) {
            return new IOSDriver(URL, this.getIOSCapabilities());
        } else if (this.isMW()) {
            System.setProperty("webdriver.chrome.driver", CHROMEDRIVERPATH);
            ChromeDriverService service = new ChromeDriverService.Builder()
                    .withVerbose(false)
                    .build();
            ChromeOptions opt = this.getMWChromeOptions();
            return new ChromeDriver(service,opt);
        } else {
            throw new Exception("Can`t detect type of the Driver. Platform value: " + getPlatformVar());
        }
    }

    public boolean isAndroid() {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS() {
        return isPlatform(PLATFORM_IOS);
    }

    public boolean isMW() { return isPlatform(PLATFORM_MOBILE_WEB);}


    private DesiredCapabilities getAndroidCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformVersion", "9");
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("deviceName", "PixelAPI28");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("app", System.getProperty("user.dir")+"/apks/org.wikipedia.apk");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("appPackage", "org.wikipedia");
        return capabilities;
    }

    private DesiredCapabilities getIOSCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("automationName","XCUITest");
        capabilities.setCapability("browser","iOS");
        capabilities.setCapability("platformVersion", "11.2");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone X");
        capabilities.setCapability("app", System.getProperty("user.dir")+"/apks/Wikipedia.app");
        return capabilities;
    }

    private ChromeOptions getMWChromeOptions() {
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);
        deviceMetrics.put("fitWindow", true);

        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        //mobileEmulation.put("userAgent","Mozilla/5.0 (Linux; android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 9; Mi A2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.96 Mobile Safari/537.36");

        ChromeOptions chromeOptions = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.INFO);
        chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        chromeOptions.addArguments("--window-size=360,640");
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        chromeOptions.setHeadless(true);
        System.out.println(chromeOptions);


        return chromeOptions;
    }

    public String getPlatformVar() {
        return System.getenv("PLATFORM");
    }

    private boolean isPlatform(String current_platform) {
        String platform = this.getPlatformVar();
        return current_platform.equals(platform);
    }



}
