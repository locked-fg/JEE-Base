package de.locked.bob.server.service;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/auth")
public class AuthService {

    private static final Logger log = Logger.getLogger(AuthService.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("foo")
    public String getFoo() {
        return "foo";
    }
}
