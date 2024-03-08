package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utilities.Utility.findWebElement;

public class P06_FinishingOrderPage {

    private final WebDriver driver;
    private final By thanksMessage = By.tagName("h2");

    public P06_FinishingOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean checkVisibilityOfThanksMessage() {
        return findWebElement(driver, thanksMessage).isDisplayed();
    }

}
