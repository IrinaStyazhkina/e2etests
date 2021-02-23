package ru.ozon.utils;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestUtils {

    public static String getImageNameFromSrc(String imageSrc) {
        String[] srcParts = imageSrc.split("/");
        return srcParts[srcParts.length - 1];
    }

    public static Map<String, String> prepareCookies() {
        Set<Cookie> cookies = WebDriverRunner.getWebDriver().manage().getCookies();
        Map<String, String> parsedCookies = new HashMap<>();
        for (Cookie cookie : cookies) {
            parsedCookies.put(cookie.getName(), cookie.getValue());
        }
        return parsedCookies;
    }
}
