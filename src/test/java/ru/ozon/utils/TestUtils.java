package ru.ozon.utils;

public class TestUtils {

    public static String getImageNameFromSrc(String imageSrc) {
        String[] srcParts = imageSrc.split("/");
        return srcParts[srcParts.length - 1];
    }
}
