package de.locked.bob.server;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import static de.locked.bob.server.MongoDAO.LOGIN.*;
import static de.locked.bob.server.MongoDAO.MongoSingle.MongoInstance;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mongodb.morphia.AdvancedDatastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

public class MongoDAO {

    private static final Logger log = Logger.getLogger(MongoDAO.class.getName());
    protected static MongoClient mongo;

    public static final String DB_BASE = "wirpendeln";
    public static final String COLLECTION_USER = "users";
    public static final String COLLECTION_TWEETS = "tweets";

    static enum LOGIN {

        HOST("OPENSHIFT_MONGODB_DB_HOST", "localhost"),
        USER("OPENSHIFT_MONGODB_DB_USERNAME", ""),
        PASS("OPENSHIFT_MONGODB_DB_PASSWORD", "");
        final String value;

        LOGIN(String cfg, String def) {
            String v = System.getenv(cfg);
            value = (v == null) ? def : v;
        }
    }

    static enum MongoSingle {

        MongoInstance;

        final MongoClient value;

        private MongoSingle() {
            try {
                value = new MongoClient(HOST.value);
            } catch (UnknownHostException ex) {
                log.log(Level.SEVERE, ex.getMessage(), ex);
                throw new IllegalStateException(ex);
            }
        }
    }

    public MongoDAO() throws UnknownHostException {
        mongo = MongoInstance.value;
        ensureIndices();
    }

    private void ensureIndices() {
        DB db = mongo.getDB(DB_BASE);
        db.getCollection(COLLECTION_USER).ensureIndex(new BasicDBObject("user", 1), "user", true);
        db.getCollection(COLLECTION_TWEETS).ensureIndex(new BasicDBObject("user", 1).append("date", 1), "user_date", true);
    }

    protected AdvancedDatastore getDatastore() {
        Morphia morphia = new Morphia();
        morphia.map(Message.class);
        AdvancedDatastore ds = (AdvancedDatastore) morphia.createDatastore(mongo, DB_BASE);
        return ds;
    }

    public MongoClient getMongo() {
        return mongo;
    }

    public <T> BasicDAO<T, ?> getDao(Class<T> clazz) {
        BasicDAO<T, ?> b = new BasicDAO(clazz, getDatastore());
        return b;
    }
}
