package Xokyopo.hw_6.chat_server.Server.Domain.Features.ClientFeatures;

import Xokyopo.hw_6.chat_server.MessageParser.MessageParser;
import Xokyopo.hw_6.chat_server.MessageParser.Tag;
import Xokyopo.hw_6.chat_server.Server.Domain.ClientsList;
import Xokyopo.hw_6.chat_server.Server.Domain.Entity.Message;
import Xokyopo.hw_6.chat_server.Server.Domain.Features.Interface.Send;
import Xokyopo.hw_6.chat_server.Server.Domain.Client;

public class SendPrivateMessage implements Send {
    private final ClientsList clientsList;

    public SendPrivateMessage(ClientsList clientsList) {
        this.clientsList = clientsList;
    }

    @Override
    public void send(Client client, Message msg) {
        //TODO "SendPrivateMessage" из старой записи
        MessageParser messageParser = new MessageParser();
        if (!client.getBlackListName().contains(messageParser.getValueInStringByTag(msg.getMsg(), Tag.NICKNAMETAG))) {
            client.sendMessage(msg);
        }
    }
}
