package ru.ozon.config;

import org.aeonbits.owner.ConfigFactory;

import java.net.URL;

public class WebService {

    private static WebConfig config = ConfigFactory.newInstance().create(WebConfig.class, System.getProperties());

    public static boolean isRemote() {
        return config.isRemote();
    }

    public static URL getRemoteUrl() {
        return config.remoteBrowserUrl();
    }

    public static boolean isVideoOn() {
        return config.isVideoOn();
    }
}
