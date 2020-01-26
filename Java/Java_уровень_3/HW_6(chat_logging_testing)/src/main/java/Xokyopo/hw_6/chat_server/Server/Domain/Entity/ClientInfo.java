package Xokyopo.hw_6.chat_server.Server.Domain.Entity;

import java.util.List;

public class ClientInfo {
    private final String id;
    private final String name;
    private final List<String> blackListName;

    public ClientInfo(ClientInfo clientInfo) {
        this.id = clientInfo.getId();
        this.name = clientInfo.getName();
        this.blackListName = clientInfo.getBlackListName();
    }

    public ClientInfo(String id, String name, List<String> blackListName) {
        this.id = id;
        this.name = name;
        this.blackListName = blackListName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getBlackListName() {
        return blackListName;
    }

}
