import client_server_app.Client;
import client_server_app.SerialObject;
import client_server_app.Server;

public class Task4 {

    public Task4() {
        this.serverRun();
        this.clientRun();
    }

    private void serverRun() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Server server = new Server();
                server.runServer();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    private void clientRun() {
        Client client = new Client();
        client.runClient();
    }
}
