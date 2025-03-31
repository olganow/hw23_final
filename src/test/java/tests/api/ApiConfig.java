package tests.api;

import org.aeonbits.owner.Config;

import static constants.Constants.BASE_URL;

public interface ApiConfig extends Config {
    @Config.DefaultValue(BASE_URL)
    String baseURI();

    @Config.DefaultValue("/api")
    String basePath();

}