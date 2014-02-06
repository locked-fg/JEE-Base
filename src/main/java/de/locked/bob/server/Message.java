package de.locked.bob.server;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@XmlRootElement
@Entity(value = "tweets", noClassnameStored = true)
public class Message {

    @Id
    private Long id;
    private Date date;
    private String message;
    private String user;

    public Message() {
    }

    public Message(Date date, String message, long id, String user) {
        this.date = date;
        this.message = message;
        this.id = id;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
