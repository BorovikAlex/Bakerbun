package client.controllers;

import client.sample.AlertWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class StewardSignInController {

    @FXML
    private TextField stewardLogin;

    @FXML
    private PasswordField stewardPassword;

    @FXML
    private Button backButton;

    public void signInSteward() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        if (stewardLogin.getText().equals("")|| stewardPassword.getText().equals("")) {
            AlertWindow.display("Вы не заполнили поля!");
            return;
        }
        client.send("loginSteward " + stewardLogin.getText() + " " + stewardLogin.getText());
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            stewardPassword.getScene().getWindow().hide();
            client.send("getstewarddata " + stewardLogin.getText() + " " + stewardPassword.getText());
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("stewardOrder", "");
        } else {
            AlertWindow.display("Неверные логин или пароль!");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("login", "");
    }
}
