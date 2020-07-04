package client.controllers;

import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ClientMenuController {

    @FXML
    private Button backButton;

    @FXML
    private Button orderB;

    @FXML
    private Button dishB;

    @FXML
    private Button menuB;

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("sample", "");
    }

    @FXML
    void order() {
        orderB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("addOrder", "");
    }

    @FXML
    void menu() {
        menuB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("addClientDish", "");
    }

    @FXML
    void dish() {
        dishB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("menuClinet", "");
    }
}
