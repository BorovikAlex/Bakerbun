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

public class AddClientDishController {

    @FXML
    private Button backButton;

    @FXML
    private Button addB;

    @FXML
    private TextField amount;

    @FXML
    private TextField name;

    @FXML
    private TextField table;

    @FXML
    private ChoiceBox<String> dishBox;

    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        client.send("getDish");
        ArrayList<String> dishlist = client.receiveResultList();
        ObservableList<String> dish = FXCollections.observableArrayList(dishlist);
        dishBox.setItems(dish);
    }

    public void add() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        if (name.getText().equals("") || amount.getText().equals("") || dishBox.getValue() == null || table.getText() == null) {
            AlertWindow.display("Вы не заполнили поля!");
            return;
        }

        StringBuilder info = new StringBuilder();
        info.append("addClientDish " +
                name.getText() +
                " " + table.getText() + " " + dishBox.getValue() +
                " " + amount.getText());
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

