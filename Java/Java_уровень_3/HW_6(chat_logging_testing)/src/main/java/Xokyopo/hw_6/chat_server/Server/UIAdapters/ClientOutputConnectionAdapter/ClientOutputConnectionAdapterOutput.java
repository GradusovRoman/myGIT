package Xokyopo.hw_6.chat_server.Server.UIAdapters.ClientOutputConnectionAdapter;

import Xokyopo.hw_6.chat_server.Server.Domain.Entity.Message;

public interface ClientOutputConnectionAdapterOutput {

    void send(Message msg);
}
