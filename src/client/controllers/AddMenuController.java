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

public class AddMenuController {


    @FXML
    private Button backButton;

    @FXML
    private Button addB;

    @FXML
    private TextField cost;

    @FXML
    private TextField dish;

    @FXML
    private TextField weight;

    @FXML
    private ChoiceBox<String> typeBox;

    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        client.send("getType");
        ArrayList<String> typelist = client.receiveResultList();
        ObservableList<String> type = FXCollections.observableArrayList(typelist);
        typeBox.setItems(type);
    }

    public void add() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        if (cost.getText().equals("") || dish.getText().equals("") || typeBox.getValue() == null || weight.getText() == null) {
            AlertWindow.display("Вы не заполнили поля!");
            return;
        }

        StringBuilder info = new StringBuilder();
        info.append("addMenu " + typeBox.getValue() +
                 dish.getText() +
                 weight.getText()+
                " " + cost.getText());
        AlertWindow.display("Данные добавлены успешно!");
        ClientInstance.INSTANCE.getInstance().send(info.toString());
        if (!ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Ошибка добавления");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("menu", "");
    }
}
