package de.locked.bob.server;

import de.locked.bob.share.ApiUser;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlRootElement;
import de.locked.bob.HexUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@XmlRootElement
@Entity(value = "users", noClassnameStored = true)
public class User {

    private transient static final Logger log = Logger.getLogger(User.class.getName());

    @Id
    private ObjectId id;
    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User crypt() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(password.getBytes("UTF-8"));
            String newpassword = HexUtils.convert(digest);
            return new User(username, newpassword);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            log.log(Level.SEVERE, null, ex);
            throw new IllegalStateException(ex);
        }
    }
}
