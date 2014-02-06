package de.locked.bob.share;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReturnMessage {

    int code = 0;
    String message;

    /**
     * success!
     */
    public ReturnMessage() {
    }

    public ReturnMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
