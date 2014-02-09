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

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import static com.sun.jersey.api.core.PackagesResourceConfig.PROPERTY_PACKAGES;
import com.sun.jersey.api.json.JSONConfiguration;
import java.util.Date;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

public class MyClient {

    public static void main(String[] args) {
        MyPojo out = new MyPojo("Going to the server", 1L, new Date());

        ClientConfig dcc = new DefaultClientConfig();
        dcc.getProperties().put(PROPERTY_PACKAGES, "de.locked.jeebase;org.codehaus.jackson.jaxrs");
        dcc.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, true);
        dcc.getClasses().add(JacksonJsonProvider.class);

        Client c = com.sun.jersey.api.client.Client.create(dcc);
//        WebResource wr = c.resource("http://localhost/api/endpoint");
//        wr.type(MediaType.APPLICATION_JSON).post(MyPojo.class, out);
//        MyPojo in = wr.post(MyPojo.class, out);
        WebResource wr = c.resource("http://localhost/api/endpointGet");
        ClientResponse clientResponse = wr.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        MyPojo in = clientResponse.getEntity(new GenericType<MyPojo>() {});

//        System.out.println(">>> " + out.toString());
        System.out.println("<<< " + in.toString());

    }

}
