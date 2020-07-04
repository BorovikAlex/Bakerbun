package client.controllers;

import client.sample.AlertWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddOrderController {

    @FXML
    private Button backButton;

    @FXML
    private Button addB;

    @FXML
    private TextField phone;

    @FXML
    private TextField name;

    @FXML
    private TextField time;

    @FXML
    private TextField date;

    @FXML
    private TextField seats;



    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
    }

    public void addOrder() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        if (name.getText().equals("") || phone.getText().equals("")
                || time.getText().equals("") || date.getText().equals("")|| seats.getText().equals("")) {
            AlertWindow.display("Вы не заполнили поля!");
            return;
        }
        StringBuilder info = new StringBuilder();
        info.append("addOrder " + name.getText() + " " + phone.getText() + " " + seats.getText()
                + " " + date.getText() + " " + time.getText());
        AlertWindow.display("Данные добавлены успешно!");
        ClientInstance.INSTANCE.getInstance().send(info.toString());
        if (!ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Ошибка добавления");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("clientMenu", "");
    }
}
