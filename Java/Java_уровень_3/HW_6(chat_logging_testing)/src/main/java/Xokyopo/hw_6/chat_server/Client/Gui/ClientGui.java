package Xokyopo.hw_6.chat_server.Client.Gui;

import Xokyopo.hw_6.chat_server.Client.Client;
import Xokyopo.hw_6.chat_server.Template.InputMessage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClientGui {
    private Stage chatGui = null;
    private Stage loginGui = null;
    private String chatGuiPathToFXML = "/Chat.fxml";
    private String loginGuiPathToFXML = "/LoginAndPassword.fxml";
    private LoginAndPasswordController loginAndPasswordController = new LoginAndPasswordController();
    private ChatController chatController = new ChatController();
    private InputMessage currentController;
    private Client client;

    public ClientGui(Client client) {
        this.client = client;
    }

    private Stage getChatGui() {
        //TODO Инициализация в случая ее отсутсвия
        if (this.chatGui == null) {
            this.chatGui = this.creatingWindows(this.chatGuiPathToFXML, this.chatController, false);
        }
        return this.chatGui;
    }

    private Stage getLoginGui() {
        //TODO Инициализация в случая ее отсутсвия
        if (this.loginGui == null) {
            this.loginGui = this.creatingWindows(this.loginGuiPathToFXML, this.loginAndPasswordController, true);
        }
        return this.loginGui;
    }

    private Stage creatingWindows(String _FXMLPath, AbstractController _controllers, boolean _dialog) {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(_FXMLPath));
        _controllers.setClientGui(this);
        fxmlLoader.setController(_controllers);
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (_dialog) {
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
        }
        return stage;
    }

    public void setLoginGuiEnable() {
        if (!this.getLoginGui().isShowing()) {
            this.getChatGui().hide();
            this.getLoginGui().show();
        }
        this.currentController = this.loginAndPasswordController;
        this.loginAndPasswordController.enableEdit();
    }

    public void setChatGuiEnable() {
        this.getChatGui().show();
        this.getLoginGui().hide();
        this.currentController = this.chatController;
    }

    public InputMessage getCurrentController() {
        return this.currentController;
    }

    public Client getClient() {
        return client;
    }

    public void setTitleText(String _text) {
        this.getChatGui().setTitle(_text);
    }
}
