package Xokyopo.hw_6.chat_server.Server.Domain;

import Xokyopo.hw_6.chat_server.Server.Domain.Entity.ClientInfo;
import Xokyopo.hw_6.chat_server.Server.Domain.Entity.Message;
import Xokyopo.hw_6.chat_server.Server.Domain.Features.Interface.Send;
import Xokyopo.hw_6.chat_server.Server.UIAdapters.ClientInputConnectionAdapter.ClientInputConnectionAdapter;

public class Client extends ClientInfo {
    private final ClientInputConnectionAdapter postalAddress;
    //TODO сетевой интерфейс
    private Send server;

    public Client(ClientInfo clientInfo, ClientInputConnectionAdapter postalAddress, Send server) {
        super(clientInfo);
        this.postalAddress = postalAddress;
        this.server = server;
    }

    public ClientInputConnectionAdapter getPostalAddress() {
        return postalAddress;
    }

    public void setServer(Send server) {
        this.server = server;
    }

    //переброс сообщения на сервер
    private void send(Message msg) {
        this.server.send(this, msg);
    }

    public void sendMessage(Message msg) {
        this.postalAddress.send(msg);
    }
}
