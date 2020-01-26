package Xokyopo.hw_6.chat_server.Client.Gui;


import Xokyopo.hw_6.chat_server.Client.Client;
import Xokyopo.hw_6.chat_server.MessageParser.Tag;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.Map;

public class ChatController extends AbstractController{
    @FXML TextField textField;
    @FXML TextArea textArea;
    @FXML TextArea clientList;


    public void sendMessage() {
        String textLine = this.getText();
        if (textLine.contains("\\p ")) {
            String[] splitText = textLine.split(" ", 3);
            this.getClientGui().getClient().sendMessagePrivate(splitText[1], splitText[2]);
        } else if (textLine.contains("\\b ")) {
            String[] splitText = textLine.split(" ", 2);
            this.getClientGui().getClient().addClientToBlackList(splitText[1]);
        } else {
            this.getClientGui().getClient().sedMessageAll(textLine);
        }
    }

    @Override
    public void inputMessage(String _msg) {
        Map<Tag, String> message = Client.messageParser.parseStringByTags(_msg);
        if (message.get(Tag.PRIVATETAG) != null) {
            this.printMessage("private from " + message.get(Tag.NICKNAMETAG) + " : " + message.get(Tag.MESSAGETAG));
        } else if (message.get(Tag.MESSAGETAG) != null) {
            this.printMessage(message.get(Tag.NICKNAMETAG) + " : " + message.get(Tag.MESSAGETAG));
        } else if (message.get(Tag.CLIENTLISTTAG) != null) {
            setClientList(Client.messageParser.stringsArrayToArrayOfString(message.get(Tag.CLIENTLISTTAG)));
        } else if (message.get(Tag.SERVERINFOTAG) != null) {
            this.printMessage(message.get(Tag.SERVERINFOTAG));
        }
    }

    private String getText() {
        String msg = this.textField.getText();
        this.textField.clear();
        return msg;
    }

    private void setClientList(String... _clientLIst) {
        this.clientList.clear();
        for (int i = 0; i < _clientLIst.length; i++) {
            this.clientList.appendText(_clientLIst[i] + "\n");
        }
    }

    private void printMessage(String _msg) {
        this.textArea.appendText(_msg + "\n");
    }

}
