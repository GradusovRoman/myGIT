package Xokyopo.hw_6.chat_server.Server.Domain.Entity;

public class Message {
    private final String type;
    private final String msg;

    public Message(Message msg) {
        this.type = msg.getType();
        this.msg = msg.getMsg();
    }

    public Message(String type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }

}
