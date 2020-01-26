package Xokyopo.hw_6.chat_server.Server.Domain;

import Xokyopo.hw_6.chat_server.Server.Domain.Entity.Message;
import Xokyopo.hw_6.chat_server.Server.Domain.Features.ClientsFeatureList;
import Xokyopo.hw_6.chat_server.Server.Domain.Features.Interface.Send;

public class Server implements Send {
    private final ClientsFeatureList clientsFeatureList;

    public Server(ClientsFeatureList clientsFeatureList) {
        this.clientsFeatureList = clientsFeatureList;
    }

    @Override
    public void send(Client client, Message msg) {
        this.clientsFeatureList.getFeature(msg.getType()).send(client, msg);
    }

}
