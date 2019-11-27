package xokyopo.chat;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import xokyopo.chat.client.gui.MessageList;
import xokyopo.chat.client.gui.template.MessageLIstViewArea;
import xokyopo.chat.client.gui.template.MessageObject;
import xokyopo.chat.client.gui.template.MessageType;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        MessageList root = new MessageList();
        root.setVisible(true);
        primaryStage.setScene(new Scene(root,300,275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
