package client.controllers;

import client.sample.AlertWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AddStewardController {

    @FXML
    private Button backButton;

    @FXML
    private Button addB;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;


    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
    }

    public void addSteward() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        if (name.getText().equals("") || surname.getText().equals("")
                || login.getText().equals("") || password.getText().equals("")) {
            AlertWindow.display("Вы не заполнили поля!");
            return;
        }
        StringBuilder info = new StringBuilder();
        info.append("addSteward " + name.getText() + " " + surname.getText() + " " + login.getText()
                + " " + password.getText());
        AlertWindow.display("Данные добавлены успешно!");
        ClientInstance.INSTANCE.getInstance().send(info.toString());
        if (!ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Ошибка добавления");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("steward", "");
    }
}
