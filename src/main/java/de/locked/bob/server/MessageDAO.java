package de.locked.bob.server;

import com.google.common.collect.Lists;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Logger;

import org.mongodb.morphia.AdvancedDatastore;
import org.mongodb.morphia.query.Query;

public class MessageDAO extends MongoDAO {

    private static final Logger log = Logger.getLogger(MessageDAO.class.getName());
    private AdvancedDatastore ds = getDatastore();

    public MessageDAO() throws UnknownHostException {
        super();
    }

    public int save(List<Message> list) {
        int saveCount = 0;
        for (Message message : list) {
            long countAll = ds.find(Message.class, "id", message.getId()).countAll();
            if (countAll == 0) {
                ds.save(message);
                saveCount++;
            }
        }
        return saveCount;
    }

    public List<Message> getRecent(int num) {
        num = Math.max(0, num);
        Query<Message> q = ds.createQuery(Message.class).order("-date").limit(num);
        return Lists.newArrayList(q.fetch());
    }
}
