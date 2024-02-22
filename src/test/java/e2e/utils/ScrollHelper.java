package e2e.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScrollHelper {
    public  void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public static void scrollToMiddleOfElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long windowHeight = (Long) js.executeScript("return window.innerHeight;");
        long elementY = element.getLocation().getY();
        long middleY = elementY - (windowHeight / 2);
        js.executeScript("window.scrollTo(0, arguments[0]);", middleY);
    }
}
