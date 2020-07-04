package client.controllers;

import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminMenuController {

    @FXML
    private Button backButton;

    @FXML
    private Button stewardB;

    @FXML
    private Button clientB;

    @FXML
    private Button menuB;

    @FXML
    private Button orderB;

    @FXML
    private Button accountB;

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("sample", "");
    }

    @FXML
    void order() {
        orderB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("orders", "");
    }

    @FXML
    void steward() {
        stewardB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("steward", "");
    }

    @FXML
    void client() {
        clientB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("clients", "");
    }

    @FXML
    void account() {
        accountB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("account", "");
    }

    @FXML
    void menu() {
        menuB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("menu", "");
    }
}
