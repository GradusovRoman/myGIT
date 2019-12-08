package Xokyopo.HW_7.Template;

public class Client {
    private InternetConnection internetConnection;
    private String name;

    public Client(String name, InternetConnection internetConnection) {
        this.internetConnection = internetConnection;
        this.name = name;
    }

    public InternetConnection getInternetConnection() {
        return internetConnection;
    }

    public void setInternetConnection(InternetConnection internetConnection) {
        this.internetConnection = internetConnection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
