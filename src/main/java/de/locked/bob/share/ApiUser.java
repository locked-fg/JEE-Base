package de.locked.bob.share;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ApiUser {

    private String login;
    private String password;

    public ApiUser() {
    }

    public ApiUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
