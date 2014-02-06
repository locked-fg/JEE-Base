package de.locked.bob.server;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TweetLoader {

    private static final Logger log = Logger.getLogger(TweetLoader.class.getName());
    private static final String RO_OAUTH_CONSUMER_SECRET = "e6E15wts2Qa97cTdASVP941zbrU2ddvKELUxkhji8w";
    private static final String RO_OAUTH_CONSUMER_STRING = "fctCyuxlbhi8HpAgruMxA";
    private static final String RO_OAUTH_TOKEN = "342583919-LuRRgL0FeXX6e4hORHpxTgGF7CIN1CMPOaItFiKT";
    private static final String RO_OAUTH_TOKEN_SECRET = "xaep8mQWY0tv1z36DoAbpQktHHFqFTq98AHJxUH1g";
    private static final String BOB_USER_NAME = "BOB_Info";

    public static void main(String... args) throws UnknownHostException {
        List<Message> data = new TweetLoader().fetch();
        new MessageDAO().save(data);
    }

    // String url = "http://twitter.com/" + user + "/status/" + id;
    public List<Message> fetch() {
        log.info("start fetching");
        List<Status> statuses = null;
        try {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true).setOAuthConsumerKey(RO_OAUTH_CONSUMER_STRING)
                    .setOAuthConsumerSecret(RO_OAUTH_CONSUMER_SECRET)
                    .setOAuthAccessToken(RO_OAUTH_TOKEN)
                    .setOAuthAccessTokenSecret(RO_OAUTH_TOKEN_SECRET);
            Twitter twitter = new TwitterFactory(cb.build()).getInstance();
            statuses = twitter.getUserTimeline(BOB_USER_NAME);
        } catch (TwitterException e) {
            log.log(Level.WARNING, "twitter caused an exception", e);
        }

        if (statuses == null) {
            statuses = Collections.EMPTY_LIST;
        }

        List<Message> messages = new ArrayList<>(statuses.size());
        for (Status status : statuses) {
            String user = status.getUser().getScreenName();
            long id = status.getId();
            Message message = new Message(status.getCreatedAt(), status.getText(), id, user);
            messages.add(message);
        }

        log.log(Level.INFO, "fetched {0} messages", statuses.size());
        return messages;
    }
}
