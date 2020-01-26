package Xokyopo.hw_6.chat_server.Server.DAOAdapters.DataBaseAdapter;

public interface DataBaseAdapterResponse {

    String getNickName(String _login, String _password);

    String getBlackList(String _nickName);

    void addClientToBlackList(String _nickName, String _blockClientName);

    void removeClientToBlackList(String _nickName, String _blockClientName);

    String getUserId(String _name);

    boolean isLoginAreUsed(String _login);

    boolean isNickAreUsed(String _nickname);

    boolean addClient(String _login, String _password, String _nickname);
}
