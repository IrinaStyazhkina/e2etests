package ru.ozon.utils;

import org.aeonbits.owner.Config;

import java.net.URL;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
})
public interface WebConfig extends Config {

    @Key("web.isRemote")
    @DefaultValue("false")
    boolean isRemote();

    @Key("web.remote.url")
    URL remoteBrowserUrl();

    @Key("web.enableVideo")
    @DefaultValue("false")
    boolean isVideoOn();
}
