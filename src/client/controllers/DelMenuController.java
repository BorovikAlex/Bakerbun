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

public class DelMenuController {

    @FXML
    private Button backButton;

    @FXML
    private Button delB;

    @FXML
    private ChoiceBox<String> dishBox;

    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        client.send("getDish");
        ArrayList<String> dishList = client.receiveResultList();
        ObservableList<String> dish = FXCollections.observableArrayList(dishList);
        dishBox.setItems(dish);
    }

    public void del() {
        if (dishBox.getValue().equals("")) {
            AlertWindow.display("Вы не выбрали блюдо!");
            return;
        }

        ClientInstance.INSTANCE.getInstance().send(("delMenu " + dishBox.getValue()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Данные удалены успешно!");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("menu", "");
    }
}
