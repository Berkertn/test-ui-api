package utils;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class AssertionUtil {

    public static void assertEqualText(String expected, String actual) {
        if (actual == null) {
            actual = "null value";
        }
        Assert.assertEquals(String.format("\nEqual Assertion Error;\nExpected: %s\nActual: %s\n------", expected, actual), actual.toLowerCase(), expected.toLowerCase());
    }

    public static void assertTrueIsDisplayed(WebElement element) {
        Assert.assertTrue("\nDisplayed Assertion Error For Element; " + element + "\n------", element.isDisplayed());
    }
}
