package Xokyopo.hw_6.chat_server.Server.UIAdapters.ClientOutputConnectionAdapter;

import Xokyopo.hw_6.chat_server.Server.Domain.Entity.Message;

public class ClientOutputConnectionAdapter implements ClientOutputConnectionAdapterInput {
    private final ClientOutputConnectionAdapterOutput clientInputConnectionAdapterOutput;

    public ClientOutputConnectionAdapter(ClientOutputConnectionAdapterOutput clientInputConnectionAdapterOutput) {
        this.clientInputConnectionAdapterOutput = clientInputConnectionAdapterOutput;
    }

    @Override
    public void send(Message msg) {
        this.clientInputConnectionAdapterOutput.send(msg);
    }
}
