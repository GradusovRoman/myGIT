package Xokyopo.hw_6.chat_server.Server.Domain.Features.ClientFeatures;

import Xokyopo.hw_6.chat_server.Server.Domain.ClientsList;
import Xokyopo.hw_6.chat_server.Server.Domain.Entity.Message;
import Xokyopo.hw_6.chat_server.Server.Domain.Features.Interface.Send;
import Xokyopo.hw_6.chat_server.Server.Domain.Client;

public class SendMessageALL implements Send {
    private final ClientsList clientsList;

    public SendMessageALL(ClientsList clientsList) {
        this.clientsList = clientsList;
    }

    @Override
    public void send(Client client, Message msg) {
        for (Client ServerClient: clientsList.getList()) {
            //отсылает сообщение только не заблокированному пользователю
            if (!client.getBlackListName().contains(ServerClient.getName())) ServerClient.sendMessage(msg);
        }
    }
}
