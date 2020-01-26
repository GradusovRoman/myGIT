package Xokyopo.hw_6.chat_server.Server.Domain.Features.ServerFeatures;

import Xokyopo.hw_6.chat_server.Server.Domain.Client;
import Xokyopo.hw_6.chat_server.Server.Domain.ClientsList;
import Xokyopo.hw_6.chat_server.Server.Domain.Entity.Message;
import Xokyopo.hw_6.chat_server.Server.Domain.Features.Interface.Send;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SendClientList implements Send {
    private final ClientsList clientsList;

    public SendClientList(ClientsList clientsList) {
        this.clientsList = clientsList;
    }

    @Override
    public void send(Client client, Message msg) {
        //TODO помечать заблокированных
        // list.add("(mute)" + name);
        String clients = Arrays.toString(this.overlayingBlacklist(client).toArray());
        client.sendMessage(new Message("ClientList", clients));
    }

    private List<String> overlayingBlacklist(Client client) {
        List<String> clientBlackList =  client.getBlackListName();
        List<Client> listWithClient = clientsList.getList();
        List<String> nameOfClient = new ArrayList<>();

        //TODO это можно сделать еще одним способ iterator().for... поэксперементировать
        for (Client client1 : listWithClient) {
            nameOfClient.add(String.format("%%",(clientBlackList.contains(client1.getName()))? "(mute)":"", client1.getName()));
        }

        return nameOfClient;
    }
}
