package client.controllers;

import client.sample.AlertWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController {

    @FXML
    private Button backButton;

    @FXML
    private TextField userName;

    @FXML
    private TextField userSurname;

    @FXML
    private TextField userLogin;

    @FXML
    private PasswordField userPassword;

    public void registration() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        if (userName.getText().equals("") || userSurname.getText().equals("") || userLogin.getText().equals("")
                || userPassword.getText().equals("")) {
            AlertWindow.display("Введите все данные!");
            return;
        }

        ClientInstance.INSTANCE.getInstance().send(("addClient " + userName.getText() + " " + userSurname.getText() + " " +
                userLogin.getText() + " " + userPassword.getText()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Клиент добавлен успешно!");
            userName.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("clientSignIn", "");
        }
        else {
            AlertWindow.display("Такой логин уже существует!");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("clientSignIn", "");
    }
}
