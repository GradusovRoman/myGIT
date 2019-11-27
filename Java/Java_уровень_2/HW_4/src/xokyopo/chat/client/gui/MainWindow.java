package xokyopo.chat.client.gui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import xokyopo.chat.client.Client;
import xokyopo.chat.client.gui.template.InitializeInterface;
import xokyopo.chat.client.gui.template.UpdateFrame;

/**
 *Описываем общие правила для нашего интерфейса
 *
 */
public class MainWindow implements UpdateFrame, InitializeInterface {
    private UpdateFrame Child;
    private Client Parent;
    private Boolean messageListDisable = true;

    public MainWindow(Client _parent) {
        this.Parent = _parent;
        this.setMailListEnable();
    }

    public MainWindow() {
        //TODO для проверок потом уберу
        //this.Parent = _parent;
        this.setMailListEnable();
    }

    @Override
    public void initialize() {
    }

    @Override
    public void updateFrame() {
        //TODO пробрасываем обновление для вложенных объектов
        this.Child.updateFrame();
    }

    protected void setMailListEnable() {          //переключаем в режим отображения списка чатов
        //TODO требуется передача данных
        this.messageListDisable = true;
        this.Child = new MailList(this);
        this.updateFrame();
    }

    protected void setMessageListEnable(int _idMailto) {       //переключаем в режим чата c конкретным пользователем
        //TODO требуется передача данных
        this.messageListDisable = false;
        this.Child = new MessageList(this, _idMailto);
        this.updateFrame();
    }

}
