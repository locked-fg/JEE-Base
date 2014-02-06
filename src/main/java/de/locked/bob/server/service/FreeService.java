package de.locked.bob.server.service;

import com.sun.jersey.spi.container.ParamQualifier;
import de.locked.bob.server.Message;
import de.locked.bob.server.MessageDAO;
import de.locked.bob.server.TweetLoader;
import de.locked.bob.server.User;
import de.locked.bob.server.UserDAO;
import de.locked.bob.share.ApiUser;
import de.locked.bob.share.ReturnMessage;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/")
public class FreeService {

    private static final Logger log = Logger.getLogger(FreeService.class.getName());

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("register")
    public ReturnMessage register(ApiUser au) {
        try {
            // check login/password
            String login = au.getLogin();
            String password = au.getPassword();
            if (login == null) {
                return new ReturnMessage(1, "Login must be set");
            }
            login = login.trim();
            if (login.length() < 5) {
                return new ReturnMessage(2, "Login must be at least 5 characters long");
            }
            if (password == null || password.length() < 3) {
                return new ReturnMessage(3, "Password must be at least 3 characters long");
            }

            // done checking
            User user = new User(login, password).crypt();
            UserDAO dao = new UserDAO();
            if (dao.loginExists(user)) {
                return new ReturnMessage(4, "Login exists");
            } else {
                dao.save(user);
                return new ReturnMessage();
            }
        } catch (UnknownHostException ex) {
            log.log(Level.SEVERE, null, ex);
            return new ReturnMessage(5, "DB exception");
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listTweets")
    public Message[] getAll() {
        try {
            List<Message> m = new MessageDAO().getRecent(100);
            return m.toArray(new Message[]{});
        } catch (UnknownHostException ex) {
            log.log(Level.SEVERE, null, ex);
            return new Message[0];
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("importData")
    public String importData() {
        try {
            long a = System.currentTimeMillis();
            List<Message> data = new TweetLoader().fetch();
            int count = new MessageDAO().save(data);
            long b = System.currentTimeMillis();
            return "loaded " + data.size() + " tweets. Saved " + count + ". Time " + (b - a) + "ms";
        } catch (UnknownHostException ex) {
            log.log(Level.SEVERE, null, ex);
            return "error";
        }
    }
}
