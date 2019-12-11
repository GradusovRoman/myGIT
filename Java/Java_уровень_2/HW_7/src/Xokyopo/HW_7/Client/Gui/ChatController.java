package Xokyopo.HW_7.Client.Gui;


import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatController extends AbstractController{
    @FXML TextField textField;
    @FXML TextArea textArea;


    public void sendMessage() {
        this.textArea.appendText(this.textField.getText() + "\n");
    }

    @Override
    public void inputMessage(String _msg) {

    }
}
