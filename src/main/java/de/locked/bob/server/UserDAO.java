package de.locked.bob.server;

import java.net.UnknownHostException;
import java.util.logging.Logger;
import org.mongodb.morphia.AdvancedDatastore;
import org.mongodb.morphia.dao.BasicDAO;

public class UserDAO extends MongoDAO {

    private static final Logger log = Logger.getLogger(UserDAO.class.getName());
    private BasicDAO dao = new BasicDAO(User.class, getDatastore());

    public UserDAO() throws UnknownHostException {
        super();
    }

    public boolean loginExists(User u) {
        return dao.exists("username", u.getUsername());
    }

    public void save(User u) {
        dao.save(u);
    }
}
