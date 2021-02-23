package ru.ozon.utils;

import org.aeonbits.owner.Config;

import java.net.URL;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:web.${environment}.properties"
})
public interface WebConfig extends Config {

    @Key("selenide.browser")
    @DefaultValue("chrome")
    String browserName();

    @Key("web.isRemote")
    @DefaultValue("false")
    boolean isRemote();

    @Key("web.remote.url")
    URL remoteBrowserUrl();
}
