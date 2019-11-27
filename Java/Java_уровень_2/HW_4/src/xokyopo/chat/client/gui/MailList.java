package xokyopo.chat.client.gui;

import xokyopo.chat.client.gui.template.InitializeInterface;
import xokyopo.chat.client.gui.template.UpdateFrame;

public class MailList implements UpdateFrame, InitializeInterface {
    private MainWindow parent;
    private int LastUpdate;

    public MailList(MainWindow _parent) {
        this.parent = _parent;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void updateFrame() {

    }

    private void viewMessageList(int _idMailto) {
        this.parent.setMessageListEnable(_idMailto);
    }
}
