package client.controllers;

import client.sample.AlertWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ClientSignInController {

    @FXML
    private Button regButton;

    @FXML
    private TextField clientLogin;

    @FXML
    private PasswordField clientPassword;

    @FXML
    private Button backButton;

    public void signInClient() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        if (clientLogin.getText().equals("")|| clientPassword.getText().equals("")) {
            AlertWindow.display("Вы не заполнили поля!");
            return;
        }
        client.send("loginClient " + clientLogin.getText() + " " + clientPassword.getText());
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            clientPassword.getScene().getWindow().hide();
            client.send("getclientdata " + clientLogin.getText() + " " + clientPassword.getText());
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("clientMenu", "");
        } else {
            AlertWindow.display("Неверные логин или пароль!");
        }
    }


    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("login", "");
    }

    @FXML
    void reg() {
        regButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("registration", "");
    }
}
