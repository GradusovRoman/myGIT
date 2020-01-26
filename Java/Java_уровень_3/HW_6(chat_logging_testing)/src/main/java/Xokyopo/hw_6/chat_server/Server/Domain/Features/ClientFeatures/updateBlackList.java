package Xokyopo.hw_6.chat_server.Server.Domain.Features.ClientFeatures;

import Xokyopo.hw_6.chat_server.MessageParser.MessageParser;
import Xokyopo.hw_6.chat_server.MessageParser.Tag;
import Xokyopo.hw_6.chat_server.Server.DAO.DataBase.DataBase;
import Xokyopo.hw_6.chat_server.Server.DAOAdapters.DataBaseAdapter.DataBaseAdapter;
import Xokyopo.hw_6.chat_server.Server.Domain.ClientsList;
import Xokyopo.hw_6.chat_server.Server.Domain.Entity.Message;
import Xokyopo.hw_6.chat_server.Server.Domain.Features.Interface.Send;
import Xokyopo.hw_6.chat_server.Server.Domain.Client;
import Xokyopo.hw_6.chat_server.Server.Domain.Features.ServerFeatures.SendClientList;

public class updateBlackList implements Send {
    private final ClientsList clientsList;

    //TODO костыль "DataBaseAdapter" пока что
    private final DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(new DataBase());

    public updateBlackList(ClientsList clientsList) {
        this.clientsList = clientsList;
    }

    @Override
    public void send(Client client, Message msg) {
        //TODO "updateBlackList" из старой реализации
        MessageParser messageParser = new MessageParser();

        String banName = messageParser.getValueInStringByTag(msg.getMsg(), Tag.BLACKLISTTAG);

        dataBaseAdapter.addClientToBlackList(client.getName(), banName);

        if (client.getBlackListName().contains(banName)) {
            client.getBlackListName().remove(banName);
        } else {
            client.getBlackListName().add(banName);
        }

        //сообщаем пользователю что добавили и высылаем список
        String newMsg = (messageParser.buildStringByTag("пользователь " + banName + ((client.getBlackListName().contains(banName))? " добавлен в черный списк":" удален из черного списка"), Tag.SERVERINFOTAG));
        new SendPrivateMessage(clientsList).send(client, new Message("updateBlackList",newMsg));

        new SendClientList(clientsList).send(client, msg);
    }
}
