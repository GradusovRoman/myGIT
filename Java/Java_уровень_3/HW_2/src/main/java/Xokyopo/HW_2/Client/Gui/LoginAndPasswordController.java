package Xokyopo.HW_2.Client.Gui;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginAndPasswordController extends AbstractController{
    @FXML TextField loginField;
    @FXML PasswordField passwordField;

    public void verifyLoginAndPassword() {
        //TODO проверка пароля и переключение окон
        this.getClientGui().getClient().sendLoginAndPassword(this.loginField.getText(), this.passwordField.getText());
        this.disableEdit();
    }

    public void disableEdit() {
        this.loginField.setDisable(true);
        this.passwordField.setDisable(true);
    }

    public void enableEdit() {
        this.loginField.setDisable(false);
        this.passwordField.setDisable(false);
        this.clearFields();
    }

    private void clearFields() {
        this.loginField.clear();
        this.passwordField.clear();
    }

    @Override
    public void inputMessage(String _msg) {

    }
}
