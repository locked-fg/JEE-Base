package de.locked.jeebase;

import java.util.Date;
import java.util.logging.Logger;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class MyService {

    private static final Logger log = Logger.getLogger(MyService.class.getName());

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("endpoint")
    public MyPojo post(MyPojo in) {
        log.info("Object via Rest -> " + in.toString());
        long ts = System.currentTimeMillis() + 45 * 86_400;
        return new MyPojo("Answer from Server", 1_000L, new Date(ts));
    }

}
