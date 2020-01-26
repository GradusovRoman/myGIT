package Xokyopo.hw_6.chat_server.Server.UIAdapters.ClientInputConnectionAdapter;

import Xokyopo.hw_6.chat_server.Server.Domain.Entity.Message;

public class ClientInputConnectionAdapter implements ClientInputConnectionAdapterInput {
    private final ClientInputConnectionAdapterOutput clientInputConnectionAdapterOutput;

    public ClientInputConnectionAdapter(ClientInputConnectionAdapterOutput clientInputConnectionAdapterOutput) {
        this.clientInputConnectionAdapterOutput = clientInputConnectionAdapterOutput;
    }

    @Override
    public void send(Message msg) {
        this.clientInputConnectionAdapterOutput.send(msg);
    }
}
