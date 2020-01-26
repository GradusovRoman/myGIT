package Xokyopo.hw_6.chat_server.Server.UIAdapters.ClientOutputConnectionAdapter;

import Xokyopo.hw_6.chat_server.Server.Domain.Entity.Message;

public interface ClientOutputConnectionAdapterInput {

    void send(Message msg);
}
