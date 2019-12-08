package Xokyopo.HW_7.Server;

public class Message {
    private String msg;
    private String AuthorSpliter = ":";
    private String privateLabel = ":\\P ";

    public Message(String msg) {
        this.msg = msg;
    }

    public boolean isPrivate() {
        return this.msg.contains(this.privateLabel);
    }

    public String getAuthorName() {
        return this.msg.substring(0, msg.indexOf(this.AuthorSpliter));
    }

    public String getMessage() {
        if (this.isPrivate()) {
            //TODO сообщение привата
            String to = this.getPrivateTarget();
            return msg.substring(msg.indexOf(to) + to.length() + 1);
        }
        return msg.substring(msg.indexOf(this.AuthorSpliter) + 1);
    }

    public String getPrivateTarget() {
        if (this.isPrivate()) {
            return msg.substring(msg.indexOf(this.privateLabel) + this.privateLabel.length()).split(" ")[0];
        }
        return null;
    }
}
