package client_server_app;

import java.io.Serializable;

public class SerialObject implements Serializable {
    private String text;

    public SerialObject(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
