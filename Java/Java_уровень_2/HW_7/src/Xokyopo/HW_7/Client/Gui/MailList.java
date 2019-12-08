package Xokyopo.HW_7.Client.Gui;

import Xokyopo.HW_7.Client.Gui.template.InitializeInterface;
import Xokyopo.HW_7.Client.Gui.template.UpdateFrame;

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
