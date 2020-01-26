package Xokyopo.hw_6.chat_server.Client.Gui;

import Xokyopo.hw_6.chat_server.Template.InputMessage;

public abstract class AbstractController implements InputMessage {
    private ClientGui clientGui;

    public ClientGui getClientGui() {
        return clientGui;
    }

    public void setClientGui(ClientGui clientGui) {
        this.clientGui = clientGui;
    }
}
