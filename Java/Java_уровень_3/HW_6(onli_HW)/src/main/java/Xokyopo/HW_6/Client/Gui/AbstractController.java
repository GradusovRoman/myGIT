package Xokyopo.HW_6.Client.Gui;

import Xokyopo.HW_6.Template.InputMessage;

public abstract class AbstractController implements InputMessage {
    private ClientGui clientGui;

    public ClientGui getClientGui() {
        return clientGui;
    }

    public void setClientGui(ClientGui clientGui) {
        this.clientGui = clientGui;
    }
}
