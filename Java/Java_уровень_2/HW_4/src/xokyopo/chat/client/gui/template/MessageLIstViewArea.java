package xokyopo.chat.client.gui.template;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import xokyopo.chat.client.gui.MessageList;


public class MessageLIstViewArea extends ScrollPane {
    private final VBox pane = new VBox();
    private MessageList parent;

    public MessageLIstViewArea(MessageList _parent) {
        this.parent = _parent;
        this.setFitToWidth(true);
        this.setContent(this.pane);
    }

    public void addMessage(MessageObject _message) {
        //TODO принмаем на вход сообщение и вытаскиваем из него данные
        switch (_message.getType()){
            case MY: this.addMyMessage(_message.getString());
            break;
            case TALKER: this.addTalkerMessage(_message.getString());
            break;
            default: this.addSystemMessage(_message.getString());
        }
    }

    //TODO пока что каждое сообщение буду делать отдельно
    private void addMyMessage(String s) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BASELINE_RIGHT);
        Label msg = new Label(s);
        msg.setId("addMyMessage");
        hBox.getChildren().add(msg);
        this.pane.getChildren().add(hBox);
    }

    private void addSystemMessage(String s) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        Label msg = new Label(s);
        msg.setId("addSystemMessage");
        hBox.getChildren().add(msg);
        this.pane.getChildren().add(hBox);
    }

    private void addTalkerMessage(String s) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BASELINE_LEFT);
        Label msg = new Label(s);
        msg.setId("addTalkerMessage");
        hBox.getChildren().add(msg);
        this.pane.getChildren().add(hBox);
    }

    private void addObject() {
        //TODO подумает что и как с ним сделать
    }
}
