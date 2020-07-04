package client.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.sample.AlertWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class SetAccountController {

    @FXML
    private ChoiceBox<String> orderBox;

    @FXML
    private TextField account;

    @FXML
    private Button backButton;

    @FXML
    private Button addB;

    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        client.send("getId");
        ArrayList<String> idList = client.receiveResultList();
        ObservableList<String> id = FXCollections.observableArrayList(idList);
        orderBox.setItems(id);
    }

    public void add() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        if (account.getText().equals("") || orderBox.getValue() == null ) {
            AlertWindow.display("Вы не заполнили поля!");
            return;
        }

        StringBuilder info = new StringBuilder();
        info.append("setAccount "  + orderBox.getValue() +
                " " + account.getText());
        AlertWindow.display("Данные добавлены успешно!");
        ClientInstance.INSTANCE.getInstance().send(info.toString());
        if (!ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Ошибка добавления");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("stewardOrder", "");
    }
}

