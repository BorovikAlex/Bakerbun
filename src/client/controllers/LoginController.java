package client.controllers;

import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoginController {

    @FXML
    private Button adminButton;

    @FXML
    private Button stewardButton;

    @FXML
    private Button clientButton;

    public void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
    }

    @FXML
    void adminButt() {
        adminButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("adminSignIn", "");

    }

    @FXML
    void stewardButt() {
        stewardButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("stewardSignIn", "");

    }

    @FXML
    void clientButt() {
        clientButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("clientSignIn", "");

    }
}
