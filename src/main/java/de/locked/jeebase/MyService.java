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

import java.util.Date;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class MyService {

    private static final Logger log = Logger.getLogger(MyService.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("endpointGet")
    public MyPojo get() {
        long ts = System.currentTimeMillis() + 45 * 86_400;
        return new MyPojo("Answer from Server", 1_000L, new Date(ts));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("endpoint")
    public MyPojo post(MyPojo in) {
        log.info("Object via Rest -> " + in.toString());
        long ts = System.currentTimeMillis() + 45 * 86_400;
        return new MyPojo("Answer from Server", 1_000L, new Date(ts));
    }

}
