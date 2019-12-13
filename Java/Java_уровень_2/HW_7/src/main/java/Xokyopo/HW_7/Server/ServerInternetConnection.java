package Xokyopo.HW_7.Server;

import Xokyopo.HW_7.Template.InputMessage;
import Xokyopo.HW_7.Template.InternetConnection;

import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ServerInternetConnection extends InternetConnection {
    private String ClientName = null;
    private Set<String> blackList = new HashSet<>();

    public ServerInternetConnection(Socket _socket, InputMessage _parent) {
        super(_socket, _parent);
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String _clientName) {
        ClientName = _clientName;
    }

    private void changeBlackLIst(String _clientName) {
       if (this.isNameInBlackLIst(_clientName)) {
           this.blackList.remove(_clientName);
       } else {
           this.blackList.add(_clientName);
       }
    }

    public void changeBlackList(String... _clientsName) {
        for (int i = 0; i < _clientsName.length; i++) {
            this.changeBlackLIst(_clientsName[i]);
        }
    }

    public String[] getBlackList() {
        //TODO пока что не нужно точно
        return this.blackList.toArray(new String[0]);
    }

    public boolean isNameInBlackLIst(String _clientName) {
        return this.blackList.contains(_clientName);
    }

}
