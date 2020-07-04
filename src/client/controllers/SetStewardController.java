package client.controllers;

import client.sample.AlertWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class SetStewardController {

    @FXML
    private ChoiceBox<String> orderBox;

    @FXML
    private ChoiceBox<String> stewardBox;

    @FXML
    private TextField table;

    @FXML
    private Button backButton;

    @FXML
    private Button addB;

    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        client.send("getStewardSurname");
        ArrayList<String> stList = client.receiveResultList();
        client.send("getId");
        ArrayList<String> idList = client.receiveResultList();
        ObservableList<String> steward = FXCollections.observableArrayList(stList);
        ObservableList<String> id = FXCollections.observableArrayList(idList);
        orderBox.setItems(id);
        stewardBox.setItems(steward);
    }

    public void add() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        if (table.getText().equals("") || orderBox.getValue() == null || stewardBox.getValue() == null) {
            AlertWindow.display("Вы не заполнили поля!");
            return;
        }

        StringBuilder info = new StringBuilder();
        info.append("setSteward "  + orderBox.getValue() + " " + table.getText() + " " + stewardBox.getValue());
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
