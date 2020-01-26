package Xokyopo.hw_6.chat_server.Server.Domain.Features.Interface;

import Xokyopo.hw_6.chat_server.Server.Domain.Entity.Message;
import Xokyopo.hw_6.chat_server.Server.Domain.Client;

@FunctionalInterface
public interface Send {
    void send(Client client, Message msg);
}
