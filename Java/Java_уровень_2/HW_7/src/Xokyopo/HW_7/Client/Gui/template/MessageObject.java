package Xokyopo.HW_7.Client.Gui.template;


public class MessageObject {
    private String s;
    private MessageType type;

    public MessageObject(String _s, MessageType _type) {
        this.s = _s;
        this.type = _type;
    }

    public String getString() {
        return this.s;
    }

    public MessageType getType() {
        return this.type;
    }
}
