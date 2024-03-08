package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_LoginPage;
import Pages.P02_LandingPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getPropertyValue;
import static Utilities.Utility.*;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC02_LandingTest {
    private final String USERNAME = DataUtils.getJsonData("validLogin", "username");
    private final String PASSWORD = DataUtils.getJsonData("validLogin", "password");
    private Set<Cookie> cookies;

    @BeforeClass(alwaysRun = true)
    public void login() throws IOException {
        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : getPropertyValue("environment", "Browser");
        LogsUtils.info(System.getProperty("browser"));
        setupDriver(browser);
        LogsUtils.info("Edge driver is opened");
        getDriver().get(getPropertyValue("environment", "BASE_URL"));
        LogsUtils.info("Page is redirected to the URL");
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
        new P01_LoginPage(getDriver())
                .enterUsername(USERNAME)
                .enterPassword(PASSWORD)
                .clickOnLoginButton();
        cookies = getAllCookies(getDriver());
        quitDriver();
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() throws IOException {
        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : getPropertyValue("environment", "Browser");
        LogsUtils.info(System.getProperty("browser"));
        setupDriver(browser);
        LogsUtils.info("Edge driver is opened");
        getDriver().get(getPropertyValue("environment", "BASE_URL"));
        LogsUtils.info("Page is redirected to the URL");
        restoreSession(getDriver(), cookies);
        getDriver().get(getPropertyValue("environment", "HOME_URL"));
        getDriver().navigate().refresh();
    }

    @Test
    public void checkingNumberOfSelectedProductsTC() {
        new P02_LandingPage(getDriver()).addAllProductsToCart();
        Assert.assertTrue(new P02_LandingPage(getDriver()).comparingNumberOfSelectedProductsWithCart());
    }

    @Test
    public void addingRandomProductsToCartTC() {
        new P02_LandingPage(getDriver()).addRandomProducts(3, 6);
        Assert.assertTrue(new P02_LandingPage(getDriver()).comparingNumberOfSelectedProductsWithCart());
    }

    @Test
    public void clickOnCartIcon() throws IOException {
        new P02_LandingPage(getDriver()).clickOnCartIcon();
        Assert.assertTrue(VerifyURL(getDriver(), DataUtils.getPropertyValue("environment", "CART_URL")));
    }

    @AfterMethod(alwaysRun = true)
    public void quit() {
        quitDriver();
    }


    @AfterClass(alwaysRun = true)
    public void deleteSession() {
        cookies.clear();
    }
}

