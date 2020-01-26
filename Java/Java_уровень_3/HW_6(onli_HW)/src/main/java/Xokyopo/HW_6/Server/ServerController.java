package Xokyopo.HW_6.Server;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class ServerController {
    private Server server;
    @FXML
    TextArea serverLog;
    @FXML
    TextArea clientList;
    @FXML
    TextField login;
    @FXML
    TextField password;
    @FXML
    TextField nick;

    public ServerController(Server server) {
        this.server = server;
    }

    public void startServer() {
        this.printLog("Сервер запущен");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                server.connection();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void addClient() {
        String clogin = this.login.getText();
        String cpassword = this.password.getText();
        String cname = this.getValidNick(this.nick.getText());
        if(!clogin.equals("") && !cpassword.equals("") && !cname.equals("") && this.isValidLoginOrPassword(clogin) && this.isValidLoginOrPassword(cpassword)) {
            if (!DataBase.isLoginAreUsed(clogin) && !DataBase.isNickAreUsed(cname)) {
                if (DataBase.addClient(clogin, cpassword, cname)) {
                    this.printLog(String.format("пользователь с нком '%s' добавлен с учетными данными логин = '%s' и пароль = '%s'", cname, clogin, cpassword));
                } else {
                    this.printLog("ошибка добавления");
                }
            } else {
                this.printLog("Пользователь с такими учетными данными уже существует.");
            }
        } else {
            this.printLog("добавление пользователя: введены некоректные данные");
        }
        this.clearAddClientFields();
    }

    public void printLog(String _message) {
        this.serverLog.appendText(_message + "\n");
    }

    public void updateClientList(String... _clientList) {
        this.clientList.clear();
        for (int i = 0; i < _clientList.length; i++) {
            this.clientList.appendText(_clientList[i] + "\n");
        }
    }

    private boolean isValidLoginOrPassword(String _password) {
        //TODO проверка пароля на правильность введенных данных
        return (!_password.contains(" ") || !_password.contains("\\"));
    }

    private String getValidNick(String _nick) {
        return _nick.replace(" ", "_");
    }

    private void clearAddClientFields() {
        this.login.clear();
        this.nick.clear();
        this.password.clear();
    }

}
