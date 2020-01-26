package Xokyopo.hw_6.chat_server.Server.DAOAdapters.DataBaseAdapter;

public class DataBaseAdapter implements DataBaseAdapterRequest{
    private final DataBaseAdapterResponse dataBaseAdapterResponse;

    public DataBaseAdapter(DataBaseAdapterResponse dataBaseAdapterResponse) {
        this.dataBaseAdapterResponse = dataBaseAdapterResponse;
    }

    @Override
    public String getNickName(String _login, String _password) {
        return this.dataBaseAdapterResponse.getNickName(_login, _password);
    }

    @Override
    public String getBlackList(String _nickName) {
        return this.dataBaseAdapterResponse.getBlackList(_nickName);
    }

    @Override
    public void addClientToBlackList(String _nickName, String _blockClientName) {
        this.dataBaseAdapterResponse.addClientToBlackList(_nickName, _blockClientName);
    }

    @Override
    public void removeClientToBlackList(String _nickName, String _blockClientName) {
        this.dataBaseAdapterResponse.removeClientToBlackList(_nickName, _blockClientName);
    }

    @Override
    public String getUserId(String _name) {
        return this.getUserId(_name);
    }

    @Override
    public boolean isLoginAreUsed(String _login) {
        return this.dataBaseAdapterResponse.isLoginAreUsed(_login);
    }

    @Override
    public boolean isNickAreUsed(String _nickname) {
        return this.dataBaseAdapterResponse.isNickAreUsed(_nickname);
    }

    @Override
    public boolean addClient(String _login, String _password, String _nickname) {
        return this.dataBaseAdapterResponse.addClient(_login, _password, _nickname);
    }
}
