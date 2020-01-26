package Xokyopo.hw_6.chat_server.Server.UIAdapters.ClientInputConnectionAdapter;

import Xokyopo.hw_6.chat_server.Server.Domain.Entity.Message;

public interface ClientInputConnectionAdapterOutput {

    void send(Message msg);
}
