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
package de.locked.jeebase;

import com.sun.jersey.api.core.PackagesResourceConfig;
import static com.sun.jersey.api.core.ResourceConfig.FEATURE_CANONICALIZE_URI_PATH;
import static com.sun.jersey.api.core.ResourceConfig.FEATURE_REDIRECT;
import com.sun.jersey.api.json.JSONConfiguration;


public class MyResourceConfig extends PackagesResourceConfig {

    public MyResourceConfig() {
        getFeatures().put(FEATURE_CANONICALIZE_URI_PATH, Boolean.TRUE);
        getFeatures().put(FEATURE_REDIRECT, Boolean.TRUE);

        // possibily need Jackson dependencies to work!!!
//        getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, true);
    }

}
