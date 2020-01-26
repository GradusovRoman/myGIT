package Xokyopo.hw_6.chat_server.Server.Domain.Features.ServerFeatures;

import Xokyopo.hw_6.chat_server.Server.Domain.Client;
import Xokyopo.hw_6.chat_server.Server.Domain.ClientsList;
import Xokyopo.hw_6.chat_server.Server.Domain.Entity.Message;
import Xokyopo.hw_6.chat_server.Server.Domain.Features.Interface.Send;

public class SendClientListAll implements Send {
    private final ClientsList  clientsList;

    public SendClientListAll(ClientsList clientsList) {
        this.clientsList = clientsList;
    }

    @Override
    public void send(Client client, Message msg) {
        Send sendList = new SendClientList(this.clientsList);
        for (Client c: this.clientsList.getList()) {
            sendList.send(client, msg);
        }
    }
}
