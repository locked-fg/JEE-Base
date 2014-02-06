package de.locked.bob.server.cfg;

import com.sun.jersey.api.core.PackagesResourceConfig;
import static com.sun.jersey.api.core.ResourceConfig.FEATURE_CANONICALIZE_URI_PATH;
import static com.sun.jersey.api.core.ResourceConfig.FEATURE_REDIRECT;
import com.sun.jersey.api.json.JSONConfiguration;


public class MyResourceConfig extends PackagesResourceConfig {

    public MyResourceConfig() {
        getFeatures().put(FEATURE_CANONICALIZE_URI_PATH, Boolean.TRUE);
        getFeatures().put(FEATURE_REDIRECT, Boolean.TRUE);

        // possibily nee Jackson dependencies to work!!!
        getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, true);
    }

}
