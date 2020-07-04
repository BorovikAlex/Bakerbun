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

import java.util.ArrayList;

public class DelStewardController {

    @FXML
    private Button backButton;

    @FXML
    private Button delB;

    @FXML
    private ChoiceBox<String> stewardBox;

    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        client.send("getStewardSurname");
        ArrayList<String> stewardList = client.receiveResultList();
        ObservableList<String> surname = FXCollections.observableArrayList(stewardList);
        stewardBox.setItems(surname);
    }

    public void del() {
        if (stewardBox.getValue().equals("")) {
            AlertWindow.display("Вы не выбрали официанта!");
            return;
        }

        ClientInstance.INSTANCE.getInstance().send(("delSteward " + stewardBox.getValue()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Данные удалены успешно!");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("steward", "");
    }
}
