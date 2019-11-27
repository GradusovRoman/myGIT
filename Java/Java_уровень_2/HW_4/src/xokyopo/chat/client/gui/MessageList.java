package xokyopo.chat.client.gui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import xokyopo.chat.client.gui.template.*;
import xokyopo.chat.client.template.Message;


public class MessageList extends BorderPane implements UpdateFrame, InitializeInterface {
    private MainWindow parent;
    private int idMailto;
    private int lastUpdate;
    private String styleSheet = "/style.css";
    private MessageLIstViewArea messageLIstViewArea;
    private TextField text;


    public MessageList(MainWindow _parent, int _idMailto) {
        this.parent = _parent;
        this.idMailto = _idMailto;
    }


    public MessageList() {
        //TODO для проверки
        this.initialize();
    }

    @Override
    public void initialize() {
        this.getStylesheets().add(this.styleSheet);
        this.messageLIstViewArea = new MessageLIstViewArea(this);
        this.creatingGUI();

        //TODO тестовый блок
        this.messageLIstViewArea.addMessage(new MessageObject("ЯЯЯЯ", MessageType.MY));
        this.messageLIstViewArea.addMessage(new MessageObject("Оппонент", MessageType.TALKER));
        this.messageLIstViewArea.addMessage(new MessageObject("Система", MessageType.SYSTEM));
        this.messageLIstViewArea.addMessage(new MessageObject("ЯЯЯЯ", MessageType.MY));
        this.messageLIstViewArea.addMessage(new MessageObject("Оппонент", MessageType.TALKER));
        this.messageLIstViewArea.addMessage(new MessageObject("Система", MessageType.SYSTEM));
        this.messageLIstViewArea.addMessage(new MessageObject("ЯЯЯЯfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", MessageType.MY));
        this.messageLIstViewArea.addMessage(new MessageObject("Оппонентfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", MessageType.TALKER));
        this.messageLIstViewArea.addMessage(new MessageObject("Системаfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", MessageType.SYSTEM));
    }

    @Override
    public void updateFrame() {

    }

    private void viewMailList() {
        this.parent.setMailListEnable();
    }

    private void addMessage(MessageObject... messages) {
        //TODO получаем на вход список объектов и добавляем их в внаш чат
    }

    private void sendMessage(MessageObject messages) {
        //TODO отправлем наши сообщения на сервер.
    }

    private void creatingGUI() {
        this.setCenter(this.messageLIstViewArea);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BASELINE_RIGHT);
        text = new TextField();
        text.setPrefWidth(Integer.MAX_VALUE);
        Button button = new Button("Отправить");
        button.setId("buttom-send");
        button.getStylesheets().add(this.styleSheet);
        button.setMinWidth(80);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                messageLIstViewArea.addMessage(getMessageObject());
            }
        });
        hBox.getChildren().addAll(text, button);
        this.setBottom(hBox);
        //TODO место для подготовки и отправки сообщения (поле и кнопка(две кнопки))
    }

    private MessageObject getMessageObject() {
        MessageObject msg = new MessageObject(this.text.getText(), MessageType.MY);
        this.text.clear();
        return msg;
    }
}
