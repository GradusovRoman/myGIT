package Xokyopo.hw_6.chat_server.Server.Domain.Features.ServerFeatures;

import Xokyopo.hw_6.chat_server.Server.Domain.Client;
import Xokyopo.hw_6.chat_server.Server.Domain.ClientsList;
import Xokyopo.hw_6.chat_server.Server.Domain.Entity.Message;
import Xokyopo.hw_6.chat_server.Server.Domain.Features.Interface.Send;

public class addClient implements Send {
    private final ClientsList clientsList;

    public addClient(ClientsList clientsList) {
        this.clientsList = clientsList;
    }

    @Override
    public void send(Client client, Message msg) {
        //добавляем клиента и обновляем все данные
        this.clientsList.addClient(client);

        //TODO "addClient" сообщение о том что клиент подключился

        new SendClientListAll(this.clientsList).send(client, msg);
    }
}
