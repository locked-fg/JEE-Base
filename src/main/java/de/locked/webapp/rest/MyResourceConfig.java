/*
 * Copyright 2014 Dr. Franz Graf <code@Locked.de>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.locked.webapp.rest;

import com.sun.jersey.api.core.PackagesResourceConfig;
import static com.sun.jersey.api.core.ResourceConfig.FEATURE_CANONICALIZE_URI_PATH;
import static com.sun.jersey.api.core.ResourceConfig.FEATURE_REDIRECT;
import com.sun.jersey.api.json.JSONConfiguration;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.ApplicationPath;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

@WebServlet(loadOnStartup = 1)
@ApplicationPath("api")
public class MyResourceConfig extends PackagesResourceConfig {

    public MyResourceConfig() {
        // super("de.locked.jeebase");
        super(MyResourceConfig.class.getPackage().getName());
        getFeatures().put(FEATURE_CANONICALIZE_URI_PATH, Boolean.TRUE);
        getFeatures().put(FEATURE_REDIRECT, Boolean.TRUE);

        getClasses().add(JacksonJsonProvider.class);
        getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, true);
    }

}
