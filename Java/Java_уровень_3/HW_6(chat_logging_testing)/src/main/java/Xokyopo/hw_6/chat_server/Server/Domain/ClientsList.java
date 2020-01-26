package Xokyopo.hw_6.chat_server.Server.Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ClientsList {
    private final Vector<Client> clientList;

    public ClientsList(Vector<Client> clientList) {
        this.clientList = clientList;
    }

    public void addClient(Client client) {
        this.clientList.add(client);
    }

    public void dropClient(Client client) {
        this.clientList.remove(client);
    }

    public List<Client> getList() {
        return new ArrayList<>(this.clientList);
    }

    //под вопросом
    public Client findByServerClientName(String name) {
        for (Client currentClient : this.getList()) {
            if (currentClient.getName().equals(name)) return currentClient;
        }

        //нулл
        return null;
    }
}
