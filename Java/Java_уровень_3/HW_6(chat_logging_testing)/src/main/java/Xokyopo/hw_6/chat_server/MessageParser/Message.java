package Xokyopo.hw_6.chat_server.MessageParser;

public class Message  extends MessageParser{
    private String inputString;

    public Message(String inputString) {
        this.inputString = inputString;
    }

    public boolean isContainsTag(Tag _tag) {
        return this.isContainsTag(this.inputString, _tag);
    }

    public String getValueByTag(Tag _tag) {
        return this.getValueInStringByTag(this.inputString, _tag);
    }

    public String getString() {
        return this.inputString;
    }


}
