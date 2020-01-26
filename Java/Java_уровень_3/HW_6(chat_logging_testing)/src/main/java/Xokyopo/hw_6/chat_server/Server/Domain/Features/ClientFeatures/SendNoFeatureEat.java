package Xokyopo.hw_6.chat_server.Server.Domain.Features.ClientFeatures;

import Xokyopo.hw_6.chat_server.Server.Domain.Entity.Message;
import Xokyopo.hw_6.chat_server.Server.Domain.Features.Interface.Send;
import Xokyopo.hw_6.chat_server.Server.Domain.Client;

public class SendNoFeatureEat implements Send {

    @Override
    public void send(Client client, Message msg) {
        //TODO сообщение о неизвестной команде
    }
}
